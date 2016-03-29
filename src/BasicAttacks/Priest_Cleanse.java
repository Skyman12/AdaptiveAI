package BasicAttacks;

import java.util.ArrayList;

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
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		cleanse(theAttacker, target, 0);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}
	
	@Override
	public int getSoftCap() {
		ArrayList<Class> players = getAliveAllies(theAttacker);
		for (Class p : players) {
			if ((p.turnsStunned > 0 || p.turnsConfused > 0) && p != theAttacker) {
				return 2;
			}
		}
		
		return 0;
	}

}
