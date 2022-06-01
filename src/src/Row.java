/**
 * 
 */
package src;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;


/**
 * @author Ahmet
 *
 */
public class Row extends JPanel implements NodeListener,java.io.Serializable {
	private static final long serialVersionUID = 1L; 
	
	public int length;
	private int exActive;
	private int currActive=0;
	
	public CoolNode[] buttonArray;
	public Guess guess;
	public ArrayList<String> CorrectFormula;
	private HashMap<Character, Integer> hmap;
	public Row(int length,ArrayList<String>CorrectFormula) {
		
		this.length = length;
		this.CorrectFormula = CorrectFormula;
		
		setBackground(new Color(31,41,55));
		
		guess = new Guess(length,CorrectFormula);
		buttonArray = new CoolNode[length];
		
		setLayout(new FlowLayout(FlowLayout.LEADING));
		
		hmap = new HashMap<Character, Integer>(); 
		for(char c: guess.CorrectFormulaChar) {
			if(!hmap.containsKey(c)) {
				hmap.put(c,1);
			}else {
				hmap.replace(c, hmap.get(c)+1);
			}
		}
		
		for(int i=0; i<length;i++) {
			
			buttonArray[i] = new CoolNode(i);
			buttonArray[i].addListener(this);
			add(buttonArray[i]);
		}
	}
	public void setStates() {
		for(int i=0;i<length;i++) {
			buttonArray[i].setText(Character.toString(guess.guessFormulaChar[i]));
			if(guess.CorrectFormulaChar[i] == guess.guessFormulaChar[i]) {
				buttonArray[i].state =1;
				hmap.replace(guess.guessFormulaChar[i], hmap.get(guess.guessFormulaChar[i])-1);
			}
		}
		
		for(int i=0;i<length;i++) {
			if(buttonArray[i].state !=1) {
				if(hmap.get(guess.guessFormulaChar[i])==null||hmap.get(guess.guessFormulaChar[i])==0) {
					buttonArray[i].state =3;
				}else {
					hmap.replace(guess.guessFormulaChar[i], hmap.get(guess.guessFormulaChar[i])-1);
					buttonArray[i].state =2;
				}
			}
		}
	}
	public boolean isTrue() {
		for(CoolNode cn : buttonArray) {
			if(cn.state!=1) {
				return false;
			}
		}
		return true;
	}
	public boolean isActivated() {
		for(CoolNode cn:buttonArray) {
			if(cn.active) {
				return true;
			}
		}
		return false;
	}
	public CoolNode getActive() {
		for(int i=0;i<length;i++) {
			if(buttonArray[i].active) {
				return buttonArray[i];
			}
		}
		return null;
	}
	public int getActiveIndex() {
		for(int i=0;i<length;i++) {
			if(buttonArray[i].active) {
				return i;
			}
		}
		return -1;
	}
	public void clear() {
		for(CoolNode cn:buttonArray) {
			cn.active = false;
		}
	}
	public void nodeActivated(int index) {
		exActive = currActive;
		currActive = index;
		clear();
		buttonArray[index].active =true;
		update(exActive,currActive);
	}
	public void nodesDisabled() {
		for(CoolNode cn:buttonArray) {
			cn.setEnabled(false);
			cn.enabled = false;
		}
	}
	public void nodesEnabled() {
		for(CoolNode cn:buttonArray) {
			cn.setEnabled(true);
			cn.enabled = true;
		}
	}
	public void update() {
		
		for(int i =0;i<length;i++) {
			buttonArray[i].updateAppereance();
		}
	}
	
	public void update(int ex,int n) {//overload
		
		buttonArray[ex].updateAppereance();
		buttonArray[n].updateAppereance();
	}

}
