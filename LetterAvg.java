import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterAvg {

	char letter; //letter that will be from MesoInherit.letterAverage()
	String stationString;  // String of stations that start with char letter
	int counter = 0;
	int arraySize = 10;
	String[] output = new String[arraySize];
	String[] tempArray = new String[arraySize];
	
	String STID_COLUMN = "STID"; //Constant variable, Station IDs follow this line.
	String MesoStation;		// Station we are interested in.
	String finalstring = "YUKO";
	String[] stations = new String[5];  // array of all the station strings.
	
	LetterAvg(char letter){
		this.letter = letter;
		readFile();  // read file function
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
			}
			
			// Once STID is found, we can begin reading in the strings into an. array.
			// We only want the 1st token of the string, the STID.
			// while loops until the finalString is reached.
			while (!index.contentEquals(finalstring)) {
			strg = br.readLine();
			strgArray = strg.split(" ");
			index = strgArray[1];
			stations[indexCount] = index;
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

	int numberOfStationWithLetterAvg(){
		//look through array for strings beginning with desired letter
		int i = 0;
		while (i<stations.length-1) {  			  //search through entire array
			if(stations[i].charAt(0) == letter) {   
			output[counter] = stations[i];	  // once you found one, add it to string and increase the counter.
			counter++;
			if(counter == output.length) {
				resize();
			}
			}
		i++;
		}
		return counter;
	}
	
	//if number of stored stations becomes too large, resize array.
	public void resize() {
		arraySize = arraySize + 1;
		for(int i = 0; i < arraySize; i++) {
			tempArray[i] = output[i];
		}
		output = new String[arraySize];
		for(int i = 0; i < arraySize; i++ ) {
			output[i] = tempArray[i];
		}
	}
	// Takes in 1 long string, splits into array of strings, prints strings 1 line at at time
	public String toString() {
		String outputString ="They are:\n";

		
		for(int i = 0; i < counter; i++){
			outputString = outputString + output[i] + "\n";
		}
		
		return outputString;
		
	}
}
