/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: 29 March, 2021
 *
 * Description: Contains the Abstract Data Type for creating and operating with AttributeT objects
 */


package src;

import src.*;

import java.util.*;

public class AttributeT{
    private String name;
    private IndicatorT[] s;

    public AttributeT(String attribName, IndicatorT[] indicators){
        this.name = attribName;
        HashSet<IndicatorT> set = new HashSet<>();
        for (IndicatorT indicator : indicators){
            set.add(indicator);
        }

        IndicatorT [] ind = new IndicatorT[set.size()];
        ind = set.toArray(ind);
        this.s = ind;
    }

    public String getName(){
        return name;
    }


    public IndicatorT[] getIndicators(){
        return s;
    }
}
