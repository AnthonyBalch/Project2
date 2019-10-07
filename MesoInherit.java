import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MesoInherit { // extends MesoAbstract { REMOVED ABSTRACT TO TEST 
	String STID_COLUMN = "STID"; //Constant variable, Station IDs follow this line.
	String MesoStation;		// Station we are interested in.
	String finalstring = "YUKO";
	
	String[] stations = new String[5];  // array of all the station strings.

	
	MesoInherit(String MesoStation) {
		this.MesoStation = MesoStation;
		
		readFile();  // read file function 
		System.out.println("Read stations into program!");
		System.out.println("The element in 0th array is: " + stations[0]);
		System.out.println("The element in 1st array is: " + stations[0+1]);

		
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
	
	
	/*
	//calAverage Override
	@Override
	public int[] calAverage() {
		
	}
	
	//letterAverage Override
	@Override
	char letterAverage() {
		
	} */
}
