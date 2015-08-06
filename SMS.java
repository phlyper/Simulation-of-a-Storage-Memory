/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StoMemSim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PHLYPER
 */
public class SMS extends JFrame implements ActionListener {

    JPanel panel[] = new JPanel[3];
    JLabel label[] = new JLabel[6];
    JTextField text[] = new JTextField[3];
    JButton addB = new JButton("add"), removeB = new JButton("remove");
    StorageMemorySim sms = null;

    public SMS() {
        this.setTitle("|..:: Storge Memory Similator ::..|");
        this.setVisible(false);
        this.setSize(new Dimension(1030, 280));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));


        int c = initData();
        sms = new StorageMemorySim(c);
        initGraph();

        this.setVisible(false);
    }

    private int initData() {
        String s = "";
        boolean b = false;
        while (b == false) {
            s = JOptionPane.showInputDialog(panel[1], "Donner la capacite du memoire (un entier) ");
            try {
                int c = Integer.parseInt(s);
                b = true;
                return c;
            } catch (NumberFormatException ex) {
                b = false;
            }
        }
        return 1024;
    }

    private void initGraph() {
        for (int i = 0; i < panel.length; i++) {

            switch (i) {
                case 0:
                    panel[i] = new JPanel(new GridLayout(1, 3, 0, 0));
                    panel[i].setPreferredSize(new Dimension(1000, 50));
                    break;
                case 1:
                    panel[i] = new MS();
                    panel[i].setPreferredSize(new Dimension(1000, 120));
                    break;
                case 2:
                    panel[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    panel[i].setPreferredSize(new Dimension(1000, 50));
                    break;
            }
            panel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            this.getContentPane().add(panel[i]);
        }

        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            switch (i) {
                case 0:
                    label[i].setText("Name :");
                    break;
                case 1:
                    label[i].setText("Size :");
                    break;
                case 2:
                    label[i].setText("Index :");
                    break;
                case 3:
                    label[i].setText("la capacite du memoire : " + sms.getCapacity());
                    break;
                case 4:
                    label[i].setText("L'espace memoire allouer : " + sms.getUsed());
                    break;
                case 5:
                    label[i].setText("L'espace memoire utiliser : " + sms.getFree());
                    break;
            }

            if (i > 2) {
                label[i].setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY));
            }
        }
        for (int i = 0; i < text.length; i++) {
            text[i] = new JTextField(10);
            //text[i].setPreferredSize(new Dimension(100, 28));
        }

        panel[0].add(label[3]);
        panel[0].add(label[4]);
        panel[0].add(label[5]);


        panel[2].add(label[0]);
        panel[2].add(text[0]);
        panel[2].add(label[1]);
        panel[2].add(text[1]);
        panel[2].add(addB);
        panel[2].add(label[2]);
        panel[2].add(text[2]);
        panel[2].add(removeB);

        addB.addActionListener(this);
        removeB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (e.getSource().equals(addB)) {
                try {
                    sms.addFile(new Fichier(Integer.parseInt(text[1].getText()), text[0].getText()));
                } catch (ExceptionEspacePleine ex) {
                    JOptionPane.showMessageDialog(panel[1], ex.getMessage(), "AddFile", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel[1], "La taille du fichier est entier", "AddFile", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (e.getSource().equals(removeB)) {
                try {
                    sms.removeFile(text[2].getText());
                } catch (ExceptionFichierIntrouvable ex) {
                    JOptionPane.showMessageDialog(panel[1], ex.getMessage(), "RemoveFile", JOptionPane.ERROR_MESSAGE);
                }
            }
            panel[1].repaint();
            label[4].setText("L'espace memoire allouer : " + sms.getUsed());
            label[5].setText("L'espace memoire utiliser : " + sms.getFree());
            System.out.println(sms);
        }
    }

    public static void main(String[] args) {
        SMS sms = new SMS();
        sms.setVisible(true);
    }

    public class MS extends JPanel {

        private double rapport = 1.0;

        MS() {
            setLayout(new FlowLayout(FlowLayout.CENTER));

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            rapport = (double) ((double) (this.getPreferredSize().width - (3 + sms.getInstances().size())) / (double) sms.getCapacity());
            System.out.println("(rapport:" + rapport + ")");
            int ht = this.getPreferredSize().height - 4;

            g.translate(0, 0);

            g.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 16));

            int rt = 0, dg = 0;
            for (int i = 0; i < sms.getInstances().size(); i++) {

                if (sms.getInstances().get(i) instanceof Fichier) {
                    Fichier f = (Fichier) sms.getInstances().get(i);

                    rt = (int) (f.getTaille() * rapport);

                    g.setColor(Color.RED);
                    g.fillRect(dg + 2, 2, rt, ht);

                    g.setColor(Color.WHITE);
                    g.drawString(f.getname(), dg + 4, 70);
                    g.drawString(String.valueOf(f.getTaille()), dg + 4, 110);

                    dg += rt + 1;
                } else if (sms.getInstances().get(i) instanceof BlocVide) {
                    BlocVide bv = (BlocVide) sms.getInstances().get(i);

                    rt = (int) (bv.getTaille() * rapport);

                    g.setColor(Color.BLUE);
                    g.fillRect(dg + 2, 2, rt, ht);

                    g.setColor(Color.WHITE);
                    g.drawString(String.valueOf(bv.getTaille()), dg + 4, 110);

                    dg += rt + 1;
                }
            }
        }
    }
}