/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: 29 March, 2021
 *
 * Description: Contains the Abstract Data Type for creating and operating with ProgramT objects
 * on the object
 */

package src;

import src.*;

import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures {

    @Override
    public double[] measures(){
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] measures(IndicatorT ind){
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] measures(AttributeT att){
        double[] m = new double[]{0,0,0,0};
        for (CourseT c : this){
            m = sumMeas(m, c.measures(att));
        }
        return Services.normal(m);
    }

    private double[] sumMeas(double[] a, double[] b){
        return new double[] {a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
    }
}
