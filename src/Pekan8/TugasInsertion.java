package Pekan8;

//Nama: Fadil Insanus Siddik
//NIM : 2411532013

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TugasInsertion extends JFrame {
 private int[] array = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                        31, 37, 41, 43, 47};
 private JLabel[] labelArray;
 private JTextArea stepArea;
 private JButton stepButton, resetButton;
 private JPanel panelArray;
 private int i = 1, j;
 private boolean sorting = false;
 private int key;
 private int stepCount = 1;
 private boolean innerLoop = false;

 public TugasInsertion() {
     setTitle("Insertion Sort Langkah per Langkah");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setSize(800, 500);
     setLayout(new BorderLayout());
     setLocationRelativeTo(null);

     panelArray = new JPanel();
     panelArray.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
     labelArray = new JLabel[array.length];
     for (int k = 0; k < array.length; k++) {
         labelArray[k] = new JLabel(String.valueOf(array[k]), SwingConstants.CENTER);
         labelArray[k].setOpaque(true);
         labelArray[k].setBackground(Color.WHITE);
         labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
         labelArray[k].setPreferredSize(new Dimension(50, 50));
         labelArray[k].setFont(new Font("Arial", Font.BOLD, 16));
         panelArray.add(labelArray[k]);
     }

     stepArea = new JTextArea(10, 60);
     stepArea.setEditable(false);
     stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
     JScrollPane scrollPane = new JScrollPane(stepArea);

     JPanel controlPanel = new JPanel();
     stepButton = new JButton("Langkah Selanjutnya");
     resetButton = new JButton("Reset");
     controlPanel.add(stepButton);
     controlPanel.add(resetButton);

     add(panelArray, BorderLayout.NORTH);
     add(scrollPane, BorderLayout.CENTER);
     add(controlPanel, BorderLayout.SOUTH);

     stepArea.append("Nama: Fadil Insanus Siddik\n");
     stepArea.append("NIM : 2411532013\n\n");
     stepArea.append("Deret awal: ");
     tampilkanArray();
     stepArea.append("\nAlgoritma: Insertion Sort\n\n");

     stepButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             if (!sorting) {
                 key = array[i];
                 j = i - 1;
                 sorting = true;
             }
             if (j >= 0 && array[j] > key) {
                 array[j + 1] = array[j];
                 j--;
             } else {
                 array[j + 1] = key;
                 sorting = false;
                 i++;
                 stepArea.append("Langkah " + (stepCount++) + ": ");
                 tampilkanArray();
             }
             updateLabels();

             if (i >= array.length) {
                 stepArea.append("\n\nHasil akhir: ");
                 tampilkanArray();
                 stepButton.setEnabled(false);
             }
         }
     });

     resetButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             array = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                              31, 37, 41, 43, 47};
             i = 1;
             sorting = false;
             stepCount = 1;
             stepArea.setText("");
             stepArea.append("Nama: Fadil Insanus Siddik\n");
             stepArea.append("NIM : 2411532013\n\n");
             stepArea.append("Deret awal: ");
             tampilkanArray();
             stepArea.append("\nAlgoritma: Insertion Sort\n\n");
             updateLabels();
             stepButton.setEnabled(true);
         }
     });
 }

 private void tampilkanArray() {
     stepArea.append("[");
     for (int k = 0; k < array.length; k++) {
         stepArea.append(String.valueOf(array[k]));
         if (k < array.length - 1) stepArea.append(", ");
     }
     stepArea.append("]\n");
 }

 private void updateLabels() {
     for (int k = 0; k < array.length; k++) {
         labelArray[k].setText(String.valueOf(array[k]));
     }
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(() -> {
         TugasInsertion frame = new TugasInsertion();
         frame.setVisible(true);
     });
 }
}