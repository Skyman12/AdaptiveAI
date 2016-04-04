package Abilities;

import java.util.ArrayList;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_BuffAuora extends Attacks {
	public Bard_BuffAuora(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
	
		attackName = "Buff Auora";
		attackDescription = "Buffs the entire team. +20 Shield, +20 Energy";
	
		damage = 0;
		speed = 0;
		cost = 75;
		critChance = 0;
		numOfTargets = 0;
		
		
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		restore(0, 20, 20, theTarget);
		
		effectivness = 30;
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.addAll(getAliveAllies(theAttacker));
	}

	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		for (Class p : players) {
			if (p.baseShield - p.currentEnergy < 20  || p.baseEnergy - p.baseEnergy < 20) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void chooseAITarget() {
		theTargets.addAll(getAliveAllies(theAttacker));
	}

}
