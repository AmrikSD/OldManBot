package com.potatoblood.oldman.commands;

import java.util.Random;

public class DiceRoll {

	private int sum;
	private int rolls;
	private int faces;
	private int[] results;

	public DiceRoll() {
		this.rolls = 1;
		this.faces = 20;
		this.results = new int[1];

		Random rand = new Random();
		this.sum = rand.nextInt(20) + 1;
		this.results[0] = this.sum;

	}

	public DiceRoll(int noOfRolls, int noOfFaces) {
		this.rolls = noOfRolls;
		this.faces = noOfFaces;

		if (noOfRolls < 1)
			this.rolls = 1;
		if (noOfFaces < 1)
			this.faces = 20;

		this.results = new int[noOfRolls];

		int currRoll;
		Random rand = new Random();
		for (int i = 0; i < noOfRolls; i++) {
			currRoll = (rand.nextInt(noOfFaces) + 1);
			this.results[i] = currRoll;
			this.sum += currRoll;
		}

	}

	public int getRolls() {
		return rolls;

	}

	public int getFaces() {
		return faces;
	}

	public int getSum() {
		return sum;
	}

	/**
	 * Returns an array of integers of size n such that each index n is equal to the (n+1)th roll.
	 * 
	 */
	public int[] getResults() {
		return results;
	}

}
