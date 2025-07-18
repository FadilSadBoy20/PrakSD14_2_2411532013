package Pekan7;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SelectionSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private int[] array;
	private JPanel panelArray;
	private JLabel[] labelArray;
	private JButton stepButton, resetButton, setButton;
	private JTextField inputField;
	private JTextArea stepArea;
	
	private int minIndex;
	private int i = 0, j;
	private boolean sorting = false;
	private int stepCount = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionSort frame = new SelectionSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
	
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectionSort() {
		setTitle("Selection Sort Langkah per Langkah");
		setSize(750, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		// panel input
		JPanel inputPanel = new JPanel (new FlowLayout());
		inputField = new JTextField(30);
		setButton = new JButton("Set Array");
		inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma): "));
		inputPanel.add(inputField);
		inputPanel.add(setButton);
		
		// panel array visual
		panelArray = new JPanel();
		panelArray.setLayout (new FlowLayout());
		
		// panel kontrol
		JPanel controlPanel = new JPanel();
		stepButton = new JButton("Langkah selanjutnya");
		resetButton = new JButton("Reset");
		stepButton.setEnabled(false);
		controlPanel.add(stepButton);
		controlPanel.add(resetButton);
		
		// area teks untuk log langkah-langkah
		stepArea = new JTextArea(8, 60);
		stepArea.setEditable(false);
		stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(stepArea);
		
		// tambahkan panel ke frame
		add(inputPanel, BorderLayout.NORTH);
		add(panelArray, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.EAST);
		
		// event set array
		setButton.addActionListener(e -> setArrayFromInput());
		
		// event langkah selanjutnya
		stepButton.addActionListener(e -> performStep());
		
		// event reset
	    resetButton.addActionListener(e -> reset());
		
	}
	
	private void setArrayFromInput() {
		String text = inputField.getText().trim();
		if (text.isEmpty()) return;
		String[] parts = text.split(",");
		array = new int[parts.length];
		try {
			for (int k = 0; k < parts.length; k++) {
				array[k] = Integer.parseInt(parts[k].trim()); }
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,  "Masukkan hanya angka yang dipisahkan "
					+ "dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
			return; }
		i = 0;
		stepCount = 1;
		sorting = true;
		stepButton.setEnabled(true);
		stepArea.setText("");
		panelArray.removeAll();
		labelArray = new JLabel[array.length];
		for (int k = 0; k < array.length; k++) {
			labelArray[k] = new JLabel(String.valueOf(array[k]));
			labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
			labelArray[k].setOpaque(true);
			labelArray[k].setBackground(Color.WHITE);
			labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			labelArray[k].setPreferredSize(new Dimension(50, 50));
			labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
			panelArray.add(labelArray[k]);
			}
		
		panelArray.revalidate();
		panelArray.repaint();
		j = i + 1;
		highlightMinIndex();
		
		}
	
	private void performStep() {
		if (i < array.length - 1 && sorting) {
			StringBuilder stepLog = new StringBuilder();
			if (j == i + 1) {
				minIndex = i;
			}
			
			// cari indeks minimum
			if (j < array.length) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
				j++;
			}
			
			// Jika sudah selesai membandingkan
			if (j == array.length) {
				if (minIndex != i) {
					int temp = array[i];
					array[i] = array[minIndex];
					array[minIndex] = temp;
					
					stepLog.append("Langkah ").append(stepCount).append(": Menukar elemen ke-")
					.append(i).append(" (").append(array[minIndex]).append(")")
					.append(" dengan elemen ke-").append(minIndex)
					.append(" (").append(array[i]).append(")\n");
				}
				else {
					stepLog.append("Langkah ").append(stepCount).append(": Tidak ada pertukaran (elemen ke-")
							.append(i).append(" sudah minimum)\n");
				}
					
					stepLog.append("Hasil: ").append(arrayToString(array)).append("\n\n");
					stepArea.append(stepLog.toString());
				
					i++;
					j = i + 1;
					stepCount++;
				}
				
				updateLabels();
				highlightMinIndex();
				
				if (i >= array.length - 1) {
					sorting = false;
					stepButton.setEnabled(false);
					resetHighlights();
					JOptionPane.showMessageDialog(this, "Sorting selesai!");
				}
			}
			
		}
		
		private void highlightMinIndex() {
			resetHighlights();
			if (minIndex >= 0 && minIndex < labelArray.length) {
				labelArray[minIndex].setBackground(Color.YELLOW);
			}
		}
		
		private void resetHighlights() {
			for (JLabel label : labelArray) {
				label.setBackground(Color.WHITE);
			}
		}
		
			private void updateLabels() {
				for (int k = 0; k < array.length; k++) {
				labelArray[k].setText(String.valueOf(array[k]));
				}
			}
		
		private void reset() {
			inputField.setText("");
			panelArray.removeAll();
			panelArray.revalidate();
			panelArray.repaint();
			stepArea.setText("");
			stepButton.setEnabled(false);
			sorting = false;
			i = 0;
			j = 1;
			stepCount = 1;	
		}
		
		private String arrayToString(int[] arr) {
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < arr.length; k++) {
				sb.append(arr[k]);
				if (k < arr.length - 1) sb.append(", ");
			}
			return sb.toString();
		}
	}