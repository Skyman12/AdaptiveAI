package BasicAttacks;

import java.util.Random;

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
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		restore(15, 0, 0, target);	
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
