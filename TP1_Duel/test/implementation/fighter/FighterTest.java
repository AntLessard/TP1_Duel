package implementation.fighter;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import exceptions.fighter.IllegalStatValue;
import implementation.fighter.Fighter;
import implementation.fighter.FighterStat;

class FighterTest {
	
	final int ANY_SP = 10;
	final int ANY_DP = 10;
	final int ANY_IP = 10;
	final int ANY_CP = 10;
	
	private Fighter fighter = null;
	
	@BeforeEach
	public void initializeFighter() {
		fighter = new Fighter(ANY_SP, ANY_DP, ANY_IP, ANY_CP, null, null);//MS On doit utiliser des mocks pour les capacités.
	}
	
	@Test
	void WHEN_FighterIsCreated_THEN_FighterStatsAreInitialized() {
		//Assert
		assertEquals(ANY_SP, fighter.getSP());
	}
	
	@Test
	void GIVEN_Fighter_WHEN_FighterIsCreated_THEN_HPIsCalculated() {
		//Assert
		final int EXPECTED_HP = FighterStat.INITIAL_HP - (ANY_SP + ANY_DP + ANY_IP + ANY_CP);
		assertEquals(EXPECTED_HP, fighter.getHP());
	}
	
	@Rule
    public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void WHEN_FighterIsCreated_and_InitialStatValuesAreTooHigh_THEN_RaiseIllegalStatValueExceptionRightMessage() {
		//Arrange
		ex.expect(IllegalStatValue.class);
		ex.expectMessage(IllegalStatValue.TOTAL_STAT_VALUE_TOO_HIGH);
		//Act //Assert
		Assertions.assertThrows(IllegalStatValue.class, () -> {
			new Fighter(ANY_SP + (FighterStat.INITIAL_STAT_LIMIT), ANY_DP, ANY_IP, ANY_CP, null, null);
		    });	
	}
//MS Il manque des méthodes à tester.
}
