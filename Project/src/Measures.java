/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: March 29 2021
 *
 * Description: Contains the interface for Measures that will be implemented and
 * overriden by other classes
 */

package src;

import src.*;

interface Measures {
    public double[] measures() throws UnsupportedOperationException;
    public double[] measures(IndicatorT ind) throws UnsupportedOperationException;
    public double[] measures(AttributeT att) throws UnsupportedOperationException;
}