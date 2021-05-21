/**
 * @file CourseT.java
 * @author Mohammad Omar Zahir - zahirm1
 * @brief Contains the Abstract Data Type for creating and operating with CourseT objects
 * @date March 29, 2021
*/

package src;

import java.util.*;

/**
* @brief Class for initializing and operating on CourseT objects.
* @details Consists of various functions, including the constructor, useful methods, getters, as well as the implementations of the
* measures methods from the Measures module.
*/
public class CourseT implements Measures {
    private String name;
    private Map<IndicatorT, HashSet<LOsT>> m = new HashMap<IndicatorT, HashSet<LOsT>>();

    /**
    * @brief Method for constructing the CourseT object.
    * @param courseName A String to represent the name.
	* @param indicators An array of indicators to be stored in the course object.
    * @details The constructor makes use of a Hashmap that maps an empty array of LOs objects to
    * each of the indicators as separate keys.
    */
    public CourseT(String courseName, IndicatorT[] indicators) {
        this.name = courseName;
        for (IndicatorT indicator : indicators) {
            this.m.put(indicator, new HashSet<LOsT>());
        }
    }

    /**
	* @brief Getter for returning the name of the CourseT object.
	* @return The name of the course as a String.
    */
    public String getName() {
        return this.name;
    }

    /**
	* @brief Getter for returning the array of IndicatorT objects given at the time of initialization.
	* @details The IndicatorT objects from the class's Hashmap.
	* @return Array of IndicatorT objects for the Course.
    */
    public IndicatorT[] getIndicators() {
        return this.m.keySet().toArray(new IndicatorT[0]);
    }

    /**
    * @brief Method for retrieving the LOsT objects that are mapped to a given IndicatorT object.
    * @param indicator An IndicatorT object for which the LOs objects that are mapped to it are to be returned.
    * @details If the IndicatorT object is found, the LOs objects that are mapped to it are returned.
    * If the IndicatorT object does not exist, or there are no LOsT objects mapped to it nothing, an empty array of LOsT objects is returned.
    * @return Array of LOsT objects that correspond to the given IndicatorT object.
    */
    public LOsT[] getLOs(IndicatorT indicator) {
        ArrayList<LOsT> l = new ArrayList<LOsT>();
        for (IndicatorT s : this.m.keySet()) {
            if (s == indicator) {
                for (LOsT i : this.m.get(s)) {
                    l.add(i);
                }
                return l.toArray(new LOsT[0]);
            }
        }
        return new LOsT[] {};
    }

    /**
    * @brief Method for adding/mapping a given LOsT object to a given IndicatorT object.
    * @param indicator An IndicatorT object for mapping the LOs object to.
	* @param outcome An LOsT object for mapping to the given IndicatorT object.
    * @details If the IndicatorT object is found, the LOs object is only added if another LOs object of the same name does not exist there.
    * If the IndicatorT object does not exist, or the LOsT object already exists mapped to the IndicatorT object, nothing is done.
    */
    public void addLO(IndicatorT indicator, LOsT outcome) {
        if (this.m.containsKey((indicator))) {
            HashSet<LOsT> t = this.m.get(indicator);
            for (LOsT l : t) {
                if (l.equals(outcome)) {
                    return;
                }
            }
            t.add(outcome);
        }
    }

    /**
    * @brief Method for removing a given LOsT object that is mapped to a given IndicatorT object.
    * @param indicator An IndicatorT object that has the LOs object mapped to it.
	* @param outcome An LOsT object that is mapped to the given IndicatorT object.
    * @details If the IndicatorT object is found, the LOs object is only removed if an LOs object of the same name exists there.
    * If the IndicatorT object does not exist, or the LOsT object of the same name does not exist mapped to the IndicatorT object,
    * nothing is done.
    */
    public void delLO(IndicatorT indicator, LOsT outcome) {
        ArrayList<LOsT> remove = new ArrayList<LOsT>();
        if (this.m.containsKey((indicator))) {
            HashSet<LOsT> t = this.m.get(indicator);
            for (LOsT l : t) {
                if (l.equals(outcome)) {
                    remove.add(l);
                }
            }
            t.removeAll(remove);
            this.m.put(indicator, t);
        }
    }

    /**
    * @brief Method for checking if a given IndicatorT object exists with the given amount of LOsT objects mapped to it.
    * @param indicator An IndicatorT object that should have the LOs objects mapped to it.
	* @param outcomes A array of LOsT objects that should be mapped to the given IndicatorT object.
    * @return A boolean indicating whether the given LOsT objects belong to the given IndicatorT object for this course.
    */
    public boolean member(IndicatorT indicator, LOsT[] outcomes) {
        if (this.m.containsKey(indicator)) {
            LOsT[] l = this.m.get(indicator).toArray(new LOsT[0]);
            return LOsTArrayEquality(l, outcomes);
        } else {
            return false;
        }
    }

    /**
	* @brief Implements the measure method from the Measure module that accepts no input.
	* @details This method is not supported in this class. If invoked, an UnsupportedOperationException will be thrown.
	* @throws UnsupportedOperationException
    */
    @Override
    public double[] measures() {
        throw new UnsupportedOperationException("Operation is not supported for no input");
    }

    /**
	* @brief Implements the measure method from the Measure module that accepts an IndicatorT object.
	* @param ind An IndicatorT object.
	* @details Overrides the measure method in the Measure module.
	*
	* @return The sum of all the LOs objects for a given IndicatorT object in the course, or the normal of this sum if that is
	* specified by a boolean value of nInd in the Norm.
    */
    @Override
    public double[] measures(IndicatorT ind) {
        if (this.getLOs(ind).length == 0) {
            return new double[]{0, 0, 0, 0};
        }
        double[] measureInd = new double[]{0, 0, 0, 0};
        for (LOsT o : this.getLOs(ind)) {
            measureInd = sumMeas(measureInd, o.measures());
        }
        if (Norm.getNInd()) {
            return Services.normal(measureInd);
        } else {
            return measureInd;
        }
    }

    /**
	* @brief Implements the measure method from the Measure module that accepts an IndicatorT object.
	* @param att An AttributeT object.
	* @details Overrides the measure method in the Measure module.
	* @return The sum of all the LOs objects for all the IndicatorT objects that correspond
	* to the AttributeT object provided, or the normal of this sum if that is
	* specified by a boolean value of nAtt in the Norm.
    */
    @Override
    public double[] measures(AttributeT att) {
        if (att.getIndicators().length == 0) {
            return new double[]{0, 0, 0, 0};
        }
        double[] measureInd = new double[]{0, 0, 0, 0};
        for (IndicatorT i : att.getIndicators()) {
            measureInd = sumMeas(measureInd, this.measures(i));
        }
        if (Norm.getNAtt()) {
            return Services.normal(measureInd);
        } else {
            return measureInd;
        }
    }

    private double[] sumMeas(double[] a, double[] b) {
        return new double[]{a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
    }

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
}
