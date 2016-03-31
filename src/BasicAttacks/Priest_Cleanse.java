package BasicAttacks;

import java.util.ArrayList;
import java.util.Collections;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Priest_Cleanse extends Attacks {
	public Priest_Cleanse(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
	
		attackName = "Cleanse";
		attackDescription = "Chose a friendly, remove debuffs on that player";
	
		damage = 0;
		speed = 8;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		String result = doBeginningActions(theAttacker, target);
		if (!result.equals("Success")) return result;
		
		cleanse(theAttacker, theTarget, 0);
		
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
