/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Contains the Abstract Data Type for creating and operating with LOsT objects
 */

package src;
import src.*;

public class LOsT implements Measures {
    private String name;
    private int n_blw;
    private int n_mrg;
    private int n_mts;
    private int n_exc;

    public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) throws IllegalArgumentException{
        if ((nblw < 0) || (nmrg < 0) || (nmts < 0) || (nexc < 0))
            throw new IllegalArgumentException("Integer inputs cannot be less than 0");
        if ((nblw == 0) && (nmrg == 0) && (nmts == 0) && (nexc == 0))
            throw new IllegalArgumentException("All inputs cannot be equal to 0");
        this.name = topic;
        this.n_blw = nblw;
        this.n_mrg = nmrg;
        this.n_mts = nmts;
        this.n_exc = nexc;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o){
        LOsT l = (LOsT) o;
        return (name.equals(l.getName()));
    }

    @Override
    public double[] measures(){
        double [] t = new double[4];
        t[0] = n_blw;
        t[1] = n_mrg;
        t[2] = n_mts;
        t[3] = n_exc;
        if (!Norm.getNLOs()){
            return t;
        }
        else {
            return Services.normal(t);
        }
    }

    @Override
    public double[] measures(IndicatorT ind) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double[] measures(AttributeT att) {
        throw new UnsupportedOperationException();
    }
}
