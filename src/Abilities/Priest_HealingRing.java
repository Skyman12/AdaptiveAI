package Abilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_HealingRing extends Attacks {
	public Priest_HealingRing(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Healing Ring";
		attackDescription = "Randomly choose between 1 - 4 allies to heal for +20";
	
		damage = 0;
		speed = 4;
		cost = 60;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(20, 0, 0, theTarget);
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		ArrayList<Class> alive= getAliveAllies(theAttacker);
		alive.add(theAttacker);
		if (alive.size() > 0) {
			Random random = new Random();
			int targets = random.nextInt(4) + 1;
			for (int i = 0; i < targets; i++) {
				if (alive.size() > 0) {
					int choice = random.nextInt(alive.size());
					theTargets.add(alive.get(choice));
					alive.remove(choice);			
				}
			}
		}
	}
	
	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		for (Class p : players) {
			if (p.baseHealth - p.currentHealth < 20) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void chooseAITarget() {
		chooseTargetForAttack(theAttacker);
	}

}
