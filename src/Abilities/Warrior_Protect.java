package Abilities;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Protect extends Attacks {
	public Warrior_Protect(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Protect";
		attackDescription = "Chose a friendly, any attacks to that player will be redirected to this player";
	
		damage = 0;
		speed = 1;
		cost = 20;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 20.0);
		attackParameters.put(AIAttackOptions.LOWEST_SHIELD, 20.0);
		attackParameters.put(AIAttackOptions.PRIEST, 20.0);
		attackParameters.put(AIAttackOptions.BARD, 20.0);
		attackParameters.put(AIAttackOptions.MAGE, 20.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 10, 0, theAttacker);
		target.protectedBy = theAttacker;
		
		effectivness = 30;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
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
