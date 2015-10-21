package knapsack.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import knapsack.BranchAndBound.BranchAndBound2;
import knapsack.BranchAndBound.BranchNBound;
import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel     contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Window() {
		setTitle("KP Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSolution = new JLabel("Solution:");
		GridBagConstraints gbc_lblSolution = new GridBagConstraints();
		gbc_lblSolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblSolution.gridx = 3;
		gbc_lblSolution.gridy = 1;
		contentPane.add(lblSolution, gbc_lblSolution);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 12;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		final JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JLabel lblN = new JLabel("N");
		GridBagConstraints gbc_lblN = new GridBagConstraints();
		gbc_lblN.insets = new Insets(0, 0, 5, 5);
		gbc_lblN.gridx = 0;
		gbc_lblN.gridy = 2;
		contentPane.add(lblN, gbc_lblN);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 2;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JLabel lblValues = new JLabel("Values");
		GridBagConstraints gbc_lblValues = new GridBagConstraints();
		gbc_lblValues.insets = new Insets(0, 0, 5, 5);
		gbc_lblValues.gridx = 0;
		gbc_lblValues.gridy = 3;
		contentPane.add(lblValues, gbc_lblValues);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 0;
		gbc_lblWeight.gridy = 4;
		contentPane.add(lblWeight, gbc_lblWeight);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 4;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblWeightMax = new JLabel("Weight Max");
		GridBagConstraints gbc_lblWeightMax = new GridBagConstraints();
		gbc_lblWeightMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightMax.gridx = 0;
		gbc_lblWeightMax.gridy = 5;
		contentPane.add(lblWeightMax, gbc_lblWeightMax);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 5;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMethod = new JLabel("Method");
		GridBagConstraints gbc_lblMethod = new GridBagConstraints();
		gbc_lblMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblMethod.gridx = 0;
		gbc_lblMethod.gridy = 9;
		contentPane.add(lblMethod, gbc_lblMethod);
		
		final JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 9;
		contentPane.add(comboBox, gbc_comboBox);
		
		comboBox.addItem("greedy");
		comboBox.addItem("DP");
		comboBox.addItem("Branch and Bound");
		comboBox.addItem("KP with OR-tools ");
		comboBox.addItem("PL with OR-tools ");
		
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("cliick "+comboBox.getSelectedItem().toString());
				lblNewLabel.setText("uuuuuu");
				
				
				//extraction des items
				String strN = textField_3.getText();
				double n = Double.parseDouble(strN);
				String strW = textField_2.getText();
				double weightMax = Double.parseDouble(strW);
				
				String strValues = textField.getText();
				List<String> items = Arrays.asList(strValues.split("\\s*,\\s*"));
				
				String strWeights = textField_1.getText();
				List<String> weights = Arrays.asList(strWeights.split("\\s*,\\s*"));
				
				
				ArrayList<Item> tmpList = new ArrayList<Item>();
				
				//creation des items
				int i = 1;
				Iterator<String> iteW = weights.iterator();
				for(Iterator<String> ite = items.iterator();ite.hasNext();){
					
					if(!iteW.hasNext() || i > n)
						break;
					
					String tmpV = ite.next();
					String tmpW = iteW.next();
					
				//	System.out.println(ite.next());
					
					double val = Double.parseDouble(tmpV);
					double weight = Double.parseDouble(tmpW);
					
					tmpList.add(new Item(i, val, weight));
					i++;
				}
				
			

				
				Solver sol = new Solver(tmpList, weightMax);
				
				int index = comboBox.getSelectedIndex();
				switch(index){
				case(0): 
					System.out.println("0");
				sol.solve();
				break;
				
				case(1):
					System.out.println("1");
				break;
				
				case(2):
					System.out.println("2");
				BranchAndBound2 bb = new BranchAndBound2(sol);
				bb.solve();
				if(bb.getBest() != null){
					System.out.println(bb.getBest().toString());	
					lblNewLabel.setText(bb.getBest().toString());
				}
				break;
				
				case(3):
					System.out.println("3");
				break;
				
				case(4):
					System.out.println("3");
				break;
				
				default:
					System.out.println("defff");
				}	
			}
		});
		GridBagConstraints gbc_btnCompute = new GridBagConstraints();
		gbc_btnCompute.insets = new Insets(0, 0, 5, 5);
		gbc_btnCompute.gridx = 0;
		gbc_btnCompute.gridy = 11;
		contentPane.add(btnCompute, gbc_btnCompute);
		
		JButton btnCancel = new JButton("Quit");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 11;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.anchor = GridBagConstraints.WEST;
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 13;
		contentPane.add(progressBar, gbc_progressBar);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}
