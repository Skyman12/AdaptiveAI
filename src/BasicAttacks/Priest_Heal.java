package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_Heal extends Attacks {
	public Priest_Heal(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Heal";
		attackDescription = "Choose a friendly, boost their health by 15";
	
		damage = 0;
		speed = 4;
		critChance = 0;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 100.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(15, 0, 0, theTarget);	
		
		effectivness = 25;
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
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
			if (p.baseHealth - p.currentHealth > 15) {
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
