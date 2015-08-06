/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

import java.util.Vector;

/**
 *
 * @author PHLYPER
 */
public class StorageMemorySim implements Memory {

    Vector<Bloc> b = null;
    private int capacity = 1024;
    private int used = 0;
    private int free = capacity;

    StorageMemorySim(int c) {
        capacity = c;
        free = c;
        used = 0;
        b = new Vector<Bloc>();
        b.add(new BlocVide(free));
    }

    @Override
    public void addFile(Fichier f) throws ExceptionEspacePleine {
        if (getFree() < f.getTaille()) {
            throw new ExceptionEspacePleine("Memoire Pleine");
        } else {
            int index = -1;
            for (int i = 0; i < b.size(); i++) {
                if (b.get(i) instanceof BlocVide) {
                    if (b.get(i).getTaille() > f.getTaille()) {
                        index = i;
                        break;
                    }
                }
            }

            if (index == -1) {
                defrag();
                index = b.size() - 1;
            }

            Bloc bv = b.remove(index);
            b.add(index, f);
            if (bv.getTaille() > f.getTaille()) {
                b.add(index + 1, new BlocVide(bv.getTaille() - f.getTaille()));
            }
            used += f.getTaille();
            free -= f.getTaille();
        }
    }

    @Override
    public boolean removeFile(String name) throws ExceptionFichierIntrouvable {
        Fichier f = getFile(name);
        if (f == null) {
            throw new ExceptionFichierIntrouvable("Fichier introvable");
        } else {
            int index = b.indexOf(f);
            b.remove(f);
            b.add(index, new BlocVide(f.getTaille()));
            used -= f.getTaille();
            free += f.getTaille();
        }
        return true;
    }

    @Override
    public Fichier getFile(String name) throws ExceptionFichierIntrouvable {
        Fichier f = null;

        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) instanceof Fichier) {
                f = (Fichier) b.get(i);
                if (f.getname().equals(name)) {
                    return f;
                }
            }
        }
        
        throw new ExceptionFichierIntrouvable("Fichier introvable");
        //return null;
    }

    /*    @Override
    public void defrag() {
    int v =0;
    for(int i=0;i<b.size();i++)
    {
    if(b.get(i) instanceof BlocVide){
    v += b.get(i).getTaille();
    b.remove(i);
    }
    }
    b.add(new BlocVide(v));
    }
     */
    @Override
    public void defrag() {
        Vector<Bloc> v = new Vector();
        int vide = 0;
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) instanceof Fichier) {
                v.add(b.get(i));
            } else if (b.get(i) instanceof BlocVide) {
                vide += b.get(i).getTaille();
            }
        }
        v.add(new BlocVide(vide));
        b = v;
    }

    @Override
    public String toString() {
        String s = "Capacity :" + getCapacity() + ", Used:" + getUsed() + ", Free:" + getFree() + "\n";
        s += "NB Bloc" + b.size() + " :: ";
        for (int i = 0; i < b.size(); i++) {
            s += b.get(i).toString() + ",";
        }
        return s;
    }

    public Vector<Bloc> getInstances() {
        return b;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getUsed() {
        return used;
    }

    @Override
    public int getFree() {
        return free;
    }
}