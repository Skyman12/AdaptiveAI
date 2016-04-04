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
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		Random random = new Random();
		int confused = random.nextInt(2);
		
		confuse(theAttacker, theTarget, damage, confused);	
		
		effectivness = confused * 30;

		return "Used " + attackName + " on " + theTarget.name + " -- Confused for " + confused + " turns\n";
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

	@Override
	public void chooseAITarget() {
		chooseTargetForAttack(theAttacker);
	}

}
