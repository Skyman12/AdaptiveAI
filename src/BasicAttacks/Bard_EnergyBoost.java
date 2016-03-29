package BasicAttacks;

import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_EnergyBoost extends Attacks {
	public Bard_EnergyBoost(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Energy Boost";
		attackDescription = "Choose a friendly, boost their energy by 30.";
	
		damage = 0;
		speed = 5;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		restore(0, 0, 30, target);	
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
