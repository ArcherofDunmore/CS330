package edu.odu.cs.cs330.examples.shapes;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.io.StringReader;
import java.util.Scanner;

/**
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCircle {

    private Circle generic;
    private Circle fancy;

    @Before
    public void setUp()
    {
        this.generic = new Circle();
        this.fancy   = new Circle(2);
    }

    @Test
    public void testDefaultConstructor()
    {
        assertThat(this.generic.name(), equalTo("Circle"));
        assertThat(this.generic.radius(), closeTo(1, 0.01));
    }

    @Test
    public void testConstructor()
    {
        assertThat(this.fancy.name(), equalTo("Circle"));
        assertThat(this.fancy.radius(), closeTo(2.0, 1e-8));
    }

    @Test
    public void testRadiusSetter()
    {
        Circle aCircle = new Circle();

        aCircle.radius(7.39);

        assertThat(aCircle.radius(), closeTo(7.39, 1e-8));
    }

    @Test
    public void testDiameter()
    {
        assertThat(this.generic.diameter(), closeTo(2, 1e-6));
        assertThat(this.fancy.diameter(), closeTo(4, 1e-6));
    }

    @Test
    public void testArea()
    {
        assertThat(this.generic.area(),
                   closeTo(Math.PI * Math.pow(this.generic.radius(), 2), 0.05));

        assertThat(this.fancy.area(),
                   closeTo(Math.PI * Math.pow(this.fancy.radius(), 2), 0.05));
    }

    @Test
    public void testPerimeter()
    {
        assertThat(this.generic.perimeter(),
                   closeTo(2 * Math.PI * this.generic.radius(), 0.05));

        assertThat(this.fancy.perimeter(),
                   closeTo(2 * Math.PI * this.fancy.radius(), 0.05));
    }

    @Test
    public void testClone()
        throws CloneNotSupportedException
    {
        Circle aCopy = (Circle) this.fancy.clone();

        assertThat(aCopy, is(not(sameInstance(this.fancy))));

        // I really should have public defiened __eq__
        assertThat(aCopy.radius(), closeTo(this.fancy.radius(), 0.001));
    }

    @Test
    public void testToString()
    {
        String fancyStr = this.fancy.toString();

        assertThat(fancyStr, startsWith("Name"));
        assertThat(fancyStr, containsString("Circle"));
        assertThat(fancyStr, endsWith("\n"));

        assertThat(fancyStr,
                   containsString(String.format("%-12s: %24.4f",
                                                "Perimeter",
                                                this.fancy.perimeter())));
        assertThat(fancyStr,
                   containsString(String.format("%-12s: %24.4f", "Area", this.fancy.area())));

        assertThat(fancyStr,
                   containsString(String.format("%-12s: %24.4f", "Radius", this.fancy.radius())));

        assertThat(fancyStr,
                   containsString(String.format("%-12s: %24.4f", "Diameter", this.fancy.diameter())));

        assertThat(fancyStr, endsWith("\n"));
    }

    @Test
    public void testRead()
    {
        Scanner snr = new Scanner(new StringReader("9.7"));

        Circle aCircle = new Circle();
        aCircle.read(snr);

        assertThat(aCircle.name(), equalTo("Circle"));
        assertThat(aCircle.radius(), closeTo(9.7, 0.01));
    }
}
