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

import DynamicProgram.DP;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import knapsack.BranchAndBound.BranchAndBound;
import knapsack.BranchAndBound.BranchNBoundNew;
import knapsack.heuristic.Item;
import knapsack.heuristic.Solver;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;

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
	private JTextField textField_4;
	private JTextField textField_5;

	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Window() {
		setTitle("KP Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 617);
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
		gbc_lblSolution.gridy = 0;
		contentPane.add(lblSolution, gbc_lblSolution);
		
		JLabel lblN = new JLabel("N");
		GridBagConstraints gbc_lblN = new GridBagConstraints();
		gbc_lblN.insets = new Insets(0, 0, 5, 5);
		gbc_lblN.gridx = 0;
		gbc_lblN.gridy = 1;
		contentPane.add(lblN, gbc_lblN);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 1;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		textField_3.setText("7");
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 12;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		final JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel lblValues = new JLabel("Values");
		GridBagConstraints gbc_lblValues = new GridBagConstraints();
		gbc_lblValues.insets = new Insets(0, 0, 5, 5);
		gbc_lblValues.gridx = 0;
		gbc_lblValues.gridy = 2;
		contentPane.add(lblValues, gbc_lblValues);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField.setText("70, 20, 39, 35, 7, 5, 9");
		
		
		JLabel lblWeight = new JLabel("Weights");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 0;
		gbc_lblWeight.gridy = 3;
		contentPane.add(lblWeight, gbc_lblWeight);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_1.setText("31, 10, 20, 18, 4, 3, 6");
		
		JLabel lblWeightMax = new JLabel("Weight Max");
		GridBagConstraints gbc_lblWeightMax = new GridBagConstraints();
		gbc_lblWeightMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeightMax.gridx = 0;
		gbc_lblWeightMax.gridy = 4;
		contentPane.add(lblWeightMax, gbc_lblWeightMax);
		
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_2.setText("50");
		
		JLabel lblMethod = new JLabel("Method");
		GridBagConstraints gbc_lblMethod = new GridBagConstraints();
		gbc_lblMethod.insets = new Insets(0, 0, 5, 5);
		gbc_lblMethod.gridx = 0;
		gbc_lblMethod.gridy = 5;
		contentPane.add(lblMethod, gbc_lblMethod);
		
		final JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 5;
		contentPane.add(comboBox, gbc_comboBox);
		
		comboBox.addItem("greedy");
		comboBox.addItem("DP");
		comboBox.addItem("Branch and Bound");
		comboBox.addItem("Core DP ");
		comboBox.addItem("Flow Models");
		comboBox.addItem("Forward DP");
		
		
		
		JButton btnCompute = new JButton("Compute");
		btnCompute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textPane.setText("Algo: "+comboBox.getSelectedItem().toString());
				textPane.setText("On calcul");
				
				String str       = "";
				long   startTime = 0;
				long   endTime   = 0;
				long   duration  = 0;
				
				
				//extraction des items
				String strN      = textField_3.getText();
				double n         = Double.parseDouble(strN);
				String strW      = textField_2.getText();
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
					
					double val    = Double.parseDouble(tmpV);
					double weight = Double.parseDouble(tmpW);
					
					tmpList.add(new Item(i, val, weight));
					i++;
				}

				Solver sol = new Solver(tmpList, weightMax);
				
				int index = comboBox.getSelectedIndex();
				switch(index){
				
				case(0): //greedy	
					str = "Greedy\n";
				startTime = System.nanoTime();
				sol.solve();
				endTime = System.nanoTime();
				duration = (endTime - startTime)/ 1000000 ;
				
				str += sol.getSolution();
				str += "\n Time to execute: "+duration +" millisecondes";
				textPane.setText(str);
				break;
				
				case(1)://dp
					
				str = "Branch and Bound\n";
				
				startTime = System.nanoTime();
				DP testDp = new DP(sol);
				double result = testDp.solve();
				endTime = System.nanoTime();
				duration = (endTime - startTime)/ 1000000 ;
				
				str += ""+testDp.getSolution();
				str += "result: "+result+"\n";			
				str += "\n Time to execute: "+duration +" millisecondes";
				
				textPane.setText(str);
				break;
				
				case(2)://b&b
					
				str = "Branch and Bound\n";
				startTime = System.nanoTime();
				BranchAndBound bb = new BranchAndBound(sol);
				bb.solve();
				endTime = System.nanoTime();
				duration = (endTime - startTime)/ 1000000 ; 
				
				str += bb.getResponse();
				if(bb.getBest() != null){
					str += "\n"+bb.getBest().toString();	
				}
				
				str += "\n Time to execute: "+duration +" millisecondes";
				textPane.setText(str);
				break;
				
				case(3)://core
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
		
		JLabel lblRandomGenration = new JLabel("Random Genration:");
		GridBagConstraints gbc_lblRandomGenration = new GridBagConstraints();
		gbc_lblRandomGenration.insets = new Insets(0, 0, 5, 5);
		gbc_lblRandomGenration.gridx = 0;
		gbc_lblRandomGenration.gridy = 7;
		contentPane.add(lblRandomGenration, gbc_lblRandomGenration);
		
		JLabel lblMin = new JLabel("Min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.EAST;
		gbc_lblMin.insets = new Insets(0, 0, 5, 5);
		gbc_lblMin.gridx = 0;
		gbc_lblMin.gridy = 8;
		contentPane.add(lblMin, gbc_lblMin);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 8;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.EAST;
		gbc_lblMax.insets = new Insets(0, 0, 5, 5);
		gbc_lblMax.gridx = 0;
		gbc_lblMax.gridy = 9;
		contentPane.add(lblMax, gbc_lblMax);
		
		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 9;
		contentPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		GridBagConstraints gbc_btnCompute = new GridBagConstraints();
		gbc_btnCompute.insets = new Insets(0, 0, 5, 5);
		gbc_btnCompute.gridx = 0;
		gbc_btnCompute.gridy = 11;
		contentPane.add(btnCompute, gbc_btnCompute);
		
		JButton btnCancel = new JButton("Generate");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				int max = 1000;
				int min = 1;
				if(textField_5.getText() != null)
					max = Integer.parseInt(textField_5.getText());
				if(textField_4.getText() != null)
					min = Integer.parseInt(textField_4.getText());
				
				
				
				Random randGen = new Random();
				int n = randGen.nextInt(max-min)+min;
				max = max/2;
				min = 1;
				int w = randGen.nextInt(max-min)+min;
				
				
				String strN = Integer.toString(n);
				String strW = Integer.toString(w);
				
				
				//profits
				String strV = "";
				max = 100;
				min = 1;
				for(int i = 0; i<n-1; i++){
					int p = randGen.nextInt(max-min)+min;
					strV += Integer.toString(p)+",";
				}
				int p = randGen.nextInt(max-min)+min;
				strV += Integer.toString(p);
				
				//poids
				String strWe = "";
				max = w;
				min = 1;
				for(int i = 0; i<n-1; i++){
					int we = randGen.nextInt(max-min)+min;
					strWe += Integer.toString(we)+",";
				}
				int we = randGen.nextInt(max-min)+min;
				strWe += Integer.toString(we);
				
				
				textField_3.setText(strN);
				textField.setText(strV);
				textField_2.setText(strW);
				textField_1.setText(strWe);
				
				
			}
		});
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
