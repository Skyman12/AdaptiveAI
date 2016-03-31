package BasicAttacks;

import java.util.ArrayList;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Counter extends Attacks {
	public Counter(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Counter";
		attackDescription = "If hit with a basic attack this round, deal that damage back to the player.";
	
		damage = 0;
		speed = 0;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		theTarget.countered = true;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
	}

	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.ROGUE);
		parameters.add(AIAttackOptions.WARRIOR);
		parameters.add(AIAttackOptions.MAGE);
		parameters.add(AIAttackOptions.WARLOCK);
		chooseAttackTargetAI(parameters);
	}

}
