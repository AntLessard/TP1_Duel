package implementation.capacity;

import abstracts.capacity.ICapacity;
import abstracts.capacity.IOffense;
import implementation.fighter.FighterStat;

public class FireballSpell extends Capacity implements IOffense{

	public FireballSpell(int charc) {
		super(charc);
	}
	
	public int calculatePower(ICapacity capacity, FighterStat fighterStat) {
		int power = (fighterStat.ip * this.getCharc() / 100) * 3;//MS Les chiffres magiques doivent être dans des constantes.
		return power;
	}


}
