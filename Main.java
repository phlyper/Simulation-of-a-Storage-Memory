/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

import java.util.Scanner;

/**
 *
 * @author PHLYPER
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("donner la taille de fichier");
        int st = sc.nextInt();
        System.out.println("la taille de fichier est:" + st);
        System.out.println("donner le nom de fichier");
        String stt = sc.next();
        System.out.println("le nom de fichier est:" + stt);
        System.out.println("donner la capacity de fichier");
        int c = sc.nextInt();
        System.out.println("la capacity  de fichier est:" + c);
        StorageMemorySim s = new StorageMemorySim(c);
        try {
            s.addFile(new Fichier(st, stt));
            s.addFile(new Fichier(100, "f1"));
            s.addFile(new Fichier(100, "f2"));
            s.addFile(new Fichier(200, "f3"));
            System.out.println(s);
            s.removeFile("f1");
            System.out.println(s);
        } catch (ExceptionEspacePleine e) {
            System.out.println("Memoire " + e.getMessage());
        } catch (ExceptionFichierIntrouvable e) {
            System.out.println("fichier" + e.getMessage());
        }
    }
}
