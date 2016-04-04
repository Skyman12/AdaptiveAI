package Abilities;

import java.util.ArrayList;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_TidalWave extends Attacks {
	public Mage_TidalWave(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
		
		attackName = "Tidal Wave";
		attackDescription = "Deals damage to the entire enemy team.";
	
		damage = 30;
		speed = 6;
		cost = 60;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;

		int damageDealt = dealDamage(theAttacker, theTarget, damage, critChance);
		
		effectivness = 60;
		
		return "Used " + attackName + " on " + theTarget.name + " -- Dealt " + damageDealt + " damage\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		ArrayList<Class> alive= getAliveOpponents(theAttacker);
		
		for (Class player : alive) {
			theTargets.add(player);
		}
	}

	@Override
	public void chooseAITarget() {
		ArrayList<Class> alive= getAliveOpponents(theAttacker);
		
		for (Class player : alive) {
			theTargets.add(player);
		}	
	}

}
