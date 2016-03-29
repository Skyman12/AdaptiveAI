package Abilities;


import java.util.ArrayList;
import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_LifeTransfer extends Attacks {
	public Priest_LifeTransfer(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Life Transfer";
		attackDescription = "Chose a friendly, heal for flat amount plus bonus (10-20).";
	
		damage = 0;
		speed = 4;
		cost = 60;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";

		Random random = new Random();
		int bonus = random.nextInt(10) + 10;
		
		restore(30 + bonus, 0, 0, theAttacker);
		
		return "Used " + attackName + " on " + target.name + " --- Healed for " + bonus + 30 + "\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		players.add(theAttacker);
		for (Class p : players) {
			if (p.baseHealth - p.currentHealth > 50) {
				return true;
			}
		}
		
		return false;
	}

}
