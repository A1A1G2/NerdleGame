package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CoolKeyboard extends JButton{
	
	private static final long serialVersionUID = 1L;
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color border;
	private Color colorActivated;
	private Color colorNotActivated;
	private int radius;
	public boolean active;
	
	public CoolKeyboard(String s) {
		active = false;
		setText(s);
		colorActivated = new Color(226,232,240);
		colorNotActivated = new Color(203, 213, 225);
		color = colorActivated;
		border = getBackground();
		radius = 10;
		setFocusable(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				active=true;
				color = colorNotActivated;
			}
			@Override
			public void mouseExited(MouseEvent e) {
				active=false;
				color = colorActivated;
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});
		
	} 
	
	public boolean isOver() {
		return over;
	}
	public void setOver(boolean over) {
		this.over = over;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColorOver() {
		return colorOver;
	}
	public void setColorOver(Color colorOver) {
		this.colorOver = colorOver;
	}
	public Color getColorClick() {
		return colorClick;
	}
	public void setColorClick(Color colorClick) {
		this.colorClick = colorClick;
	}
	public Color getBorderColor() {
		return border;
	}
	public void setBorderColor(Color borderColor) {
		this.border = borderColor;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(31,41,55));
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		
		if(active) {
			g2.setColor(Color.BLACK);
			g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
			g2.setColor(color);
			g2.fillRoundRect(3, 3, getWidth()-6, getHeight()-6, radius, radius);
		}
		else {
			g2.setColor(color);
			g2.fillRoundRect(1, 1, getWidth()-2, getHeight()-2, radius, radius);
		}
		super.paintComponent(g);
	}
	
}
	