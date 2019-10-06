import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MesoInherit { // extends MesoAbstract { REMOVED ABSTRACT TO TEST 
	String STID_COLUMN = "STID"; //Constant variable, Station IDs follow this line.
	String MesoStation;		// Station we are interested in.

	
	MesoInherit(String MesoStation) {
		this.MesoStation = MesoStation;
		
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
		String firstElement = "AAAA";
		String index = "AAAA";
		String[] strgArray = new String[5];
		
		// look for beginning of data, search for "STID"
		while(!firstElement.contentEquals(STID_COLUMN)) {
		strg = br.readLine();
		strgArray = strg.split(" ");
		firstElement = strgArray[1];
		System.out.println("The first element in: " + firstElement); // Show me whats stored
			// Once STID is found, we can begin reading in the strings into an array.
			// We only want the 1st token of the string, the STID.
			if(firstElement.contentEquals(STID_COLUMN)) {
			strg = br.readLine();
			strgArray = strg.split(" ");
			index = strgArray[1];
			System.out.println("The first element stored after finding STID is:" + index); // Show me whats stored
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
