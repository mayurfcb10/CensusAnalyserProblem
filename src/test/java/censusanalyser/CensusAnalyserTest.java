package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
	CensusAnalyser censusAnalyser = new CensusAnalyser();
	@Test
	public void printWelcomeMessage() {
		censusAnalyser.welcomeMessage();
	}
}
