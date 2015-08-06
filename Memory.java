/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

/**
 *
 * @author PHLYPER
 */
public interface Memory {

    public int getCapacity();

    public int getUsed();

    public int getFree();

    public void addFile(Fichier f) throws ExceptionEspacePleine;

    public boolean removeFile(String name) throws ExceptionFichierIntrouvable;

    public Fichier getFile(String name) throws ExceptionFichierIntrouvable;

    public void defrag();
}
