/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Test file for CourseT objects and testing their various methods and properties
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

public class TestCourseT
{

	@Test
    public void testBlank()
    {
        assertTrue(true);
    }

    IndicatorT[] ind1 = new IndicatorT[] {
            IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
            IndicatorT.openEnded, IndicatorT.standards, IndicatorT.desPrinciples,
            IndicatorT.awarePEO, IndicatorT.modelSelect
    };

    //Duplicate IndicatorT values
    IndicatorT[] ind2 = new IndicatorT[] {
        IndicatorT.math, IndicatorT.assumpt,
        IndicatorT.recogTheory, IndicatorT.modelSelect, IndicatorT.tools, IndicatorT.math,
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
        course2.addLO(IndicatorT.math, LO5);

        course3 = new CourseT("Course 3", ind3);
        course3.addLO(IndicatorT.assumpt, LO5);
        course3.addLO(IndicatorT.assumpt, LO3); //Empty Learning Outcomes

        course4 = new CourseT("", ind4); //Empty Indicator Set

        Norm.setNorms(false, false, false);
    }

    @After
    public void tearDown()
    {
        course1 = null;
        course2 = null;
        course3 = null;
        course4 = null;
    }

    //Regular Test
    @Test
    public void getName_test_1()
    {
        assertEquals("Course 1", course1.getName());
    }

    //Regular Test
    @Test
    public void getName_test_2()
    {
        assertEquals("Course 2", course2.getName());
    }

    //Edge Case: Tests empty string for name
    @Test
    public void getName_test3()
    {
        assertEquals("", course4.getName());
    }

    //Regular Test
    @Test
    public void getIndicators_test1()
    {
        assertTrue(arrayEquality(course1.getIndicators(), ind1));
    }

    //Edge Case: Testing that the set is returned as IndicatorT list given contained duplicate (.math)
    @Test
    public void getIndicators_test2()
    {
        assertTrue(arrayEquality(course2.getIndicators(), new IndicatorT[] {
                IndicatorT.math, IndicatorT.assumpt,
                IndicatorT.recogTheory, IndicatorT.modelSelect,
                IndicatorT.tools,}));
    }

    //Edge Case: Tests empty indicator list
    @Test
    public void getIndicators_test3()
    {
        assertTrue(arrayEquality(course4.getIndicators(), new IndicatorT[] {}));
    }

    //Edge Case: Tests equality of LOs of same type/object and name only(LO1)
    @Test
    public void getLOs_test1()
    {
        assertTrue(LOsTArrayEquality(course1.getLOs(IndicatorT.specEngKnow),
                new LOsT[]{new LOsT("L1", 5, 12, 47, 23), LO6}));
    }

    @Test
    //Regular Test
    public void getLOs_test2()
    {
        assertTrue(LOsTArrayEquality(course2.getLOs(IndicatorT.modelSelect), new LOsT[]{LO2, LO3}));
    }

    @Test
    //Edge Case: Tests for indicator that does not contain any LOs
    public void getLOs_test3()
    {
        assertTrue(LOsTArrayEquality(course1.getLOs(IndicatorT.assumpt), new LOsT[]{}));
    }

    //Regular Test: Using list of newly created LOsT objects matching the same name
    @Test
    public void getLOs_test4()
    {
        assertTrue(LOsTArrayEquality(course3.getLOs(IndicatorT.assumpt), new LOsT[]{
                new LOsT("", 2, 1, 6, 9),
        new LOsT("L5", 0, 41, 23, 0),}));
    }

    @Test
    //False assertion to make sure it recognizes the names
    public void getLOs_test5()
    {
        assertFalse(LOsTArrayEquality(course3.getLOs(IndicatorT.assumpt), new LOsT[]{
                new LOsT("L3", 2, 1, 6, 9),
                new LOsT("L5", 0, 41, 23, 0),}));
    }

    @Test
    //Edge Case: Test for an IndicatorT that does not exist
    public void getLOs_test6()
    {
        assertTrue(LOsTArrayEquality(course2.getLOs(IndicatorT.desPrinciples), new LOsT[]{}));
    }

    //Exclusive testing for addLOs was not done as this was indirectly tested by the getLOs tests,
    //which would not have passed if addLOs did not function correctly

    //Regular Test: Testing the equality of a newly created LOsT object with the same name
    @Test
    public void delLOs_test1()
    {
        course1.delLO(IndicatorT.specEngKnow, LO1);
        assertTrue(LOsTArrayEquality(course1.getLOs(IndicatorT.specEngKnow),
                new LOsT[]{new LOsT("L6", 4, 8, 3, 7)}));
    }

    //Regular Test: Tested in different order
    @Test
    public void delLOs_test2()
    {
        course1.delLO(IndicatorT.specEngKnow, LO6);
        assertTrue(LOsTArrayEquality(course1.getLOs(IndicatorT.specEngKnow),
                new LOsT[]{LO1}));
    }

    //Regular Test: Removing all LOs
    @Test
    public void delLOs_test3()
    {
        course1.delLO(IndicatorT.specEngKnow, LO6);
        course1.delLO(IndicatorT.specEngKnow, LO1);
        assertTrue(LOsTArrayEquality(course1.getLOs(IndicatorT.specEngKnow),
                new LOsT[]{}));
    }

    @Test
    //Edge Case: Removing an LOsT that does not exist in the IndicatorT
    public void delLOs_test4()
    {
        course2.delLO(IndicatorT.math, LO2);
        assertTrue(LOsTArrayEquality(course2.getLOs(IndicatorT.math),
                new LOsT[]{LO5}));
    }

    @Test
    //Edge Case: Removing an LOsT for an IndicatorT that does not exist
    public void delLOs_test5()
    {
        course2.delLO(IndicatorT.standards, LO7);
        assertTrue(LOsTArrayEquality(course2.getLOs(IndicatorT.standards),
                new LOsT[]{}));
    }

    //Regular Test
    @Test
    public void member_test1()
    {
        assertTrue(course2.member(IndicatorT.math, new LOsT[]{LO5}));
    }

    @Test
    //Regular Test: Indicator does not exist
    public void member_test2()
    {
        assertFalse(course2.member(IndicatorT.desPrinciples, new LOsT[]{LO2}));
    }

    //Regular: Adding a new Learning outcome, assert False
    @Test
    public void member_test3()
    {
        course1.addLO(IndicatorT.specEngKnow, LO7);
        assertFalse(course1.member(IndicatorT.specEngKnow, new LOsT[]{LO1, LO6}));
    }

    //Regular: Adding a new Learning outcome, assert False
    @Test
    public void member_test4()
    {
        course1.addLO(IndicatorT.specEngKnow, LO7);
        assertTrue(course1.member(IndicatorT.specEngKnow, new LOsT[]{LO6, LO7, LO1}));
    }

    //Regular: Testing list in reverse order
    @Test
    public void member_test5()
    {
        assertTrue(course1.member(IndicatorT.specEngKnow, new LOsT[]{LO6, LO1}));
    }

    //Edge Case: Omitting an LOsT to ensure it only returns True if all LOs are present
    @Test
    public void member_test6()
    {
        assertFalse(course1.member(IndicatorT.specEngKnow, new LOsT[]{LO6}));
    }

    //Edge Case: Testing exception for empty input for measures method
    @Test (expected = UnsupportedOperationException.class)
    public void measure_test1(){
        course1.measures();
    }

    @Test
    //Regular Test: All Norm False
    public void measure_Ind_test1(){
        assertTrue(arrayMarginEquals(course1.measures(Att1), new double[]{9, 20, 50, 30}));
    }

    @Test
    //Regular Test: nInd true Test
    public void measure_Ind_test2(){
        Norm.setNInd(true);
        assertTrue(arrayMarginEquals(course2.measures(IndicatorT.modelSelect),
                new double[]{0.389528, 0.147859, 0.347602, 0.115729}));
    }

    @Test
    //Regular Test: nLOs true Test with nAtt true test (no effect)
    public void measure_Ind_test3(){
        Norm.setNorms(true, false, true);
        assertTrue(arrayMarginEquals(course3.measures(IndicatorT.assumpt),
                new double[]{0.111294, 0.696385, 0.692823, 0.5}));
    }

    @Test
    //Edge Case: nAtt true Test (Has no effect)
    public void measure_Ind_test4(){
        Norm.setNorms(false, false, true);
        assertTrue(arrayMarginEquals(course3.measures(IndicatorT.assumpt), new double[]{2, 42, 29, 9}));
    }

    @Test
    //Edge Case: Indicators do not exist
    public void measure_Ind_test5(){
        assertTrue(arrayMarginEquals(course4.measures(IndicatorT.math), new double[]{0,0,0,0}));
    }

    @Test
    //Regular Test: all Norm False
    public void measure_Att_test1(){
        assertTrue(arrayMarginEquals(course1.measures(Att1), new double[]{9, 20, 50, 30}));
    }

    @Test
    //Regular Test: nInd true Test
    public void measure_Att_test2(){
        Norm.setNorms(false, true, true);
        assertTrue(arrayMarginEquals(course1.measures(Att1), new double[]{0.0834, 0.18398, 0.4579, 0.27546}));
    }

    @Test
    //nAtt true Test
    public void measure_Att_test3(){
        Norm.setNorms(false, false, true);
        assertTrue(arrayMarginEquals(course3.measures(Att3), new double[]{0.024683, 0.511867, 0.353257, 0.109524}));
    }

    @Test
    //Edge Case: Indicators do not exist
    public void measure_Att_test4(){
        assertTrue(arrayMarginEquals(course4.measures(Att4), new double[]{0,0,0,0}));
    }

    @Test
    //Edge Case: Attribute does not exist
    public void measure_Att_test5(){
        assertTrue(arrayMarginEquals(course1.measures(Att2), new double[]{0,0,0,0}));
    }

    //Local Functions
    private static boolean LOsTArrayEquality(LOsT[] l1, LOsT[] l2) {
        if (l1.length != l2.length) {
            return false;
        }
        Boolean[] results = new Boolean[l1.length];
        for (int i = 0; i < l1.length; i++) {
            for (LOsT item2 : l2) {
                if (l1[i].equals(item2)) {
                    results[i] = true;
                }
            }
        }
        int amount = l1.length;
        int counter = 0;
        List<Boolean> temp = Arrays.asList(results);
        for (Object item : temp) {
            if (item instanceof Boolean) {
                if ((boolean) item) {
                    counter ++;
                }
            }
        }
        return (counter == amount);
    }

    private static boolean arrayEquality(Object[] l1, Object[] l2)
    {
        if (l1.length != l2.length) {
            return false;
        }
        List<Object> list1 = Arrays.asList(l1);
        List<Object> list2 = Arrays.asList(l2);
        for (Object item : list1)
        {
            if (!list2.contains(item))
                return false;
        }
        return true;
    }

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
