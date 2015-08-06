/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

/**
 *
 * @author PHLYPER
 */
class ExceptionFichierIntrouvable extends Exception {

    public ExceptionFichierIntrouvable(String s) {
        super(s);
    }

    @Override
    public String toString() {
        //return super.toString();
        return "";
    }
}
