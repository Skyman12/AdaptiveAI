package Abilities;

import java.util.ArrayList;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Bard_Cleanse extends Attacks {
	public Bard_Cleanse(Class attacker) {
		super(attacker);
		attackType = AttackType.ABILITIES;
	
		attackName = "Cleanse";
		attackDescription = "Choose a friendly, removes any stuns / confusion and prevents them from being stunned the rest of the turn.";
	
		damage = 0;
		speed = 3;
		cost = 50;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		cleanse(theAttacker, target, 1);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

	@Override
	public boolean getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		for (Class p : players) {
			if ((p.turnsStunned > 0 || p.turnsConfused > 0) && p != theAttacker) {
				return true;
			}
		}
		
		return false;
	}

}
