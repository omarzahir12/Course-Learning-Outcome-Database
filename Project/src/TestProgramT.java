/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Test file for ProgramT objects and testing their various methods and properties
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestProgramT {

    IndicatorT[] ind1 = new IndicatorT[] {
            IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
            IndicatorT.openEnded, IndicatorT.standards, IndicatorT.desPrinciples,
            IndicatorT.awarePEO, IndicatorT.modelSelect
    };

    IndicatorT[] ind2 = new IndicatorT[] {
            IndicatorT.math, IndicatorT.assumpt,
            IndicatorT.recogTheory, IndicatorT.modelSelect, IndicatorT.tools,
    };

    IndicatorT[] ind3 = new IndicatorT[] {
            IndicatorT.assumpt, IndicatorT.suitableFund, IndicatorT.openEnded
    };

    IndicatorT[] ind4 = new IndicatorT[] {};

    LOsT LO1 = new LOsT("L1", 5, 12, 47, 23);
    LOsT LO2 = new LOsT("L2", 35, 13, 27, 2);
    LOsT LO3 = new LOsT("", 2, 1, 6, 9);
    LOsT LO5 = new LOsT("L5", 0, 41, 23, 0);
    LOsT LO6 = new LOsT("L6", 4, 8, 3, 7);
    LOsT LO7 = new LOsT("L7", 130708, 120, 12300, 10050);

    AttributeT Att1 = new AttributeT("Att1", ind1);
    AttributeT Att2 = new AttributeT("Att2", ind2);
    AttributeT Att3 = new AttributeT("", ind3);
    AttributeT Att4 = new AttributeT("", ind4);

    private CourseT course1;
    private CourseT course2;
    private CourseT course3;
    private CourseT course4;

    private ProgramT Prog1 = new ProgramT();
    private ProgramT Prog2 = new ProgramT();
    private ProgramT Prog3 = new ProgramT();
    private ProgramT Prog4 = new ProgramT();

    @Before
    public void setUp()
    {
        course1 = new CourseT("Course 1", ind1);
        course1.addLO(IndicatorT.specEngKnow, LO1);
        course1.addLO(IndicatorT.tools, LO7);   //Should not add anything as Indicator does not exist
        course1.addLO(IndicatorT.specEngKnow, LO6);

        course2 = new CourseT("Course 2", ind2);
        course2.addLO(IndicatorT.modelSelect, LO2);
        course2.addLO(IndicatorT.modelSelect, LO3);
        course2.addLO(IndicatorT.math, LO6);

        course3 = new CourseT("Course 3", ind3);
        course3.addLO(IndicatorT.assumpt, LO5);
        course3.addLO(IndicatorT.assumpt, LO3); //Empty Learning Outcomes

        course4 = new CourseT("", ind4); //Empty Indicator Set

        Prog1.add(course1);
        Prog1.add(course2);
        Prog1.add(course3);

        Prog2.add(course2);
        Prog2.add(course3);

        Prog3.add(course3);

        Norm.setNorms(false, false, false);
    }

    @After
    public void tearDown()
    {
        course1 = null;
        course2 = null;
        course3 = null;
        course4 = null;
        Prog1 = null;
        Prog2 = null;
        Prog3 = null;
        Prog4 = null;
    }

    //Exception Test for no input
    @Test (expected = UnsupportedOperationException.class)
    public void measures_exception_test1(){
        Prog1.measures();
    }

    //Exception Test for IndicatorT input
    @Test (expected = UnsupportedOperationException.class)
    public void measures_exception_test2(){
        Prog2.measures(IndicatorT.modelSelect);
    }

    @Test
    //Regular Test: All false
    public void measures_test1(){
        assertTrue(arrayMarginEquals(Prog1.measures(Att1), new double[] {0.168749, 0.272635, 0.373952, 0.1850843}));
    }

    @Test
    //Set nAtt to true
    public void measures_test2(){
        Norm.setNorms(false, false, true);
        assertTrue(arrayMarginEquals(Prog1.measures(Att1), new double[] {0.1524395, 0.2945872, 0.373359573, 0.179610459}));
    }

    @Test
    //Set nInd to true (should not affect)
    public void measures_test3(){
        Norm.setNorms(false, true, false);
        assertTrue(arrayMarginEquals(Prog2.measures(Att2), new double[] {0.19854936, 0.341069342, 0.27917759, 0.18123948}));
    }

    @Test
    //Regular: all norm false
    public void measures_test4(){
        Norm.setNorms(false, false, false);
        assertTrue(arrayMarginEquals(Prog2.measures(Att3), new double[] {0.02439024, 0.512195, 0.353658, 0.109756}));
    }

    @Test
    //nLOs true
    public void measures_test5(){
        Norm.setNorms(true, false, false);
        assertTrue(arrayMarginEquals(Prog2.measures(Att3), new double[] {0.055492, 0.3481737, 0.346745, 0.25048}));
    }

    @Test
    //nAtt and nInd true should be the same, should be the same as only n-att
    public void measures_test6(){
        Norm.setNorms(true, false, false);
        double[] d = Prog2.measures(Att3);
        Norm.setNorms(true, true, false);
        assertTrue(arrayMarginEquals(Prog2.measures(Att3), d));
    }

    @Test
    //nAtt true and false should be different
    public void measures_test7(){
        Norm.setNorms(true, false, false);
        double[] d = Prog2.measures(Att2);
        Norm.setNorms(false, false, true);
        assertFalse(arrayMarginEquals(Prog2.measures(Att2), d));
    }

    //Local function
    private static boolean arrayMarginEquals(double[] a, double[] b)
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] = a[i] - b[i];
            if (!(Math.abs(a[i]) < 0.001))
            {
                return false;
            }
        }
        return true;
    }
}
