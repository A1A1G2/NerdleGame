package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class gameMenu implements exitListener{
	private JFrame frame;
	
	public ArrayList<String> CorrectFormula;
	Guess[] gss = new Guess[6];
	int[] time = new int[3];
	JButton continueButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameMenu window = new gameMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gameMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getContentPane().setFocusable(false);
		frame.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
		frame.getContentPane().setBackground(new Color(250, 240, 230));
		frame.setBackground(UIManager.getColor("ToolTip.background"));
		frame.setForeground(UIManager.getColor("ToolTip.background"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton NewGameButton = new JButton("New Game\r\n");
		NewGameButton.setToolTipText("yeni oyun olu\u015Fturmak i\u00E7in bas\u0131n\u0131z.\u0130yi oyunlar");
		NewGameButton.setFocusable(false);
		NewGameButton.setForeground(new Color(244, 164, 96));
		NewGameButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		gameMenu mine = this;
		NewGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rd = new Random();
				Equation eq = new Equation(rd);
				System.out.println(eq.getFormulaLength());
				System.out.println(eq.getEqualityFormula());
				gameFrame gameFrame = new gameFrame(6,eq.getFormulaLength(),eq.getEqualityFormula());
				gameFrame.addExitListener(mine);
				gameFrame.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		NewGameButton.setBounds(128, 86, 146, 50);
		frame.getContentPane().add(NewGameButton);
		
		JButton statisticsButton = new JButton("Statistics");
		statisticsButton.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		statisticsButton.setToolTipText("istatistikleri g\u00F6rmek i\u00E7in bas\u0131n\u0131z");
		statisticsButton.setFocusable(false);
		statisticsButton.setForeground(new Color(255, 140, 0));
		statisticsButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 22));
		statisticsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsFrame statsFrame =  new StatisticsFrame();
				statsFrame.addExitListener(mine);
				statsFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		statisticsButton.setBounds(128, 146, 146, 50);
		frame.getContentPane().add(statisticsButton);
		
		JLabel lblNewLabel = new JLabel("Nerdle");
		lblNewLabel.setForeground(new Color(255, 160, 122));
		lblNewLabel.setLabelFor(frame);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Papyrus", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 9, 146, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("20011068 Ahmet Akib G\u00DCLTEK\u0130N");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(236, 223, 162, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setFocusable(false);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(341, 10, 85, 50);
		exitButton.setToolTipText("Oyundan çýkmak için basýn");
		frame.getContentPane().add(exitButton);
		
		continueButton = new JButton("Continue");
		continueButton.setToolTipText("Kay\u0131tl\u0131 oyuna devam etmek i\u00E7in bas\u0131n\u0131z.\u0130yi oyunlar");
		continueButton.setFocusable(false);
		continueButton.setFocusable(false);
		continueButton.setForeground(new Color(244, 164, 96));
		continueButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 17));
		
		String fileName = "gameFrame.dat";
		try {
			ObjectInputStream reader = new ObjectInputStream( 
					new FileInputStream( fileName ) );
			CorrectFormula = (ArrayList<String>) reader.readObject();
			gss = (Guess[]) reader.readObject();
			time = (int[]) reader.readObject();
			continueButton.setVisible(true);
			reader.close();
		} 
		catch( IOException e ) {
			continueButton.setVisible(false);
			System.out.println("game frame An exception has occured during file reading.");
			//e.printStackTrace();
		} 
		catch( ClassNotFoundException e ) {
			continueButton.setVisible(false);
			System.out.println("game frame An exception has occured while processing read records.");
			//e.printStackTrace();
		}
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				gameFrame gameFrame = new gameFrame(6,gss[0].getLengthOfCreatedFormula(),CorrectFormula,gss);
				gameFrame.setTime(time);
				gameFrame.addExitListener(mine);
				gameFrame.setVisible(true);
				frame.setVisible(false);
			}
		});
		continueButton.setBounds(128, 26, 146, 50);
		frame.getContentPane().add(continueButton);
		
		JButton testButton = new JButton("TEST");
		testButton.setFocusable(false);
		testButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testFrame tF = new testFrame();
				tF.addExitListener(mine);
				tF.setVisible(true);
				frame.setVisible(false);
			}
		});
		testButton.setBounds(10, 232, 85, 21);
		frame.getContentPane().add(testButton);
		frame.setVisible(true);
	}
	public void exitted() {
		frame.setVisible(true);
		String fileName = "gameFrame.dat";
		try {
			ObjectInputStream reader = new ObjectInputStream( 
					new FileInputStream( fileName ) );
			CorrectFormula = (ArrayList<String>) reader.readObject();
			gss = (Guess[]) reader.readObject();
			time = (int[]) reader.readObject();
			continueButton.setVisible(true);
			reader.close();
		} 
		catch( IOException e ) {
			continueButton.setVisible(false);
			System.out.println("game frame An exception has occured during file reading.");
			//e.printStackTrace();
		} 
		catch( ClassNotFoundException e ) {
			continueButton.setVisible(false);
			System.out.println("game frame An exception has occured while processing read records.");
			//e.printStackTrace();
		}
	}
}
