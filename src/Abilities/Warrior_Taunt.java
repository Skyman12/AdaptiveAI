package Abilities;

import java.util.ArrayList;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Taunt extends Attacks {

	public Warrior_Taunt(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Taunt";
		attackDescription = "Choose an enemy, that enemy must attack this player this round.";
		
		damage = 0;
		speed = 1;
		cost = 20;
		critChance = 0;
		numOfTargets = 1;
	}

	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 10, 0, theAttacker);
		theTarget.forcedTarget = theAttacker;
				
		return "Used " + attackName + " on " + theTarget.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.WARLOCK);
		parameters.add(AIAttackOptions.ROGUE);
		parameters.add(AIAttackOptions.MAGE);
		chooseAttackTargetAI(parameters);
	}

}
