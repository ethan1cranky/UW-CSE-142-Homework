// Critter class extension for a critter called a 'Bear'
// It is represented by a '/' or a '\'
// Author: Rukmal Weerawarana

import java.awt.*;

public class Bear extends Critter {
	// private variables to measure the count of the instances and polarity of the bear
	private int count;
	private boolean polar;
	
	// constructor for the critter bear
	public Bear(boolean polar) {
		this.count = 0;
		this.polar = polar;
	}
	
	// returns the color of the bear depending on the boolean polar (white if true, black if false)
	public Color getColor() {
		if (this.polar) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}
	
	// returns the string value of the bear
	public String toString() {
		this.count = this.count + 1;
		if (count % 2 == 1) {
			return "/";
		} else {
			return "\\";
		}
	}
	
	// returns the move to be made by the bear
	public Action getMove(CritterInfo info) {
		System.out.println(info.getInfectCount() + "  Bear  ");
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.EMPTY) {
			return Action.HOP;
		} else {
			return Action.LEFT;
		}
	}
}