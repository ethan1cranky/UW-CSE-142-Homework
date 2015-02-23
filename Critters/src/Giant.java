// Critter class extension for a critter called a 'Giant'
// It is represented by 'fee', 'fie', 'foe', or 'fum'
// Author: Rukmal Weerawarana

import java.awt.*;

public class Giant extends Critter {
	// private variables to measure the count of the instances and the previous string of the giant
	// and the different possible String values of the giant
	private int count;
	private String previousName;
	private final String[] giantNames = {"fee", "fie", "foe", "fum"};
	private int giantIndex;
	
	// constructor of the critter giant
	public Giant() {
		this.count = 0;
		this.giantIndex = 0;
	}
	
	// returns the color of the giant
	public Color getColor() {
		return Color.GRAY;
	}
	
	// returns the string value of the giant
	public String toString() {
		this.count = this.count + 1;
		if ((this.count - 1) % 6 == 0) {
			if (this.giantIndex == 4) {
				this.giantIndex = 0;
			}
			this.giantIndex = this.giantIndex + 1;
			return giantNames[giantIndex - 1].toString();
		} else {
			this.previousName = giantNames[giantIndex - 1];
		}
		return this.previousName;
	}
	
	// returns the move to be made by the giant
	public Action getMove(CritterInfo info) {
		if (info.getFront() == Neighbor.OTHER) {
			return Action.INFECT;
		} else if (info.getFront() != Neighbor.EMPTY) {
			return Action.RIGHT;
		} else {
			return Action.HOP;
		}
	}
}
