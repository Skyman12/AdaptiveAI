package Classes;

import Abilities.Warrior_Fury;
import Abilities.Warrior_Protect;
import Abilities.Warrior_Recharge;
import Abilities.Warrior_Taunt;
import BasicAttacks.Counter;
import BasicAttacks.Warrior_HeavySlash;
import BasicAttacks.Warrior_QuickSlash;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Warrior extends Class {
	
	public Warrior(Team whichTeam, PlayerType playerType) {
		super();
		name = "Warrior";
		
		baseHealth = 300;
		currentHealth = baseHealth;
		baseShield = 150;
		currentShield = baseShield;
		shieldRechargeRate = 15;
		baseEnergy = 75;
		currentEnergy = baseEnergy;
		energyRechargeRate = 5;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Warrior_QuickSlash(this)); 
		basicAttackList.add(new Warrior_HeavySlash(this)); 
		basicAttackList.add(new Counter(this));
		
		abilitiesList.add(new Warrior_Taunt(this));
		abilitiesList.add(new Warrior_Protect(this));
		abilitiesList.add(new Warrior_Recharge(this));
		abilitiesList.add(new Warrior_Fury(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new WarriorTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
