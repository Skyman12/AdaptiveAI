package Classes;

import Abilities.Rogue_Backstab;
import Abilities.Rogue_Flury;
import Abilities.Rogue_Sneak;
import Abilities.Rogue_Stun;
import BasicAttacks.Counter;
import BasicAttacks.Rogue_Stab;
import BasicAttacks.Rogue_TripleStab;
import General.Class;
import General.PlayerType;
import General.Team;
import WeightTemplates.RogueTemplate;
import WeightTemplates.TemplateType;
import WeightTemplates.WarriorTemplate;

public class Rogue extends Class {
	
	public Rogue(Team whichTeam, PlayerType playerType) {
		super();
		name = "Rogue";
		
		baseHealth = 240;
		currentHealth = baseHealth;
		baseShield = 100;
		currentShield = baseShield;
		shieldRechargeRate = 10;
		baseEnergy = 100;
		currentEnergy = baseEnergy;
		energyRechargeRate = 25;
		
		bonusCritChance = 0;
		
		basicAttackList.add(new Rogue_Stab(this)); 
		basicAttackList.add(new Rogue_TripleStab(this)); 
		basicAttackList.add(new Counter(this));
		
		abilitiesList.add(new Rogue_Stun(this));
		abilitiesList.add(new Rogue_Backstab(this));
		abilitiesList.add(new Rogue_Sneak(this));
		abilitiesList.add(new Rogue_Flury(this));
		
		team = whichTeam;
		this.playerType = playerType;
		
		weightTemplate = new RogueTemplate();
		
		assignWeights(TemplateType.BALANCED);
	}

}
