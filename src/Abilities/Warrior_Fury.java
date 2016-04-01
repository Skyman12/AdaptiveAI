package Abilities;

import java.util.ArrayList;
import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Warrior_Fury extends Attacks {

	public Warrior_Fury(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		attackName = "Fury";
		attackDescription = "Randomly choose 2 enemies, deal damage to them";
		
		damage = 20;
		speed = 4;
		cost = 25;
		critChance = 0;
		numOfTargets = 0;
		 
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
		ArrayList<Class> alive= getAliveOpponents(theAttacker);
		if (alive.size() > 0) {
			Random random = new Random();
			for (int i = 0; i < 2; i++) {
				if (alive.size() > 0) {
					int choice = random.nextInt(alive.size());
					theTargets.add(alive.get(choice));
					alive.remove(choice);			
				}
			}
		}
	}

	@Override
	public void chooseAITarget() {
		chooseTargetForAttack(theAttacker);
	}
	
	
	
	

}
