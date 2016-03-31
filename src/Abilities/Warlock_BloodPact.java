package Abilities;

import java.util.ArrayList;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_BloodPact extends Attacks {
	public Warlock_BloodPact(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Blood Pact";
		attackDescription = "Deal damage to all players, restore 1 times the amount of damage dealt.";
	
		damage = 10;
		speed = 8;
		cost = 45;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);
		
		restore(damageDealt, 0, 0, theAttacker);

		return "Used " + attackName + " on " + theTarget.name + " -- Dealt " + damageDealt + " damage\n";
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
