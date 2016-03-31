package Classes;

import Abilities.Priest_HealingRing;
import Abilities.Priest_HolyShield;
import Abilities.Priest_LifeTransfer;
import Abilities.Priest_Savior;
import BasicAttacks.Priest_Cleanse;
import BasicAttacks.Priest_Heal;
import BasicAttacks.Priest_Smite;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.PriestTemplate;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Priest extends Class {
	
	public Priest(Team whichTeam, PlayerType playerType) {
		super();
		name = "Priest";
		
		baseHealth = 180;
		currentHealth = baseHealth;
		baseShield = 75;
		currentShield = baseShield;
		shieldRechargeRate = 8;
		baseEnergy = 150;
		currentEnergy = baseEnergy;
		energyRechargeRate = 30;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Priest_Smite(this)); 
		basicAttackList.add(new Priest_Heal(this)); 
		basicAttackList.add(new Priest_Cleanse(this));
		
		abilitiesList.add(new Priest_Savior(this));
		abilitiesList.add(new Priest_LifeTransfer(this));
		abilitiesList.add(new Priest_HolyShield(this));
		abilitiesList.add(new Priest_HealingRing(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new PriestTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
