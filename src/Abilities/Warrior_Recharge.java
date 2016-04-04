package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Recharge extends Attacks {
	public Warrior_Recharge(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Recharge";
		attackDescription = "+20 Shield";
	
		damage = 0;
		speed = 6;
		cost = 10;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		if (theAttacker.turnsStunned > 0) return "No attack";
		
		restore(0, 20, 0, theAttacker);
		
		effectivness = 20;
		
		return "Used " + attackName + "\n";
	}

	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public boolean getSoftCap() {
		if (theAttacker.baseEnergy - theAttacker.currentEnergy > 30 && theAttacker.baseShield - theAttacker.currentShield > 30) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		chooseTargetForAttack(theAttacker);
	}
}
