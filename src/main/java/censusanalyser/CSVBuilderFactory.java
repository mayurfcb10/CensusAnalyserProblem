package censusanalyser;

import com.bridgelabz.censusanalyser.*;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}
}
