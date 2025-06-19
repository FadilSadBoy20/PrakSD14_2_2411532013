package Pekan8;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SpinGameBerhadiah extends JFrame {
    private int modal = 100_000;
    private final int TOTAL_KOTAK = 36;
    private final JLabel modalLabel = new JLabel("Modal: Rp. " + modal);
    private final JTextArea resultArea = new JTextArea(10, 40);
    private final JComboBox<Integer> spinCountBox = new JComboBox<>(new Integer[]{1, 5, 10});
    private final JComboBox<Integer> spinCostBox = new JComboBox<>(new Integer[]{200, 300, 500, 1000, 2000, 3000, 5000, 10000, 15000, 20000, 30000, 40000, 50000});
    private int freeSpins = 0;

    private final Random random = new Random();

    public SpinGameBerhadiah() {
        setTitle("Spin Acak Berhadiah");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Jumlah Spin:"));
        topPanel.add(spinCountBox);
        topPanel.add(new JLabel("Biaya per Spin:"));
        topPanel.add(spinCostBox);

        JButton spinButton = new JButton("SPIN");
        JButton stopButton = new JButton("STOP");
        topPanel.add(spinButton);
        topPanel.add(stopButton);
        topPanel.add(modalLabel);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        spinButton.addActionListener(e -> spin());
        stopButton.addActionListener(e -> stopGame());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void stopGame() {
        JOptionPane.showMessageDialog(this, "Permainan dihentikan. Modal akhir: Rp. " + modal);
        System.exit(0);
    }

    private void spin() {
        int jumlahSpin = (freeSpins > 0) ? freeSpins : (Integer) spinCountBox.getSelectedItem();
        int biayaPerSpin = (Integer) spinCostBox.getSelectedItem();

        if (freeSpins == 0 && modal < jumlahSpin * biayaPerSpin) {
            JOptionPane.showMessageDialog(this, "Modal tidak cukup.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int totalHadiah = 0;
        freeSpins = 0;

        for (int i = 0; i < jumlahSpin; i++) {
            if (freeSpins == 0) modal -= biayaPerSpin;
            ArrayList<String> hasilSpin = new ArrayList<>();
            Map<String, Integer> countMap = new HashMap<>();

            for (int j = 0; j < TOTAL_KOTAK; j++) {
                String item = generateItem();
                hasilSpin.add(item);
                countMap.put(item, countMap.getOrDefault(item, 0) + 1);
            }

            sb.append("\nSpin ke-" + (i + 1) + ": \n");
            for (int j = 0; j < TOTAL_KOTAK; j++) {
                sb.append(hasilSpin.get(j)).append((j + 1) % 6 == 0 ? "\n" : "  ");
            }

            // Hitung hadiah angka
            for (int num = 1; num <= 6; num++) {
                String s = String.valueOf(num);
                int count = countMap.getOrDefault(s, 0);
                if (count >= 8) {
                    int reward = num * count * (biayaPerSpin / 100);
                    totalHadiah += reward;
                    sb.append("\nHadiah angka " + s + " muncul " + count + " kali: Rp. " + reward);
                } else if (count > 7) {
                    hasilSpin.replaceAll(val -> val.equals(s) ? generateItem() : val);
                }
            }

            // Spin gratis karena S
            int sCount = countMap.getOrDefault("S", 0);
            if (sCount == 2) {
                freeSpins += 5;
                sb.append("\nBonus: 5 spin gratis karena 2 huruf S");
            } else if (sCount >= 3) {
                freeSpins += 10;
                sb.append("\nBonus: 10 spin gratis karena 3 huruf S");
            }

            // Hitung total multiplier
            int multiplier = 0;
            for (String k : countMap.keySet()) {
                if (k.startsWith("X")) {
                    try {
                        multiplier += Integer.parseInt(k.substring(1)) * countMap.get(k);
                    } catch (Exception ignored) {}
                }
            }
            if (multiplier > 0) {
                sb.append("\nBonus multiplier X" + multiplier);
                totalHadiah *= multiplier;
            }
        }

        modal += totalHadiah;
        modalLabel.setText("Modal: Rp. " + modal);
        resultArea.setText(sb.toString() + "\n\nTotal Hadiah: Rp. " + totalHadiah);
    }

    private String generateItem() {
        double roll = random.nextDouble() * 100;
        if (roll < 85.4) {
            return String.valueOf(1 + random.nextInt(6)); // Angka 1-6
        } else if (roll < 88.4) {
            return "X2";
        } else if (roll < 91.4) {
            return "X3";
        } else if (roll < 93.4) {
            return "X5";
        } else if (roll < 95.4) {
            return "X10";
        } else if (roll < 96.4) {
            return "X30";
        } else if (roll < 96.9) {
            return "X50";
        } else if (roll < 97.0) {
            return "X100";
        } else {
            return "S";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SpinGameBerhadiah::new);
    }
}
