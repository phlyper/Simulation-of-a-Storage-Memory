/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

/**
 *
 * @author PHLYPER
 */
public class Fichier extends Bloc {
    private String name;

    Fichier(int t, String name) {
        super(t);
        this.name = name;
    }

    String getname() {
        return name;
    }
    
    @Override
    public String toString() {
        return super.toString() + ",name:" + getname();
    }
}
