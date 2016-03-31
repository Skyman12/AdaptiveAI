package Abilities;

import java.util.ArrayList;

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
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		theTarget.lastLife = true;
		
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
		chooseSupportTargetAI(parameters);
	}

}
