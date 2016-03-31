package Classes;

import Abilities.Warlock_BloodPact;
import Abilities.Warlock_Confusion;
import Abilities.Warlock_Curse;
import Abilities.Warlock_LifeDrain;
import BasicAttacks.Warlock_Corruption;
import BasicAttacks.Warlock_LifeTap;
import BasicAttacks.Warlock_MindZap;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Warlock extends Class {
	
	public Warlock(Team whichTeam, PlayerType playerType) {
		super();
		name = "Warlock";
		
		baseHealth = 180;
		currentHealth = baseHealth;
		baseShield = 85;
		currentShield = baseShield;
		shieldRechargeRate = 5;
		baseEnergy = 125;
		currentEnergy = baseEnergy;
		energyRechargeRate = 35;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Warlock_LifeTap(this)); 
		basicAttackList.add(new Warlock_Corruption(this)); 
		basicAttackList.add(new Warlock_MindZap(this));
		
		abilitiesList.add(new Warlock_LifeDrain(this));
		abilitiesList.add(new Warlock_Curse(this));
		abilitiesList.add(new Warlock_Confusion(this));
		abilitiesList.add(new Warlock_BloodPact(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new WarriorTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
