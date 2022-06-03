package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class testFrame extends JFrame {
	Random rnd = new Random();
	Equation equ;
	private JPanel contentPane;
	private ArrayList<exitListener> listeners= new ArrayList<>();


	/**
	 * Create the frame.
	 */
	public testFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel.setBounds(10, 10, 347, 160);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New Equation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 equ = new Equation(rnd);
				 lblNewLabel.setText(equ.toString());
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBounds(169, 232, 120, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("X");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(exitListener nl:listeners) {
					nl.exitted();
		
				}
				dispose();
			}
		});
		btnNewButton_1.setBounds(367, 10, 59, 45);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("20011068 Ahmet Akib G\u00DCLTEK\u0130N");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(274, 192, 162, 30);
		contentPane.add(lblNewLabel_1);
		
	}


	public void addExitListener(exitListener toAdd) {
	     listeners.add(toAdd);
	}
}
