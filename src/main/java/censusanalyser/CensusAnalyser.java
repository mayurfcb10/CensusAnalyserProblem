package censusanalyser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.bridgelabz.censusanalyser.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CensusAnalyser {
	 List<IndiaCensusCSV> censusCSVList = null;
	 List<CSVStates> stateCSVList = null;
	public void welcomeMessage() {
		System.out.println("Welcome to the Census Analyser Problem");
	}

	// Reading Open CSV file to load india census data 
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			censusCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
		}  catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	// Reading Open CSV file to load state data 
	public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			 stateCSVList = CSVBuilderFactory.createCSVBuilder().getCSVFileList(reader, CSVStates.class);
	            return stateCSVList.size();
		}  catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	// Reading Common CSV file to load status and india census data 
	public int loadIndiaStateOrCensusDataUsingCommonsCSV(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			Iterator<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
					.withIgnoreHeaderCase()
					.withTrim()
					.parse(reader)
					.iterator();
			return this.getNumOfEntries(records);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.HEADER_DELIMETER_PROBLEM);
		}
	}

	private <E> int getNumOfEntries(Iterator<E> csvIterator) {
		Iterable<E> csvIterable = () -> csvIterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();

	}

	private void checkFileType(String csvFilePath) throws CensusAnalyserException {
		if (!(csvFilePath).matches(".+[.csv]"))
			throw new CensusAnalyserException("Incorrect file type",
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
	}

	/*Method returning JSON File Of States In Alphabetical Order*/
	public String SortedStateWiseCensusData() throws CensusAnalyserException {
		try (Writer writer = new FileWriter("D:\\CensusAnalyser\\src\\test\\resources\\IndiaStateCensusDataJson.json")) {
			if (censusCSVList == null || censusCSVList.size() == 0) {
				throw new CensusAnalyserException("No data", CensusAnalyserException.ExceptionType.NO_SUCH_DATA);
			}
			Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
			this.sort(censusComparator);
			String json = new Gson().toJson(censusCSVList);
			Gson gson = new GsonBuilder().create();
			gson.toJson(censusCSVList, writer);
			return json;

		} catch (RuntimeException | IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
		}
	}
	
	public void sort(Comparator<IndiaCensusCSV> censusComparator) {
        for (int firstcounter = 0; firstcounter < censusCSVList.size() - 1; firstcounter++) {
            for (int secondcounter = 0; secondcounter < censusCSVList.size() - firstcounter - 1; secondcounter++) {
                IndiaCensusCSV census1 = censusCSVList.get(secondcounter);
                IndiaCensusCSV census2 = censusCSVList.get(secondcounter + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    censusCSVList.set(secondcounter, census2);
                    censusCSVList.set(secondcounter + 1, census1);
                }
            }
        }
    }
}