package Abilities;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_Savior extends Attacks {
	public Priest_Savior(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Savior";
		attackDescription = "Choose a friendly, this turn, that player cannot be reduced below 1 health.";
	
		damage = 0;
		speed = 0;
		cost = 80;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 100.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		theTarget.lastLife = true;
		
		effectivness = 40;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
	}
	
	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		players.add(theAttacker);
		for (Class p : players) {
			if (p.currentHealth < 50 && p.currentShield < 30) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		chooseSupportTargetAI(attackParameters);
	}

}
