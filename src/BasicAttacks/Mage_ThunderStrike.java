package BasicAttacks;

import java.util.ArrayList;
import java.util.Random;

import General.AttackType;
import General.Attacks;
import General.Class;

public class Mage_ThunderStrike extends Attacks {
	public Mage_ThunderStrike(Class attacker) {
		super(attacker);
		attackType = AttackType.BASIC_ATTACK;
		
		attackName = "Thunder Strike";
		attackDescription = "Randomly hit 1-3 of the enemies.";
	
		damage = 10;
		speed = 6;
		critChance = 0;
		numOfTargets = 0;
	}
	
	@Override
	protected String attack(Class target) {
		if(!doBeginningActions(theAttacker, target)) return "No attack";
		
		dealDamage(theAttacker, target, damage, critChance);
		
		return "Used " + attackName + " on " + target.name + "\n";
	}
	
	@Override
	public void chooseTargetForAttack(Class target) {
		ArrayList<Class> alive= getAliveOpponents(theAttacker);
		if (alive.size() > 0) {
			Random random = new Random();
			int targets = random.nextInt(3) + 1;
			for (int i = 0; i < targets; i++) {
				if (alive.size() > 0) {
					int choice = random.nextInt(alive.size());
					theTargets.add(alive.get(choice));
					alive.remove(choice);			
				}
			}
		}
	}

}
