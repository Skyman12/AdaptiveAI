package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_EnergyBoost extends Attacks {
	public Bard_EnergyBoost(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Energy Boost";
		attackDescription = "Choose a friendly, boost their energy by 20.";
	
		damage = 0;
		speed = 5;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.LOWEST_ENERGY, 100.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 0, 10, theTarget);	
		
		effectivness = 10;
		
		return "Used " + attackName + " on " + theTarget.name + " -- Restored energy\n";
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
			if (p.baseEnergy - p.currentEnergy > 30) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void chooseAITarget() {
		chooseSupportTargetAI(attackParameters);
	}

}
