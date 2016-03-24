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
		if(!doBeginningActions(theAttacker, target)) return "No attack";

		dealDamage(theAttacker, target, damage, critChance);
		
		return "Used " + attackName + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		ArrayList<Class> alive= getAliveOpponents(theAttacker);
		
		for (Class player : alive) {
			theTargets.add(player);
		}
	}

}
