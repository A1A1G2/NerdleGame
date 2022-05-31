package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;


public class CoolNode extends JButton{
	
	private static final long serialVersionUID = 1L;
	private boolean over;
	private Color color;
	private Color colorOver;
	private Color colorClick;
	private Color border;
	private Color colorDisabled;
	private Color colorNotDisabled;
	private Color colorCorrect;
	private Color colorSemiCorrect;
	private Color colorNotCorrect;
	private int radius;
	public boolean active;
	private List<NodeListener> listeners;
	public final int index;
	public boolean enabled;
	public int state;
	
	public CoolNode(int index) {
		this.index = index;
		listeners = new ArrayList<NodeListener>();
		active = false;
		enabled = false;
		
		colorNotDisabled = new Color(237, 213, 209);
		colorDisabled = new Color(222, 199, 195);
		colorCorrect = new Color(91, 245, 103);
		colorSemiCorrect = new Color(202, 209, 4);
		colorNotCorrect = new Color(230, 94, 16);
		
		color = colorDisabled;
		border = getBackground();
		radius = 10;
		setFocusable(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setText("  ");
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(NodeListener nl:listeners) {
					if(enabled) {
						nl.nodeActivated(index);
					}
				}
				super.mouseClicked(e);
			}
		});
		
	} 
	
	public void updateAppereance() {
		paintComponent(getGraphics());
	}
	public void addListener(NodeListener toAdd) {
	     listeners.add(toAdd);
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
		
		if(enabled) {
			if(active) {
				border = Color.BLACK;
			}
			else {
				border = getBackground();
			}
			color = colorNotDisabled;
		}
		else {
			border = getBackground();
			
			switch (state) {
			case 0:
				color = colorDisabled;
				break;
				
			case 1:
				color = colorCorrect;
				break;
			case 2:
				color = colorSemiCorrect;
				break;
			case 3:
				color = colorNotCorrect;
				break;
			default:
				break;
			}
		}
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(border);
		
		if(border == Color.black) {
			g2.fillRoundRect(1, 1, getWidth()-2, getHeight()-2, radius, radius);
		}
		else {
			g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
		}
		
		g2.setColor(color);
		g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
		super.paintComponent(g);
	}
	
}
	