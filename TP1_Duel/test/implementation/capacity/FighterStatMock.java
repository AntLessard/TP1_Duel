package implementation.capacity;

import implementation.fighter.FighterStat;

public class FighterStatMock extends FighterStat {//MS Le mock doit implémenter l'interface.
	public int sp;
	public int dp;
	public int ip;
	public int cp;
	
	public FighterStatMock(int sp, int dp, int ip, int cp) {
		super(sp, dp, ip, cp);
	}
}
