package Classes;

import Abilities.Mage_Freeze;
import Abilities.Mage_IceLance;
import Abilities.Mage_Meditate;
import Abilities.Mage_TidalWave;
import BasicAttacks.Mage_FireBall;
import BasicAttacks.Mage_FlameTunnel;
import BasicAttacks.Mage_ThunderStrike;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.MageTemplate;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Mage extends Class {
	
	public Mage(Team whichTeam, PlayerType playerType) {
		super();
		name = "Mage";
		
		baseHealth = 160;
		currentHealth = baseHealth;
		baseShield = 75;
		currentShield = baseShield;
		shieldRechargeRate = 5;
		baseEnergy = 150;
		currentEnergy = baseEnergy;
		energyRechargeRate = 40;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Mage_FireBall(this)); 
		basicAttackList.add(new Mage_FlameTunnel(this)); 
		basicAttackList.add(new Mage_ThunderStrike(this));
		
		abilitiesList.add(new Mage_IceLance(this));
		abilitiesList.add(new Mage_TidalWave(this));
		abilitiesList.add(new Mage_Freeze(this));
		abilitiesList.add(new Mage_Meditate(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new MageTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
