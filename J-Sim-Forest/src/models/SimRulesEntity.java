package models;

import enums.StateRules;
import enums.SimSpeed;

public class SimRulesEntity {
	private StateRules rule;
	private SimSpeed speed;
	private int simDuration;

	public StateRules getRule() {
		return rule;
	}

	public void setRule(StateRules rule) {
		this.rule = rule;
	}

	public SimSpeed getSpeed() {
		return speed;
	}

	public void setSpeed(SimSpeed speed) {
		this.speed = speed;
	}

	public int getSimDuration() {
		return simDuration;
	}

	public void setSimDuration(int simDuration) {
		this.simDuration = simDuration;
	}

}
