package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;

public class CensusAnalyserTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String CORRECT_PATH_WRONG_CSV_FILE = "./src/test/resources/IndiaStateCensusData.txt";
	private static final String WRONG_DELIMITER_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String WRONG_HEADER_PATH = "./src/test/resources/StateCensusDataWrongHeader.csv";
	private static final String INDIA_STATECODE_CSV_FILE_PATH = "./src/test/resources/IndianStateCode.csv";
	private static final String WRONG_STATECODECSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String CORRECT_PATH_WRONG_STATECODE_CSV_FILE = "./src/main/resources/IndianStateCode.txt";
	private static final String WRONG_STATECODE_CSV_DELIMITER_PATH = "./src/test/resources/StateCodeData.csv";
	private static final String WRONG_STATECODE_HEADER_PATH = "./src/test/resources/StateCodeDataWrongHeader.csv";

	@Test
	public void printWelcomeMessage() {
		CensusAnalyser censusAnalyser = new CensusAnalyser();
		censusAnalyser.welcomeMessage();
	}

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectPath_WrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(CORRECT_PATH_WRONG_CSV_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectPath_RemovedDelimeter_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithCorrectPath_WrongHeader_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_HEADER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianStateCodeFileReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_STATECODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_STATECODECSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCode_WithCorrectPath_WrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(CORRECT_PATH_WRONG_STATECODE_CSV_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCode_WithCorrectPath_RemovedDelimeter_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_STATECODE_CSV_DELIMITER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndianStateCodeData_WithCorrectPath_WrongHeader_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_STATECODE_HEADER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenCensusFileWithCommonsCSVReturnsCorrectNoOfEntries() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {

		}
	}

	@Test
	public void givenStateCodeFileWithCommonsCSVReturnsCorrectNoOfEntries() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(INDIA_STATECODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfRecords);
		} catch (CensusAnalyserException e) {

		}
	}

	@Test
	public void givenStateCodeFileWithWrongHeaderReturnsExceptionCommonsCSV() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(WRONG_STATECODECSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusDataWithWrongDelimiterShouldThrowExceptionCommonsCSV() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateOrCensusDataUsingCommonsCSV(WRONG_DELIMITER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM, e.type);
		}
	}

	@Test
	public void giveIndianCensusDataWhenSortOnStateShouldReturnSortedResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortCensusData = censusAnalyser.SortedStateWiseCensusData();
			IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals("Andhra Pradesh", indiaCensusCSV[0].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void giveIndianCensusDataWhenSortOnStateShouldReturnExpectedSortedResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortCensusData = censusAnalyser.SortedStateWiseCensusData();
			IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals("West Bengal", indiaCensusCSV[indiaCensusCSV.length - 1].state);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void giveIndianStateData_WhenSortOnStateCode_ShouldReturnSortedResult() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndianStateData(INDIA_STATECODE_CSV_FILE_PATH);
			String sortCensusData = censusAnalyser.StateCodeWiseSortedCensusData();
			CSVStates[] indiaStateCSV = new Gson().fromJson(sortCensusData, CSVStates[].class);
			Assert.assertEquals("AD", indiaStateCSV[0].stateCode);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void giveIndianStateDataWhenSortedBasedOnPopulationShouldReturnMaximumPopulatedState() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
			IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(199812341, indiaCensusCSV[0].population);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void giveIndianStateData_WhenSortedBasedOnPopulation_ShouldReturnMinimumPopulatedState() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			String sortCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
			IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
			Assert.assertEquals(607688, indiaCensusCSV[indiaCensusCSV.length - 1].population);
		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		}
	}
	
    @Test
    public void giveIndianStateData_WhenSortOnPopulationDensity_ShouldReturnMaximumDensityState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.PopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, indiaCensusCSV[0].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void giveIndianStateData_WhenSortOnPopulationDensity_ShouldReturnMinimumDensityState() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortCensusData = censusAnalyser.PopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] indiaCensusCSV = new Gson().fromJson(sortCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(50, indiaCensusCSV[indiaCensusCSV.length - 1].densityPerSqKm);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
}
