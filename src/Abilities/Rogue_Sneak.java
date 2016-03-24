package Abilities;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Sneak extends Attacks {
	public Rogue_Sneak(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Sneak";
		attackDescription = "Cannot be damaged this turn. Next turn, crit chance increased by 50%. Gain a +10 shield bonus.";
	
		damage = 0;
		speed = 0;
		cost = 75;
		critChance = 0;
		numOfTargets = 0;
	}

	@Override
	protected String attack(Class target) {
		if (theAttacker.turnsStunned > 0) return "No attack";
		
		restore (0, 10, 0, theAttacker);
		theAttacker.bonusCritChance += 50;
		theAttacker.bonusCritChanceTurns = 2;
		
		return "Used " + attackName + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
}
