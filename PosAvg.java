import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PosAvg {
	String STID_COLUMN = "STID";    // Constant variable, Station IDs follow this line.
	String MesoStation;	        	// Station we are interested in.
	String finalstring = "YUKO";    // Final station in dataset.
	int stationIndex = 0;
	String nPlusOne;
	String nMinusOne;
	String nPlusTwo;
	String nMinusTwo;
	
	String[] stations = new String[5];  // array of all the station strings.
	
	PosAvg(String MesoStation) {
		this.MesoStation = MesoStation;
	
		readFile();  // read file function 
		indexOfStation();		
		// Only calculate n if it has an n+/-1 and n+/-2
		if (stationIndex >= 3 && stationIndex <= 118) {
			calculateNs(stationIndex); // Calculate n-1,n+1,n-2,n+2 for all except first and last 2 stations
		}
		
		if (stationIndex >= 3 && stationIndex <= 118) {
			toString();
		}
		
	}
	//try to read file method
	public void readFile() {
		String filename = "Mesonet.txt";
		// Try to read file. Throws IOException if fails.
		try {
			read(filename); // IMPLEMENT READ
		}
		catch(IOException e) {
			System.out.println("Error reading from file! \n");
			e.printStackTrace();
		}
	}
	//read the actual file into an array method
	public void read(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String strg = null;
		String firstElement = "AAAA";  // String used to find "STID" in text
		String index = "AAAA";         // String that will be the station ID
		int indexCount = 0;            // Counter for array.
		String[] strgArray = new String[5]; //readLine array
		
		// look for beginning of data, search for "STID"
		while(!firstElement.contentEquals(STID_COLUMN)) {
		strg = br.readLine();
		strgArray = strg.split(" ");
		firstElement = strgArray[1];
		// System.out.println("The first element in: " + firstElement); // Show me whats stored HELPER
		}
				
		// Once STID is found, we can begin reading in the strings into an. array.
		// We only want the 1st token of the string, the STID.
		// while loops until the finalString is reached.
		while (!index.contentEquals(finalstring)) {
		strg = br.readLine();
		strgArray = strg.split(" ");
		index = strgArray[1];
		stations[indexCount] = index;
		// System.out.println(stations[indexCount]);            // Show me whats printed HELPER
		indexCount++;	
				
		// creates an array +1 bigger than the original, when limit is reached.
		if (indexCount == stations.length) {
		String[] tempArray = new String[indexCount+1];
		for (int i = 0; i < stations.length; i++) {
			tempArray[i] = stations[i];
			}
		stations = tempArray;
		} 		
		}	
		br.close();
	}
		
		
	// Search through array looking for the index number for the station
	public int indexOfStation() {
		int i = 0;
		while(!stations[i].contentEquals(MesoStation)) {
			i++;
		}
		stationIndex = i + 1;
		return stationIndex;		
	}
	//Calculate the +/- 1 and 2 for all indexes.
	public void calculateNs(int stationIndex) {
		this.stationIndex = stationIndex;
		
		nMinusOne = stations[stationIndex-2];
		nPlusOne = stations[stationIndex];
		nMinusTwo = stations[stationIndex-3];
		nPlusTwo = stations[stationIndex+1];
		}
	
	public String toString() {
		// returns string.format if n IS NOT one of the first 2 or last 2 stations in list
		// returns default statement if n IS one of first or last 2 stations in list
		String returnString="This index does not have an index of (n+/-1) or (n+/-2).";
		if (stationIndex >= 3 && stationIndex <= 118) {
			returnString = String.format("This index is average of %s and %s, %s and %s, and so on.", 
					nMinusOne, nPlusOne, nMinusTwo, nPlusTwo);
		}
		return returnString;
		
		
	}
}
