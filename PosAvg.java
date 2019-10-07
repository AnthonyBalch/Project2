import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PosAvg {
	String STID_COLUMN = "STID"; //Constant variable, Station IDs follow this line.
	String MesoStation;		// Station we are interested in.
	String finalstring = "YUKO";
	int stationIndex = 0;
	String nPlusOne;
	String nMinusOne;
	String nPlusTwo;
	String nMinusTwo;
	
	String[] stations = new String[5];  // array of all the station strings.
	
	PosAvg(String MesoStation) {
		this.MesoStation = MesoStation;
	
		readFile();  // read file function 
		System.out.println("Read stations into program!");
		System.out.println("The MesoStation is:" + MesoStation);
		indexOfStation();
		System.out.println("About to attempt calculateNs with:" + stationIndex);
		
		// Only prints n if there is n +/- 2
		if (stationIndex >= 2 && stationIndex <= 117)
		{
			calculateNs(stationIndex); // Calculate n-1,n+1,n-2,n+2 for all except first and last 2 stations
		}
		else {
			System.out.println("Will not calculate n +/- 1,2 if there is no n +/- 1,2 ! ");
			System.out.println("N value: " + stationIndex + ".");
		}
		// toString
		
		
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
		// skip the first 2 and last 
		// 0,1,2,3,......,117,118,119
		// skip 0,1,118,119
		
		if (stationIndex == 0) {
			
			
		}
		
		this.stationIndex = stationIndex;
		
		String nMinusOne = stations[stationIndex-2];
		String nPlusOne = stations[stationIndex];
		String nMinusTwo = stations[stationIndex-3];
		String nPlusTwo = stations[stationIndex+1];
		
		System.out.println("Print n:" + stations[stationIndex-1]);
		System.out.println("Print n-1:" + nMinusOne);
		System.out.println("Print n+1:" + nPlusOne);
		System.out.println("Print n-2:" + nMinusTwo);
		System.out.println("Print n+2:" + nPlusTwo);
		
		
		// why isnt calculateNs working??

		
		/* for(int i = 2; i < 118; i++) {
			//for n-1
			String nMinusOne = stations[i-1];
			String nPlusOne = stations[i+1];
		} */
	}
}
