/**
 * Author: Mohammad Omar Zahir - zahirm1
 * Revised: 29 March, 2021
 *
 * Description: File for the Norm Abstract object that specifies various boolean
 * properties for other methods
 */

package src;

import src.*;

public class Norm{
    private static boolean normLOs;
    private static boolean normInd;
    private static boolean normAtt;

    public static void setNorms(boolean nLOs, boolean nInd, boolean nAtt){
        normLOs = nLOs;
        normInd = nInd;
        normAtt = nAtt;
    }

    public static boolean getNLOs(){
        return normLOs;
    }

    public static boolean getNInd(){
        return normInd;
    }

    public static boolean getNAtt(){
        return normAtt;
    }

    public static void setNLOs(boolean nLOs){
        normLOs = nLOs;
    }

    public static void setNInd(boolean nInd){
        normInd = nInd;
    }

    public static void setNAtt(boolean nAtt){
        normAtt = nAtt;
    }
}
