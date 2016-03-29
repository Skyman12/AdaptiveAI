package Abilities;


import java.util.ArrayList;
import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_MassConfusion extends Attacks {
	public Bard_MassConfusion(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Mass Confusion";
		attackDescription = "Targets everyone on the board. 50% chance they choose random targets for the rest of this turn.";
	
		damage = 5;
		speed = 5;
		cost = 60;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		Random random = new Random();
		int confused = random.nextInt(2);
		
		confuse(theAttacker, target, damage, confused);	

		return "Used " + attackName + " on " + target.name + " -- Confused for " + confused + " turns\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.addAll(getAlivePlayers(theAttacker));
	}
	
	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		for (Class p : players) {
			if (p.currentHealth + p.currentShield < damage) {
				return false;
			}
		}
		
		return true;
	}

}
