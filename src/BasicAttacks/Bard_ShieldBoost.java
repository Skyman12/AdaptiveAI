package BasicAttacks;

import java.util.ArrayList;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_ShieldBoost extends Attacks {
	public Bard_ShieldBoost(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Shield Boost";
		attackDescription = "Choose a friendly, boost their shield by 30.";
	
		damage = 0;
		speed = 5;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 30, 0, theTarget);	
		
		return "Used " + attackName + " on " + theTarget.name + " -- Restored shield\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		players.add(theAttacker);
		for (Class p : players) {
			if (p.baseShield - p.currentShield > 30) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.LOWEST_SHIELD);
		chooseSupportTargetAI(parameters);
	}

}
