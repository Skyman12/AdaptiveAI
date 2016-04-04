package Abilities;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Backstab extends Attacks {
	public Rogue_Backstab(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Backstab";
		attackDescription = "Chose an enemy, deal damage with high crit chance.";
	
		damage = 60;
		speed = 1;
		cost = 50;
		critChance = 50;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.PRIEST, 25.0);
		attackParameters.put(AIAttackOptions.BARD, 25.0);
		attackParameters.put(AIAttackOptions.MAGE, 25.0);
		attackParameters.put(AIAttackOptions.RANDOM, 25.0);
	}

	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);
		
		effectivness = 60;
	
		return "Used " + attackName + " on " + theTarget.name + " -- Dealt " + damageDealt + " damage\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		chooseAttackTargetAI(attackParameters);
	}
}
