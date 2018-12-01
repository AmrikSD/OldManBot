package lib;

import java.util.Random;

public class roll {

	Random r = new Random();

	int sides;
	int rolls;
	int[] individualRolls;

	public roll() {
		this.rolls = 1;
		this.sides = 6;

		this.individualRolls = new int[rolls];
		individualRolls[0] = r.nextInt(sides) + 1;
	}

	public roll(int sides) {
		this.rolls = 1;
		this.sides = sides;

		this.individualRolls = new int[rolls];
		individualRolls[0] = r.nextInt(sides) + 1;

	}

	public roll(int rolls, int sides) {
		this.rolls = rolls;
		this.sides = sides;
		this.individualRolls = new int[rolls];

		for (int i = 0; i < individualRolls.length; i++) {
			individualRolls[i] = r.nextInt(sides) + 1;
		}

	}

	public void setRolls(int rolls) {
		this.rolls = rolls;
	}

	public int getNumberOfRolls() {
		return this.rolls;
	}

	public void setSides(int sides) {
		this.sides = sides;
	}

	public int getNumberOfSides() {
		return this.sides;
	}

	public int[] getIndividualRolls() {
		int[] IndividualRolls = new int[this.rolls];

		for (int i = 0; i < IndividualRolls.length; i++) {
			IndividualRolls[i] = r.nextInt(sides) + 1;
		}
		return IndividualRolls;

	}

	public int getSum() {
		int sum = 0;
		for (int i = 0; i < individualRolls.length; i++) {
			sum += individualRolls[i];
		}
		return sum;
	}
	
	@Override
	public String toString() {
		String temp = rolls + "d" + sides + " = " + getSum();
		return temp;
	}
}
