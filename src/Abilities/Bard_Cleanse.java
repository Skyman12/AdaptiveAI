package Abilities;

import java.util.ArrayList;
import java.util.Collections;

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
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		cleanse(theAttacker, theTarget, 1);
		
		return "Used " + attackName + " on " + theTarget.name + "\n";
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
	
	@Override
	public void chooseAITarget() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		Collections.shuffle(players);
		for (Class p : players) {
			if (p.turnsStunned > 0 || p.turnsConfused > 0) {
				theTargets.add(p);
				return;
			}
		}	
	}

}
