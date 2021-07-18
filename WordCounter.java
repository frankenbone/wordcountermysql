import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class WordCounter 
{
	public static void main(String[] args) throws Exception {

		//Kickoff database methods
		getConnection();
		createTable();
		post();
		get();
		

		//file location 
		File fin = new File("src/Raven");

		//Setup a Scanner to read the file
		Scanner fileScan = new Scanner(fin);

		//setup a HashMap with String, Integer
		Map<String,Integer> map = new HashMap<String, Integer>(); 

		while (fileScan.hasNext())
		{
			String val = fileScan.next(); 
			if(map.containsKey(val) == false) // if string is not inserted in the map yet, insert by setting the frequency as 1
				map.put(val,1);//val of 1

			else //or set val to 1
			{
				int count = (map.get(val)); // find frequency of word
				map.remove(val);  // remove the entry from map
				map.put(val,count+1); // re-inserting word and increase frequency by 1 increment
			}

		}

		//Retrieve map 
		Set<Map.Entry<String, Integer>> set = map.entrySet(); 
		//Make ArrayList and initialize ArrayList 
		List<Map.Entry<String, Integer>> sortList = new ArrayList<Map.Entry<String, Integer>>(set);
		//Sort ArrayList
		Collections.sort( sortList, new Comparator<Map.Entry<String, Integer>>() 

		{
			@Override
			public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 

			{
				// sortList in descending order 
				return ( b.getValue()).compareTo( a.getValue() );

			}

		} );

		//Print out the list
		for(Map.Entry<String, Integer> i:sortList){
			System.out.println("The word: " + i.getKey()+" - occurs this many times: "+ i.getValue());
		}
	}

	public void start(Stage arg0) throws Exception {
		Stage primaryStage = null;
		primaryStage.setTitle("The Raven Word Count");

		Button button = new Button();
		button.setText("Click this button");

	}

	//Show Words added to word_occurances.word
	public static ArrayList<String> get() throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM word");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				System.out.print(result.getString("word"));
				System.out.print(" ");
				array.add(result.getString("word"));
			}
			System.out.println("All records have been selected!");
			return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
		
	}


	//Create table word to database word_occurances
	public static void createTable() throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS word(id int NOT NULL AUTO_INCREMENT, word varchar(45), PRIMARY KEY(id))");
			create.executeUpdate();			
		}catch(Exception e){System.out.println(e);}
		finally{
			System.out.println("Function Complete.");
			};
		
	}
	//Insert Data into word db
		public static void post() throws Exception{
			final String var1 = "the";
			final String var2 = "this";
			final String var3 = "test";
			final String var4 = "a";
			try{
				Connection con = getConnection();
				PreparedStatement posted = con.prepareStatement("INSERT INTO word (word) VALUES ('"+var1+"')");
				posted.executeUpdate();
				PreparedStatement postedtwo = con.prepareStatement("INSERT INTO word (word) VALUES ('"+var2+"')");
				postedtwo.executeUpdate();
				PreparedStatement postedthree = con.prepareStatement("INSERT INTO word (word) VALUES ('"+var3+"')");
				postedthree.executeUpdate();
				PreparedStatement postedfour = con.prepareStatement("INSERT INTO word (word) VALUES ('"+var4+"')");
				postedfour.executeUpdate();
				
			} catch(Exception e){System.out.println(e);}
			finally {
				System.out.println("Insert Completed.");
			}
		}
	
	//Get connection to word_occurances database

	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/word_occurances?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "gary";
			String password = "gary";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;

		}catch(Exception e) {System.out.println(e);

		return null;
		}



	}
}//complete class