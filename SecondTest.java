import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SecondTest {

	@Test
	void test() throws FileNotFoundException {
		WordCounter test = new WordCounter();
		File fin = new File("src/Raven");
		Scanner fileScan = new Scanner(fin);
		String val = fileScan.next(); 
		Map<String,Integer> map = new HashMap<String, Integer>();
		if(map.containsValue(val) == false)
			System.out.println("File is being scanned");
		else
		{
			System.out.println("File is not being scanned");
		}

	}

}