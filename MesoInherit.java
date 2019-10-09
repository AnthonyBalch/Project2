import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MesoInherit extends MesoAbstract{ //REMOVED ABSTRACT TO TEST 
	String STID_COLUMN = "STID"; //Constant variable, Station IDs follow this line.
	String MesoStation;		// Station we are interested in.
	String finalstring = "YUKO";
	String[] stations = new String[5];  // array of all the station strings.

	char charIndex;  // creates char for calAverage
	int[] intArray = new int[4];
	
	MesoInherit(String MesoStation) {
		this.MesoStation = MesoStation;
		
		readFile();  // read file function 

		
		calAverage(); // calculate ascii values and creates average
		letterAverage(); //Converts average ascii value into character.

		
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
	
	
	//calAverage Override
	@Override
	public int[] calAverage() {
		int sum = 0;
		double answer = 0.0;
		// sum ascii values and then divide by 4, converting int to double.
		for(int i = 0; i < 4; i++) {
			charIndex = MesoStation.charAt(i);
			int ascii =(int) charIndex;
			sum = sum + ascii;
			answer = sum;
			answer = answer/4;
			//System.out.println("Sum after:" + i + " loops :" + sum);
		}
		double answer1 = answer;
		double answer2 = answer;
		double answer3 = answer;
		
		//round to ceiling
		answer1 = Math.ceil(answer1);
		intArray[0] = (int) answer1;
		
		//round to floor
		answer2 = Math.floor(answer2);
		intArray[1] = (int) answer2;
		
		//round normally (average)
		answer3 = Math.round(answer3);
		intArray[2] = (int) answer3;
		
		return intArray;
	}
	
	//letterAverage Override
	@Override
	char letterAverage() {
		int answer = intArray[2];
		char asciiChar = (char)(answer);
		return asciiChar;
	} 
}
