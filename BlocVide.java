/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

/**
 *
 * @author PHLYPER
 */
public class BlocVide extends Bloc {

    BlocVide(int taille) {
        super(taille);
    }

    @Override
    public String toString() {
        return super.toString() + "bloc vide";
    }
}
