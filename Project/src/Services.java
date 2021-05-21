/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: File for the services module that contains a functions to determine the normal
 */

package src;

import src.*;

public class Services{

    public static double[] normal(double[] v){
        double[] w = new double[v.length];
        double sum = 0;
        for (int i = 0; i < v.length; i++){
            sum = sum + v[i];
        }
        for (int i = 0; i < w.length; i++){
            w[i] = (v[i]/sum);
        }
        return w;
    }
}