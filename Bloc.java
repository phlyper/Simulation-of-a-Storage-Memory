/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

/**
 *
 * @author PHLYPER
 */
public class Bloc {

    private int taille;

    Bloc(int t) {
        taille = t;
    }

    int getTaille() {
        return taille;
    }

    @Override
    public String toString() {
        return "taille:" + getTaille();
    }

   
}
