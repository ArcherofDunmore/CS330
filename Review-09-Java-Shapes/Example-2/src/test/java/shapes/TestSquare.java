package shapes;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.core.IsNull;
import static org.hamcrest.number.IsCloseTo.closeTo;

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
public class TestSquare {

    private Square generic;
    private Square fancy;

    @Before
    public void setUp()
    {
        this.generic = new Square();
        this.fancy   = new Square(2);
    }

    @Test
    public void testDefaultConstructor()
    {
        assertThat(this.generic.name(), equalTo("Square"));
        assertThat(this.generic.side(), closeTo(1, 1e-8));
    }

    @Test
    public void testConstructor()
    {
        assertThat(this.fancy.name(), equalTo("Square"));
        assertThat(this.fancy.side(), closeTo(2.0, 1e-8));
    }

    @Test
    public void testSideSetter()
    {
        Square aSquare = (Square) new Square();

        aSquare.side(7.39);

        assertThat(aSquare.side(), closeTo(7.39, 1e-8));
        assertThat(aSquare.name(), equalTo("Square"));
    }

    @Test
    public void testArea()
    {
        assertThat(this.generic.area(),
                   closeTo(Math.pow(this.generic.side(), 2), 1e-8));

        assertThat(this.fancy.area(),
                   closeTo(Math.pow(this.fancy.side(), 2), 1e-8));
    }

    @Test
    public void testPerimeter()
    {
        assertThat(this.generic.perimeter(),
                   closeTo(4 * this.generic.side(), 1e-8));

        assertThat(this.fancy.perimeter(),
                   closeTo(4 * this.fancy.side(), 1e-8));
    }

    @Test
    public void testClone()
    {
        Square aCopy = (Square) this.fancy.clone();

        assertThat(aCopy, is(not(sameInstance(this.fancy))));

        // I really should have public voidined __eq__
        assertThat(aCopy.side(), closeTo(this.fancy.side(), 0.001));
    }

    @Test
    public void testToString()
    {
        String fancyStr = this.fancy.toString();

        assertThat(fancyStr, startsWith("Name"));
        assertThat(fancyStr, containsString("Square"));
        assertThat(fancyStr,
                   containsString(String.format("%-12s: %24.4f",
                                                "Perimeter",
                                                this.fancy.perimeter())));

        assertThat(fancyStr,
                    containsString(String.format("%-12s: %24.4f",
                                                 "Area",
                                                  this.fancy.area())));

        assertThat(fancyStr,
                    containsString(String.format("%-12s: %24.4f",
                                                 "Side",
                                                 this.fancy.side())));

        assertThat(fancyStr, endsWith("\n"));
    }
}
