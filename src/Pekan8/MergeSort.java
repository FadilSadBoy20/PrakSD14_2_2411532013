package Pekan8;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Queue;
import java.util.LinkedList;

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

public class MergeSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int[] array;
	private JPanel panelArray;
	private JLabel[] labelArray;
	private JButton stepButton, resetButton, setButton;
	private JTextField inputField;
	private JTextArea stepArea;
	
	private int i, j, k, left, mid, right;
	private boolean isMerging = false;
	private boolean copying = false;
	private int[] temp;
	private int stepCount = 1;
	private Queue<int[]> mergeQueue = new LinkedList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MergeSort frame = new MergeSort();
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
	public MergeSort() {
		setTitle("Merge Sort Langkah per Langkah");
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
		
		labelArray = new JLabel[array.length];
		panelArray.removeAll();
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
		
		mergeQueue.clear();
		generateMergeSteps(0, array.length - 1);
		stepButton.setEnabled(true);
		stepArea.setText("");
		stepCount = 1;
		isMerging = false;
		panelArray.revalidate();
		panelArray.repaint();
		}
	
	private void performStep() {
		resetHighlights();
		
		if (!isMerging && !mergeQueue.isEmpty()) {
			int[] range = mergeQueue.poll();
			left = range[0];
			mid = range[1];
			right = range[2];
			temp = new int[right - left + 1];
			i = left;
			j = mid + 1;
			k = 0;
			copying = false;
			isMerging = true;
			stepArea.append("Langkah " + stepCount++ + ": Mulai merge dari " + left + " ke " + right + "\n");
			return;
		}
		
		if (isMerging && !copying) {
			if (i <= mid && j <= right) {
				labelArray[i].setBackground(Color.CYAN);
				labelArray[j].setBackground(Color.CYAN);
				if (array[i] <= array[j]) {
					temp[k++] = array[i++];
				} else {
					temp[k++] = array[j++];
				}
				stepArea.append("Langkah " + stepCount++ + ": Bandingkan dan salin elemen\n");
				return;
			} else if (i <= mid) {
				temp[k++] = array[i++];
				stepArea.append("Langkah " + stepCount++ + ": Salin sisa kiri\n");
				return;
			} else if (j <= right) {
				temp[k++] = array[j++];
				stepArea.append("Langkah " + stepCount++ + ": Salin sisa kanan\n");
				return;
			} else {
				copying = true;
				k = 0;
				return;
			}
		}		
			if (copying && k < temp.length) {
				array[left + k] = temp[k];
				labelArray[left + k].setText(String.valueOf(temp[k]));
				labelArray[left + k].setBackground(Color.GREEN);
				k++;
				stepArea.append("Langkah " + stepCount++ + ": Tempelkan ke array utama\n");
				return;
			}
			
			if (copying && k == temp.length) {
				isMerging = false;
				copying = false;
			}
			
			if (mergeQueue.isEmpty() && !isMerging) {
				stepArea.append("Selesai.\n");;
				stepButton.setEnabled(false);
				JOptionPane.showMessageDialog(this,  "Merge Sort selesai!");	
				}
			
			}
		
			private void resetHighlights() {
				if (labelArray == null) return;
				for (JLabel label : labelArray) {
					label.setBackground(Color.WHITE);
				}
			}
			
			private void reset() {
				inputField.setText("");
				panelArray.removeAll();
				panelArray.revalidate();
				panelArray.repaint();
				stepArea.setText("");
				stepButton.setEnabled(false);
				mergeQueue.clear();
				isMerging = false;
				stepCount = 1;
			}
			private void updateLabels() {
				for (int k = 0; k < array.length; k++) {
				labelArray[k].setText(String.valueOf(array[k]));
				}
			}
		
		private String arrayToString(int[] arr) {
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < arr.length; k++) {
				sb.append(arr[k]);
				if (k < arr.length - 1) sb.append(", ");
			}
			return sb.toString();
		}
		
		private void generateMergeSteps(int left, int right) {
			if (left >= right) return;
			int mid = (left + right) / 2;
			generateMergeSteps(left, mid);
			generateMergeSteps(mid + 1, right);
			mergeQueue.add(new int[]{left, mid, right});
		}

	}