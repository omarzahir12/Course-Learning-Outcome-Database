/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Test file for LOsT objects and testing their various methods and properties
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestLOsT
{
    private LOsT LO1;
    private LOsT LO2;
    private LOsT LO3;
    private LOsT LO4;
    private LOsT LO5;
    private LOsT LO6;
    private LOsT LO7;

    @Before
    public void setup(){
        LO1 = new LOsT("t1", 23, 45, 56, 89);
        LO2 = new LOsT("t2", 15, 6, 78, 4);
        LO3 = new LOsT("", 2, 1, 6, 9);
        LO5 = new LOsT("t5", 0, 41, 23, 0);
        LO6 = new LOsT("t6", 4, 8, 3, 7);
        LO7 = new LOsT("t7", 130708, 120, 12300, 10050);
        Norm.setNorms(true, false, false);
    }

    @After
    public void teardown(){
        LO1 = null;
        LO2 = null;
        LO3 = null;
        LO4 = null;
        LO5 = null;
        LO6 = null;
        LO7 = null;
    }

    @Test
    //Regular test
    public void name_test1() {
        assertTrue(LO1.getName().equals("t1"));
    }

    @Test
    //Empty name test
    public void name_test2() {
        assertTrue(LO3.getName().equals(""));
    }

    @Test
    //Regular test
    public void equals_test1() {
        assertTrue(LO3.equals(LO3));
    }

    @Test
    //Edge Case: Test for a different LOsT object of same name
    public void equals_test2() {
        assertTrue(LO5.equals(new LOsT("t5", 0, 41, 23, 0)));
    }

    //Test for all Zero exception
    @Test (expected = IllegalArgumentException.class)
    public void all_zero_test() {
        LO4 = new LOsT("t4", 0, 0, 0, 0);
    }

    //Test for negative number exception
    @Test (expected = IllegalArgumentException.class)
    public void negative_int_test() {
        LO6 = new LOsT("t6", -10, -4, -3, 5);
    }

    //Regular - nLOs true test
    @Test
    public void measures_test1() {
        double[] l = LO1.measures();
        double[] e = new double[] {0.1079, 0.2112, 0.2629, 0.4178};
        assertTrue(arrayMarginEquals(l, e));
    }

    //Regular - nLOs true test
    @Test
    public void measures_test2() {
        double[] l = LO2.measures();
        double[] e = new double[] {0.1456, 0.0582, 0.7572, 0.03883};
        assertTrue(arrayMarginEquals(l, e));
    }

    //Regular - nLOs true test
    @Test
    public void measures_test3() {
        double[] l = LO3.measures();
        double[] e = new double[] {0.1111, 0.0555, 0.3333, 0.5};
        assertTrue(arrayMarginEquals(l, e));
    }

    //Boundary Case: Large number test
    @Test
    public void measures_test4() {
        double[] l = LO7.measures();
        double[] e = new double[] {0.8533, 0.0007834, 0.0802, 0.0656};
        assertTrue(arrayMarginEquals(l, e));
    }

    //Edge Case: nInd True test (Has no Effect)
    @Test
    public void measures_test5() {
        Norm.setNorms(false, true, false);
        double[] l = LO6.measures();
        double[] e = new double[] {4,8,3,7};
        assertTrue(arrayMarginEquals(l, e));
    }

    //Edge Case: nAtt True test (Has no Effect)
    @Test
    public void measures_test6() {
        Norm.setNorms(false, false, true);
        double[] l = LO7.measures();
        double[] e = new double[] {130708, 120, 12300, 10050};
        assertTrue(arrayMarginEquals(l, e));
    }

    @Test
    //Edge Case: Ensures that the Norm is affecting the calculations
    public void measures_test7() {
        Norm.setNorms(true, false, false);
        double[] l = LO7.measures();
        Norm.setNorms(false, false, false);
        double[] f = LO7.measures();
        assertFalse(arrayMarginEquals(l, f));
    }

    @Test
    //Edge Case: Ensures that the nInd and nAtt has no effect
    public void measures_test8() {
        Norm.setNorms(false, false, false);
        double[] l = LO5.measures();
        Norm.setNorms(false, true, true);
        double[] f = LO5.measures();
        assertTrue(arrayMarginEquals(l, f));
    }

    @Test (expected = UnsupportedOperationException.class)
    public void measure_exception_test1() {
        LO7.measures(IndicatorT.math);
    }

    @Test (expected = UnsupportedOperationException.class)
    public void measure_exception_test2() {
        IndicatorT[] ind = new IndicatorT[] {IndicatorT.math, IndicatorT.awarePEO};
        AttributeT att1 = new AttributeT("att1", ind);
        LO7.measures(att1);
    }

    private static boolean arrayMarginEquals(double[] l1, double[] l2)
    {
        for (int i = 0; i < l1.length; i++)
        {
            l1[i] = l1[i] - l2[i];
            if (!(Math.abs(l1[i]) < 0.001))
            {
                return false;
            }
        }
        return true;
    }
}
