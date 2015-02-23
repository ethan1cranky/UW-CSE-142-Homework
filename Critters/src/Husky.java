// Critter class extension for a critter called a 'Husky'
// Represented by a 'o'
// Author: Rukmal Weerawarana

import java.awt.*;

public class Husky extends Critter {
	// constructor of the critter Husky
	public Husky() {
		
	}
	
	// returns the string value of the husky
	public String toString() {
		return "o";
	}
	
	// returns the color of the husky
	public Color getColor() {
		return Color.BLACK;
	}
	
	// returns the move to be made by the husky
	public Action getMove(CritterInfo info) {
		System.out.println(info.getInfectCount() + "  Husky");
		double number = Math.random();
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() == Neighbor.WALL || info.getFront() == Neighbor.SAME) {
			return Action.LEFT;
		} else {
			return Action.HOP;
		}
	}
}