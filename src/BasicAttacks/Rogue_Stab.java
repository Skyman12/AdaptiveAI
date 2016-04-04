package BasicAttacks;

import java.util.ArrayList;
import java.util.HashMap;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Rogue_Stab extends Attacks {
	public Rogue_Stab(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Stab";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 20;
		speed = 2;
		critChance = 35;
		numOfTargets = 1;
		
		attackParameters.put(AIAttackOptions.LOWEST_HEALTH, 100.0);
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);	
		
		return "Used " + attackName + " on " + theTarget.name + " -- Dealt " + damageDealt + " damage\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	public boolean getSoftCap() { 
		if (theAttacker.bonusCritChanceTurns > 0) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void chooseAITarget() {
		chooseAttackTargetAI(attackParameters);
	}

}
