/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.rifa;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jcsiglerp
 */
public class RifaUI extends JFrame {
    
    int n = 150;
    JLabel[] labels;
    boolean[] usedNumbers;
    
    RifaUI() {
        initComponents();
    }
    
    private void initComponents() {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(700, 600);
        
        JPanel panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLayout(new GridLayout(n / 15 + 1, 15));
        initLabels(panel);
        this.add(panel);
        
        /* Init button */
        JButton button = new JButton("PIKA PIKA");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener((ActionEvent e) -> {
            choiceNumber();
        });
        this.add(button);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initLabels(JPanel panel) {
        labels = new JLabel[n];
        usedNumbers = new boolean[n];
        RifaManager.readFromFile(usedNumbers);
        for (int i = 0; i < n; ++i) {
            labels[i] = new JLabel();
            labels[i].setText(String.format("%d", i+1));
            if (usedNumbers[i]) {
                labels[i].setVisible(false);
            }
            panel.add(labels[i]);
        }
    }
    
    private int cntMissing() {
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (!usedNumbers[i]) {
                cnt++;
            }
        }
        return cnt;
    }
    
    private void choiceNumber() {
        int magicNumber = (int) Math.round(Math.random() * cntMissing());
        int j = -1;
        for (int i = 0; i < n && magicNumber > 0; ++i) {
            if (!usedNumbers[i]) {
                magicNumber--;
                j = i;
            }
        }
        if (j == -1) {
            System.out.println("No more numbers, sorry");
            return;
        }
        // Hidding the winner
        System.out.println("Hidding... " + (j+1));
        JOptionPane.showMessageDialog(this, String.format("Te tocó el número %d", j+1), "Resultado", JOptionPane.PLAIN_MESSAGE);
        labels[j].setVisible(false);
        
        RifaManager.appendIntoFile(j+1);
    }
    
}
