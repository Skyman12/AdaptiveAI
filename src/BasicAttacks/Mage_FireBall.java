package BasicAttacks;


import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_FireBall extends Attacks {
	public Mage_FireBall(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Fireball";
		attackDescription = "Choose an enemy, deal damage to that player.";
	
		damage = 15;
		speed = 2;
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
