package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.SystemColor;

public class gameFrame extends JFrame implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> CorrectFormula;
	public Row row[];
	public int length;
	public int width1;
	public int line = 0;
	public boolean finded=false;
	private ArrayList<exitListener> listeners= new ArrayList<>();
	public Timer timer;
	public int hour=0,minute=0,second=0;
	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public gameFrame(int length,int width, ArrayList<String> CorrectFormula) {
		setName("frame0");
		setResizable(false);
		this.length = length;
		this.width1 = width;
		this.CorrectFormula=CorrectFormula;
		row = new Row[6];
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 631));
		setMinimumSize(new Dimension(700, 630));
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(31,41,55));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPane.rowHeights = new int[]{50, 50, 50, 50, 50, 50, 50, 86};
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 52, 0, 0};
		contentPane.setLayout(gbl_contentPane);
		
		initializeRows();
		enableRow(line);
		JLabel lblNewLabel = new JLabel("0:0:0");//timer
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 10, 5);
		gbc_lblNewLabel.gridx = 8;
		gbc_lblNewLabel.gridy = 1;
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(second>=60) {
					minute+=second/60;
					second=second%60;
				}
				if(minute>=60) {
					hour+=minute/60;
					minute=minute%60;
				}
				lblNewLabel.setText(hour+":"+minute+":"+second);
				
			}
		});
		timer.start();
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		CoolKeyboard exitButton = new CoolKeyboard("exit");
		exitButton.setFocusable(false);
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(0, 0, 10, 5);
		gbc_exitButton.gridx = 8;
		gbc_exitButton.gridy = 0;
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tim = (hour*3600)+(minute*60)+second;
				Statistics stats = new Statistics(0, finded?1:0, line+1, tim, true);
				
				for(exitListener nl:listeners) {
					nl.exitted();
		
				}
				dispose();
			}
		});
		contentPane.add(exitButton, gbc_exitButton);
		initializeKeyboard();
	}
	
	public gameFrame(int length,int width, ArrayList<String> CorrectFormula, Guess[] gss) {
		
		
		this.length = length;
		this.width1 = width;
		this.CorrectFormula=CorrectFormula;
		row = new Row[6];
		
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800, 631));
		setMinimumSize(new Dimension(700, 630));
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(31,41,55));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_contentPane.rowHeights = new int[]{50, 50, 50, 50, 50, 50, 50, 86};
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 52, 0, 0};
		contentPane.setLayout(gbl_contentPane);
		
		boolean finded=false;
		for(int i=0;i<length;i++) {
			
			row[i] = new Row(width1,CorrectFormula);
			row[i].guess=gss[i];
			if(!finded) {
				for(char c:gss[i].guessFormulaChar) {
					if(c=='~') {
						finded =true;
						line=i;
						break;
					}
				}
				if(!finded) {
					row[i].setStates();
				}
			}
			GridBagConstraints gbc_row = new GridBagConstraints();
			gbc_row.gridwidth = 8;
			gbc_row.insets = new Insets(0, 0, 5, 5);
			gbc_row.fill = GridBagConstraints.BOTH;
			gbc_row.gridx = 0;
			gbc_row.gridy = i;
			contentPane.add(row[i], gbc_row);
				
			}
		enableRow(line);
		
		JLabel lblNewLabel = new JLabel("0:0:0");//timer
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 10, 5);
		gbc_lblNewLabel.gridx = 8;
		gbc_lblNewLabel.gridy = 1;
		lblNewLabel.setForeground(Color.WHITE);
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				if(second>=60) {
					minute+=second/60;
					second=second%60;
				}
				if(minute>=60) {
					hour+=minute/60;
					minute=minute%60;
				}
				lblNewLabel.setText(hour+":"+minute+":"+second);
				
			}
		});
		timer.start();
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton exitButton = new CoolKeyboard("exit");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(0, 0, 10, 5);
		gbc_exitButton.gridx = 8;
		gbc_exitButton.gridy = 0;
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(exitListener nl:listeners) {
					nl.exitted();
		
				}
				dispose();
			}
		});
		contentPane.add(exitButton, gbc_exitButton);
		initializeKeyboard();
	}
	public void addExitListener(exitListener toAdd) {
	     listeners.add(toAdd);
	 }
private void initializeRows() {
		
	for(int i=0;i<length;i++) {
		
		row[i] = new Row(width1,CorrectFormula);
		GridBagConstraints gbc_row = new GridBagConstraints();
		gbc_row.gridwidth = 8;
		gbc_row.insets = new Insets(0, 0, 5, 5);
		gbc_row.fill = GridBagConstraints.BOTH;
		gbc_row.gridx = 0;
		gbc_row.gridy = i;
		contentPane.add(row[i], gbc_row);
			
		}
	row[0].buttonArray[0].active=true;
	}
private void initializeKeyboard() {
	CoolKeyboard Keyboard0 = new CoolKeyboard((String) null);
	Keyboard0.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard0.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard0.setText("0");
	GridBagConstraints gbc_Keyboard0 = new GridBagConstraints();
	gbc_Keyboard0.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard0.gridx = 0;
	gbc_Keyboard0.gridy = 6;
	Keyboard0.setPreferredSize(new Dimension(40, 40));
	Keyboard0.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard0, gbc_Keyboard0);
	
	CoolKeyboard Keyboard1 = new CoolKeyboard((String) null);
	Keyboard1.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard1.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard1.setPreferredSize(new Dimension(40, 40));
	Keyboard1.setText("1");
	GridBagConstraints gbc_Keyboard1 = new GridBagConstraints();
	gbc_Keyboard1.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard1.gridx = 1;
	gbc_Keyboard1.gridy = 6;
	Keyboard1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard1, gbc_Keyboard1);
	
	CoolKeyboard Keyboard2 = new CoolKeyboard((String) null);
	Keyboard2.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard2.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard2.setPreferredSize(new Dimension(40, 40));
	Keyboard2.setText("2");
	GridBagConstraints gbc_Keyboard2 = new GridBagConstraints();
	gbc_Keyboard2.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard2.gridx = 2;
	gbc_Keyboard2.gridy = 6;
	Keyboard2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard2, gbc_Keyboard2);
	
	CoolKeyboard Keyboard3 = new CoolKeyboard((String) null);
	Keyboard3.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard3.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard3.setPreferredSize(new Dimension(40, 40));
	Keyboard3.setText("3");
	GridBagConstraints gbc_Keyboard3 = new GridBagConstraints();
	gbc_Keyboard3.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard3.gridx = 3;
	gbc_Keyboard3.gridy = 6;
	Keyboard3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard3, gbc_Keyboard3);
	
	CoolKeyboard Keyboard4 = new CoolKeyboard((String) null);
	Keyboard4.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard4.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard4.setPreferredSize(new Dimension(40, 40));
	Keyboard4.setText("4");
	GridBagConstraints gbc_Keyboard4 = new GridBagConstraints();
	gbc_Keyboard4.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard4.gridx = 4;
	gbc_Keyboard4.gridy = 6;
	Keyboard4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard4, gbc_Keyboard4);
	
	CoolKeyboard Keyboard5 = new CoolKeyboard((String) null);
	Keyboard5.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard5.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard5.setPreferredSize(new Dimension(40, 40));
	Keyboard5.setText("5");
	GridBagConstraints gbc_Keyboard5 = new GridBagConstraints();
	gbc_Keyboard5.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard5.gridx = 5;
	gbc_Keyboard5.gridy = 6;
	Keyboard5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard5, gbc_Keyboard5);
	
	CoolKeyboard Keyboard6 = new CoolKeyboard((String) null);
	Keyboard6.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard6.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard6.setPreferredSize(new Dimension(40, 40));
	Keyboard6.setText("6");
	GridBagConstraints gbc_Keyboard6 = new GridBagConstraints();
	gbc_Keyboard6.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard6.gridx = 6;
	gbc_Keyboard6.gridy = 6;
	Keyboard6.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard6, gbc_Keyboard6);
	
	CoolKeyboard KeyboardDiv = new CoolKeyboard((String) null);
	KeyboardDiv.setAlignmentX(Component.CENTER_ALIGNMENT);
	KeyboardDiv.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardDiv.setPreferredSize(new Dimension(40, 40));
	KeyboardDiv.setText("/");
	GridBagConstraints gbc_KeyboardDiv = new GridBagConstraints();
	gbc_KeyboardDiv.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardDiv.gridx = 6;
	gbc_KeyboardDiv.gridy = 7;
	KeyboardDiv.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	
	CoolKeyboard KeyboardMult = new CoolKeyboard((String) null);
	KeyboardMult.setAlignmentX(Component.CENTER_ALIGNMENT);
	KeyboardMult.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardMult.setPreferredSize(new Dimension(40, 40));
	KeyboardMult.setText("*");
	GridBagConstraints gbc_KeyboardMult = new GridBagConstraints();
	gbc_KeyboardMult.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardMult.gridx = 5;
	gbc_KeyboardMult.gridy = 7;
	KeyboardMult.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	
	CoolKeyboard KeyboardMinus = new CoolKeyboard((String) null);
	KeyboardMinus.setAlignmentX(Component.CENTER_ALIGNMENT);
	KeyboardMinus.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardMinus.setPreferredSize(new Dimension(40, 40));
	KeyboardMinus.setText("-");
	GridBagConstraints gbc_KeyboardMinus = new GridBagConstraints();
	gbc_KeyboardMinus.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardMinus.gridx = 4;
	gbc_KeyboardMinus.gridy = 7;
	KeyboardMinus.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	
	CoolKeyboard KeyboardPlus = new CoolKeyboard((String) null);
	KeyboardPlus.setAlignmentX(Component.CENTER_ALIGNMENT);
	KeyboardPlus.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardPlus.setPreferredSize(new Dimension(40, 40));
	KeyboardPlus.setText("+");
	GridBagConstraints gbc_KeyboardPlus = new GridBagConstraints();
	gbc_KeyboardPlus.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardPlus.gridx = 3;
	gbc_KeyboardPlus.gridy = 7;
	KeyboardPlus.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	
	CoolKeyboard Keyboard7 = new CoolKeyboard((String) null);
	Keyboard7.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard7.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard7.setPreferredSize(new Dimension(40, 40));
	Keyboard7.setText("7");
	GridBagConstraints gbc_Keyboard7 = new GridBagConstraints();
	gbc_Keyboard7.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard7.gridx = 0;
	gbc_Keyboard7.gridy = 7;
	Keyboard7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	
	CoolKeyboard KeyboardGuess = new CoolKeyboard((String) null);
	KeyboardGuess.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardGuess.setPreferredSize(new Dimension(90, 40));
	KeyboardGuess.setText("Tahmin et");
	GridBagConstraints gbc_KeyboardGuess = new GridBagConstraints();
	gbc_KeyboardGuess.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardGuess.gridx = 8;
	gbc_KeyboardGuess.gridy = 6;
	KeyboardGuess.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Guess guess = row[line].guess;
			if(guess.controlFormula(guess.charToFormula(guess.guessFormulaChar))) {
				if(guess.finded()) {
					finded = true;
					row[line].setStates();
					row[line].clear();
					row[line].nodesDisabled();
					row[line].update();
					timer.stop();
					KeyboardGuess.setEnabled(false);
					System.out.println("finded");
				}else if(line<length-1) {
					row[line].clear();
					row[line].setStates();
					enableRow(line+1);
					row[line].update();
					line++;
					row[line].buttonArray[0].active=true;
					row[line].update();
				}
				else {
					finded = false;
					row[line].setStates();
					row[line].clear();
					row[line].nodesDisabled();
					row[line].update();
					timer.stop();
					KeyboardGuess.setEnabled(false);
				}
			}
		}
	});
	
	CoolKeyboard coolKeyboard_16 = new CoolKeyboard((String) null);
	coolKeyboard_16.setAlignmentX(Component.CENTER_ALIGNMENT);
	coolKeyboard_16.setFont(new Font("Dialog", Font.BOLD, 11));
	coolKeyboard_16.setPreferredSize(new Dimension(90, 40));
	coolKeyboard_16.setText("=");
	GridBagConstraints gbc_coolKeyboard_16 = new GridBagConstraints();
	gbc_coolKeyboard_16.insets = new Insets(0, 0, 5, 5);
	gbc_coolKeyboard_16.gridx = 7;
	gbc_coolKeyboard_16.gridy = 6;
	coolKeyboard_16.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(coolKeyboard_16, gbc_coolKeyboard_16);
	contentPane.add(KeyboardGuess, gbc_KeyboardGuess);
	contentPane.add(Keyboard7, gbc_Keyboard7);
	
	CoolKeyboard Keyboard8 = new CoolKeyboard((String) null);
	Keyboard8.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard8.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard8.setPreferredSize(new Dimension(40, 40));
	Keyboard8.setText("8");
	GridBagConstraints gbc_Keyboard8 = new GridBagConstraints();
	gbc_Keyboard8.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard8.gridx = 1;
	gbc_Keyboard8.gridy = 7;
	Keyboard8.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard8, gbc_Keyboard8);
	
	CoolKeyboard Keyboard9 = new CoolKeyboard((String) null);
	Keyboard9.setAlignmentX(Component.CENTER_ALIGNMENT);
	Keyboard9.setFont(new Font("Dialog", Font.BOLD, 11));
	Keyboard9.setPreferredSize(new Dimension(40, 40));
	Keyboard9.setText("9");
	GridBagConstraints gbc_Keyboard9 = new GridBagConstraints();
	gbc_Keyboard9.insets = new Insets(0, 0, 5, 5);
	gbc_Keyboard9.gridx = 2;
	gbc_Keyboard9.gridy = 7;
	Keyboard9.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index<width1-1) {
				row[line].nodeActivated(index+1);
			}
			cn.setText(e.getActionCommand());
			row[line].guess.guessFormulaChar[index] = e.getActionCommand().charAt(0);
			
		}
	});
	contentPane.add(Keyboard9, gbc_Keyboard9);
	contentPane.add(KeyboardPlus, gbc_KeyboardPlus);
	contentPane.add(KeyboardMinus, gbc_KeyboardMinus);
	contentPane.add(KeyboardMult, gbc_KeyboardMult);
	contentPane.add(KeyboardDiv, gbc_KeyboardDiv);
	
	CoolKeyboard KeyboardSonrBitir = new CoolKeyboard((String) null);
	KeyboardSonrBitir.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardSonrBitir.setPreferredSize(new Dimension(110, 40));
	KeyboardSonrBitir.setText("Sonra Bitir");
	GridBagConstraints gbc_KeyboardSonrBitir = new GridBagConstraints();
	gbc_KeyboardSonrBitir.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardSonrBitir.gridx = 8;
	gbc_KeyboardSonrBitir.gridy = 7;
	KeyboardSonrBitir.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Statistics stats = new Statistics(1, 0, 0, 0,true);
			try {
				int i;
				Guess[] gss = new Guess[6]; 
				for(i=0;i<row.length;i++) {
					gss[i]=row[i].guess;
				}
				int []time = new int[3];
				time[0]=second;
				time[1]=minute;
				time[2]=hour;
				String fileName = "gameFrame.dat";
				ObjectOutputStream wrtr = new ObjectOutputStream( new FileOutputStream( fileName )  );
				wrtr.writeObject(CorrectFormula);
				wrtr.writeObject( gss );
				
				wrtr.writeObject(time);
				
				wrtr.close();
				System.out.println("The information you have entered has "
						+ "been successfully saved in file " + fileName);
			} 
			catch( IOException rr ) {
				System.out.println("An exception has occured during writing to file.");
				rr.printStackTrace();
			} 
			for(exitListener nl:listeners) {
				nl.exitted();
	
			}
			dispose();
		}
	});
	
	CoolKeyboard KeyboardDel = new CoolKeyboard((String) null);
	KeyboardDel.setFont(new Font("Dialog", Font.BOLD, 11));
	KeyboardDel.setPreferredSize(new Dimension(90, 40));
	KeyboardDel.setText("Sil");
	GridBagConstraints gbc_KeyboardDel = new GridBagConstraints();
	gbc_KeyboardDel.insets = new Insets(0, 0, 5, 5);
	gbc_KeyboardDel.gridx = 7;
	gbc_KeyboardDel.gridy = 7;
	KeyboardDel.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CoolNode cn = row[line].getActive();
			int index = row[line].getActiveIndex();
			if(index>0) {
				row[line].nodeActivated(index-1);
			}
			cn.setText(" ");
			row[line].guess.guessFormulaChar[index] = '~';
			
		}
	});
	contentPane.add(KeyboardDel, gbc_KeyboardDel);
	contentPane.add(KeyboardSonrBitir, gbc_KeyboardSonrBitir);
}
private void enableRow(int indexOfRow) {
	for(int i=0;i<length;i++) {
		if(i==indexOfRow) {
			row[i].nodesEnabled();
		}
		else {
			row[i].nodesDisabled();
		}
	}
	
}
public void setTime(int[] time) {
	second = time[0];
	minute = time[1];
	hour = time[2];
}
public void setGuesses(Guess[] gss) {
	boolean finded=false;
	for(int i=0;i<row.length;i++) {
		row[i].guess=gss[i];
		row[i].setStates();
		if(!finded) {
			enableRow(i);
			row[i].update();
			for(char c:gss[i].guessFormulaChar) {
				if(c=='~') {
					finded =true;
					line=i;
					break;
				}
			}
		}
	}	
}
}
