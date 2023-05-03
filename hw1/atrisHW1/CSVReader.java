import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	
	public static void main(String[] args)
	{
		
		String path = "/Users/atrivyas7/Downloads/biostats.csv";
		String line = "";
		int lines = 0;
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			while((line = br.readLine()) != null) {
				
				String[] values = line.split(",");
				System.out.println(values[0] + " " + values[1] + " " + values[2] + " " + values[3] + " " + values[4]);
				lines++;
				break;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		System.out.println(lines);
		
		
		
		
	}
 
}
