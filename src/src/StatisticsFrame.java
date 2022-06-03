package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StatisticsFrame extends JFrame {
	private Statistics stats;
	private JPanel contentPane;
	private ArrayList<exitListener> listeners= new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public StatisticsFrame() {
		stats = new Statistics(0, 0, 0, 0, false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel TyaridaBirakilan = new JLabel("Yar\u0131da b\u0131rak\u0131lan oyun say\u0131s\u0131:");
		TyaridaBirakilan.setToolTipText("Yar\u0131da b\u0131rak\u0131lan oyun say\u0131s\u0131");
		TyaridaBirakilan.setBounds(10, 46, 162, 13);
		contentPane.add(TyaridaBirakilan);
		
		JLabel yarýdaOyun = new JLabel(Integer.toString(stats.getYarida()));
		yarýdaOyun.setBounds(205, 46, 45, 13);
		contentPane.add(yarýdaOyun);
		
		JLabel Tzaman = new JLabel("Ortalama s\u00FCre:");
		Tzaman.setToolTipText("ba\u015Far\u0131yla tamamlananlar ortalama ne kadar s\u00FCrede tamamlanm\u0131\u015F");
		Tzaman.setBounds(10, 240, 144, 13);
		contentPane.add(Tzaman);
		
		JLabel OrtalamaSure = new JLabel(Integer.toString(stats.getSure()));
		OrtalamaSure.setBounds(205, 240, 45, 13);
		contentPane.add(OrtalamaSure);
		
		JLabel TbasariliSayisi = new JLabel("Ba\u015Far\u0131l\u0131 Oyun Say\u0131s\u0131:");
		TbasariliSayisi.setToolTipText("ka\u00E7 oyun ba\u015Far\u0131yla tamamlanm\u0131\u015F");
		TbasariliSayisi.setBounds(10, 140, 144, 13);
		contentPane.add(TbasariliSayisi);
		
		JLabel basariliOyun = new JLabel(Integer.toString(stats.getbasariliOyunSayisi()));
		basariliOyun.setBounds(205, 140, 45, 13);
		contentPane.add(basariliOyun);
		
		JLabel Tortalama = new JLabel("Ortalama sat\u0131r say\u0131s\u0131:");
		Tortalama.setToolTipText("Kazan\u0131lan oyunlar\u0131n ortalama sat\u0131r say\u0131s\u0131");
		Tortalama.setBounds(10, 193, 144, 13);
		contentPane.add(Tortalama);
		
		JLabel ortalamaSatir = new JLabel(Integer.toString(stats.getSatirSayisi()));
		ortalamaSatir.setBounds(205, 193, 45, 13);
		contentPane.add(ortalamaSatir);
		
		JLabel TbasarisizlikSayisi = new JLabel("Ba\u015Far\u0131s\u0131z oyun say\u0131s\u0131:");
		TbasarisizlikSayisi.setToolTipText(" ka\u00E7 oyun ba\u015Far\u0131s\u0131zl\u0131kla sonu\u00E7lanm\u0131\u015F");
		TbasarisizlikSayisi.setBounds(10, 94, 144, 13);
		contentPane.add(TbasarisizlikSayisi);
		
		JLabel basarisizOyun = new JLabel(Integer.toString(stats.getBasarisizlik()));
		basarisizOyun.setBounds(205, 94, 45, 13);
		contentPane.add(basarisizOyun);
		
		JLabel Istatistik = new JLabel("\u0130statistikler");
		Istatistik.setFont(new Font("Tahoma", Font.BOLD, 16));
		Istatistik.setBounds(10, 10, 123, 13);
		contentPane.add(Istatistik);
		
		JButton btnNewButton = new JButton("X");
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(exitListener nl:listeners) {
					nl.exitted();
		
				}
				dispose();
			}
		});
		btnNewButton.setBounds(231, 8, 49, 51);
		contentPane.add(btnNewButton);
	}
	public void addExitListener(exitListener toAdd) {
	     listeners.add(toAdd);
	}
}
