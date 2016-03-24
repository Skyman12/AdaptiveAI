package BasicAttacks;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_FlameTunnel extends Attacks {
	public Mage_FlameTunnel(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Flame Tunnel";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 30;
		speed = 7;
		critChance = 0;
		numOfTargets = 1;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		dealDamage(theAttacker, target, damage, critChance);	
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		theTargets.add(target);
	}

}
