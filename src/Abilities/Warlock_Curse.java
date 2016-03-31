package Abilities;

import java.util.ArrayList;
import java.util.Random;

import General.AIAttackOptions;
import General.AttackType;
import General.Attacks;
import General.Class;

public class Warlock_Curse extends Attacks {
	public Warlock_Curse(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Curse";
		attackDescription = "Chose an enemy, deal flat damage plus a bonus.";
	
		damage = 25;
		speed = 6;
		cost = 60;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		Random random = new Random();
		int bonus = random.nextInt(10) + 15;
		
		int damageDealt = dealDamage(theAttacker, theTarget, damage + bonus, critChance);

		return "Used " + attackName + " on " + theTarget.name + " -- It did " + damageDealt + " damage\n";
	}


	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public void chooseAITarget() {
		ArrayList<AIAttackOptions> parameters = new ArrayList<>();
		parameters.add(AIAttackOptions.PRIEST);
		parameters.add(AIAttackOptions.BARD);
		parameters.add(AIAttackOptions.MAGE);
		parameters.add(AIAttackOptions.RANDOM);
		chooseAttackTargetAI(parameters);
	}

}
