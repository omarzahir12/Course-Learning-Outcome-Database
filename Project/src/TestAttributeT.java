/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Test file for AttributeT objects and testing their various methods and properties
 */

package src;

import src.*;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class TestAttributeT
    {
        //Empty indicator
        private IndicatorT[] ind1 = new IndicatorT[]{};
        //Completely full indicator
        private IndicatorT[] ind2 = new IndicatorT[]{
                IndicatorT.math, //Competence in mathematics
                IndicatorT.specEngKnow, //Competence in specialized engineering knowledge
                IndicatorT.assumpt, //Ability to identify reasonable assumptions
                IndicatorT.suitableFund, //Ability to identify suitable engineering fundamentals
                IndicatorT.recogTheory, //Able to recognize and discuss applicable theory knowledge base
                IndicatorT.modelSelect, //Selects appropriate model and methods
                IndicatorT.estOutcomes, //Estimates outcomes, uncertainties and determines data to collect
                IndicatorT.desProcess, //Recognizes and follows an engineering design process
                IndicatorT.desPrinciples, //Recognizes and follows engineering design principles
                IndicatorT.openEnded, //Proposes solutions to open-ended problems
                IndicatorT.ideaGeneration, //Employs appropriate techniques for generation of creative ideas
                IndicatorT.healthSafety, //Includes appropriate health and safety considerations
                IndicatorT.standards, //Determines and employs applicable standards and codes of practice
                IndicatorT.tools, //The ability to use modern/state of the art tools
                IndicatorT.engInSoc, //Demonstrates an understanding of the role of the engineer in society
                IndicatorT.awarePEO //Aware of PEO and role of licensing
        };

        //Duplicate indicators
        private IndicatorT[] ind3 = new IndicatorT[]{IndicatorT.math, IndicatorT.math};
        private IndicatorT[] ind4 = new IndicatorT[]{
                IndicatorT.math, //Competence in mathematics
                IndicatorT.specEngKnow, //Competence in specialized engineering knowledge
                IndicatorT.assumpt, //Ability to identify reasonable assumptions
                IndicatorT.suitableFund, //Ability to identify suitable engineering fundamentals
                IndicatorT.recogTheory, //Able to recognize and discuss applicable theory knowledge base
                IndicatorT.modelSelect, //Selects appropriate model and methods
                IndicatorT.estOutcomes, //Estimates outcomes, uncertainties and determines data to collect
                IndicatorT.desProcess, //Recognizes and follows an engineering design process
                IndicatorT.desPrinciples, //Recognizes and follows engineering design principles
                IndicatorT.openEnded, //Proposes solutions to open-ended problems
                IndicatorT.ideaGeneration, //Employs appropriate techniques for generation of creative ideas
                IndicatorT.healthSafety, //Includes appropriate health and safety considerations
                IndicatorT.standards, //Determines and employs applicable standards and codes of practice
                IndicatorT.tools, //The ability to use modern/state of the art tools
                IndicatorT.engInSoc, //Demonstrates an understanding of the role of the engineer in society
                IndicatorT.awarePEO, //Aware of PEO and role of licensing
                IndicatorT.math, //Competence in mathematics
                IndicatorT.specEngKnow, //Competence in specialized engineering knowledge
                IndicatorT.assumpt, //Ability to identify reasonable assumptions
                IndicatorT.suitableFund, //Ability to identify suitable engineering fundamentals
                IndicatorT.recogTheory, //Able to recognize and discuss applicable theory knowledge base
                IndicatorT.modelSelect, //Selects appropriate model and methods
                IndicatorT.estOutcomes, //Estimates outcomes, uncertainties and determines data to collect
                IndicatorT.desProcess, //Recognizes and follows an engineering design process
                IndicatorT.desPrinciples, //Recognizes and follows engineering design principles
                IndicatorT.openEnded, //Proposes solutions to open-ended problems
                IndicatorT.ideaGeneration, //Employs appropriate techniques for generation of creative ideas
                IndicatorT.healthSafety, //Includes appropriate health and safety considerations
                IndicatorT.standards, //Determines and employs applicable standards and codes of practice
                IndicatorT.tools, //The ability to use modern/state of the art tools
                IndicatorT.engInSoc, //Demonstrates an understanding of the role of the engineer in society
                IndicatorT.awarePEO //Aware of PEO and role of licensing
        };
        private IndicatorT[] ind5 = new IndicatorT[]{IndicatorT.math, IndicatorT.assumpt};
        private IndicatorT[] ind6 = new IndicatorT[]{IndicatorT.standards, IndicatorT.tools};
        private IndicatorT[] ind7 = new IndicatorT[]{IndicatorT.standards, IndicatorT.tools, IndicatorT.healthSafety, IndicatorT.desPrinciples, IndicatorT.awarePEO};

        AttributeT att1;
        AttributeT att2;
        AttributeT att3;
        AttributeT att4;
        AttributeT att5;
        AttributeT att6;
        AttributeT att7;

        @Before
        public void setup() {
            att1 = new AttributeT("", ind1);
            att2 = new AttributeT("att2", ind2);
            att3 = new AttributeT("att3", ind3);
            att4 = new AttributeT("att4", ind4);
            att5 = new AttributeT("att5", ind5);
            att6 = new AttributeT("att6", ind6);
            att7 = new AttributeT("att7", ind7);
        }

        @After
        public void teardown() {
            att1 = null;
            att2 = null;
            att3 = null;
            att4 = null;
            att5 = null;
            att6 = null;
            att7 = null;
        }

        //Edge case: Empty list of Indicators
        @Test
        public void getIndicator_test1() {
            assertTrue(arrayEquality(att1.getIndicators(), new IndicatorT[] {})); //Should be empty list
        }

        //Edge case: List of all Indicators
        @Test
        public void getIndicator_test2() {
            assertTrue(arrayEquality(att2.getIndicators(), ind2)); //Should be list of all indicators
        }

        //Edge case: Testing the set property by providing duplicate Indicator object, but should return only one
        @Test
        public void getIndicator_test3() {
            assertTrue(arrayEquality(att3.getIndicators(), new IndicatorT[] {IndicatorT.math})); //Should be list of only one .math
        }

        //Edge case: Testing the set property for the list of duplicates of all IndicatorT objects
        @Test
        public void getIndicator_test4() {
            assertTrue(arrayEquality(att4.getIndicators(), ind2)); //Should be list of all indicators
        }

        //Regular Test: Testing with a newly created IndicatorT list
        @Test
        public void getIndicator_test5() {
            arrayEquality(att5.getIndicators(), new IndicatorT[] {IndicatorT.math, IndicatorT.assumpt}); //Should be .math and .assumpt
        }

        //Regular Test
        @Test
        public void getIndicator_test6() {
            arrayEquality(att7.getIndicators(), ind7);
        }

        //Regular Test
        @Test
        public void getIndicator_test7() {
            arrayEquality(att6.getIndicators(), ind6);
        }

        //Edge case: Empty String for name
        @Test
        public void getName_test1() {
            att1.getName().equals("");
        }

        //Regular Test
        @Test
        public void getName_test2() {
            att2.getName().equals("att2");
        }

        //Regular Test
        @Test
        public void getName_test3() {
            att6.getName().equals(att6.getName());
        }

        //Regular Test
        @Test
        public void getName_test4() {
            att7.getName().equals("att7");
        }

        private static boolean arrayEquality(Object[] a1, Object[] a2)
        {
            if (a1.length != a2.length) {
                return false;
            }
            List<Object> list1 = Arrays.asList(a1);
            List<Object> list2 = Arrays.asList(a2);
            for (Object item : list1)
            {
                if (!list2.contains(item))
                    return false;
            }
            return true;
        }
}
