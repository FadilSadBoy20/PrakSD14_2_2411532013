package Pekan7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;

public class TugasSorting extends JFrame {

    // Konstruktor untuk membuat tampilan GUI
    public TugasSorting() {
        setTitle("Tugas Praktikum Sorting - GUI");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // tengah layar

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        // Proses data dan tampilkan
        char[] sortedData = sortData();
        StringBuilder result = new StringBuilder("Hasil Sorting:\n");

        for (int i = 0; i < sortedData.length; i++) {
            result.append(sortedData[i]);
            if (i < sortedData.length - 1) {
                result.append(" - ");
            }
        }

        textArea.setText(result.toString());
    }

    // Fungsi Insertion Sort
    private void insertionSort(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            char key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Menyusun data awal dan menjalankan proses sorting
    private char[] sortData() {
        // Huruf dari z ke a
        char[] fullArray = {
            'z','y','x','w','v','u','t','s','r','q','p','o','n','m',
            'l','k','j','i','h','g','f','e','d','c','b','a'
        };

        // Ambil 13 huruf pertama
        char[] dataToSort = new char[13];
        for (int i = 0; i < 13; i++) {
            dataToSort[i] = fullArray[i];
        }

        // Urutkan secara ascending
        insertionSort(dataToSort);
        return dataToSort;
    }

    // Method utama (main)
    public static void main(String[] args) {
        // Tampilkan GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TugasSorting().setVisible(true);
        });
    }
}

