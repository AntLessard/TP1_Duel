package implementation.duel;

import abstracts.capacity.ICapacity;
import abstracts.capacity.IHealing;
import abstracts.capacity.IOffense;
import abstracts.duel.IDuel;
import exceptions.duel.IllegalCapacityUsedInEngagement;
import implementation.capacity.HealPotion;
import implementation.fighter.Fighter;

public class Duel implements IDuel{

	public Fighter atk; //Attacker
	public Fighter def; //Defender
	public ICapacity engCap; //engagementCapacity
	
	public Duel(Fighter attacker, Fighter defender, ICapacity engagementCapacity) {//MS On doit s'assurer que le fighter a bien la capacité utilisée.
		this.atk = attacker;
		this.def = defender;
		validateEngagementCapacity(engagementCapacity);
		this.engCap = engagementCapacity;
		
	}
	
	private void validateEngagementCapacity(ICapacity engCap) {
		if(!(engCap instanceof IOffense)) throw new IllegalCapacityUsedInEngagement(IllegalCapacityUsedInEngagement.ILLEGAL_ENGAGEMENT_CAPACITY_TYPE);
	}
	
	public void acceptEngagement(ICapacity retalCap) { //retaliationCapacity
		if(retalCap instanceof IHealing) throw new IllegalCapacityUsedInEngagement(IllegalCapacityUsedInEngagement.ILLEGAL_RETALIATION_CAPACITY_TYPE);
		fight(this.engCap, retalCap);//MS On avait tout mis en place (IOffense, IHealing!)pour ne pas faire d'instanceof!!
	}
	
	public void denyEngagement() {
		rewardVictor(this.atk);
		punishDefeated(this.def, 0);
	}
	
	private void fight(ICapacity engCap, ICapacity retalCap) {
		int atkPower = engCap.calculatePower(engCap, this.atk.fighterStat);
		int defPower = retalCap.calculatePower(retalCap, this.def.fighterStat);
		int capDiff = Math.abs(atkPower - defPower);
		if(atkPower >= defPower) {
			rewardVictor(this.atk);
			punishDefeated(this.def, capDiff);
		}
		else {
			rewardVictor(this.def);
			punishDefeated(this.atk, capDiff);
		}
	}
	
	private void rewardVictor(Fighter victor) {
		victor.statUp();
		victor.addCapacity(new HealPotion(20));	//Placeholder capacity //MS Le gagnant a toujours la même capacité?
	}
	
	private void punishDefeated(Fighter defeated, int capDiff) {//MS Pour être plus clean, on aurait dû mettre deux méthodes avec un nombre d'arguments différents.
		defeated.statDown();
		defeated.fighterStat.hp = defeated.fighterStat.hp - capDiff;//MS Tell don't ask: ce n'est pas au duel de changer les statistiques, il doit indiquer de combien on modifie et les stats vont faire le travail.
	}
}
