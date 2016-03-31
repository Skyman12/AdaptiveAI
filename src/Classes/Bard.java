package Classes;

import Abilities.Bard_BuffAuora;
import Abilities.Bard_Charm;
import Abilities.Bard_Cleanse;
import Abilities.Bard_MassConfusion;
import BasicAttacks.Bard_EnergyBoost;
import BasicAttacks.Bard_PowerCord;
import BasicAttacks.Bard_ShieldBoost;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.BardTemplate;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Bard extends Class {
	
	public Bard(Team whichTeam, PlayerType playerType) {
		super();
		name = "Bard";
		
		baseHealth = 200;
		currentHealth = baseHealth;
		baseShield = 85;
		currentShield = baseShield;
		shieldRechargeRate = 8;
		baseEnergy = 125;
		currentEnergy = baseEnergy;
		energyRechargeRate = 25;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Bard_PowerCord(this)); 
		basicAttackList.add(new Bard_ShieldBoost(this)); 
		basicAttackList.add(new Bard_EnergyBoost(this));
		
		abilitiesList.add(new Bard_Charm(this));
		abilitiesList.add(new Bard_Cleanse(this));
		abilitiesList.add(new Bard_BuffAuora(this));
		abilitiesList.add(new Bard_MassConfusion(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new BardTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
