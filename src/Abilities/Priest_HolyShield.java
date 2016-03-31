package Abilities;


import java.util.ArrayList;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_HolyShield extends Attacks {
	public Priest_HolyShield(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Holy Shield";
		attackDescription = "Chose a friendly, Health +40, Shield +40";
	
		damage = 0;
		speed = 0;
		cost = 100;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(40, 40, 0, theAttacker);
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
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
			if (p.baseHealth - p.currentHealth > 40 && p.baseShield - p.currentShield > 40) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.LOWEST_HEALTH);
		parameters.add(AIAttackOptions.LOWEST_SHIELD);
		chooseSupportTargetAI(parameters);
	}

}
