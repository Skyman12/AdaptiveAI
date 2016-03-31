package Abilities;

import java.util.ArrayList;

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
		cost = 40;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 10, 0, theAttacker);
		target.protectedBy = theAttacker;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.LOWEST_HEALTH);
		parameters.add(AIAttackOptions.LOWEST_SHIELD);
		parameters.add(AIAttackOptions.PRIEST);
		parameters.add(AIAttackOptions.BARD);
		parameters.add(AIAttackOptions.MAGE);
		chooseSupportTargetAI(parameters);
	}

}
