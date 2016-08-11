import java.sql.*; 
import java.util.ArrayList;  // Use classes in java.sql package
import java.io.*;
import view.mainPage;
//Note: This class grossly violated OO principles. This could probably be done more 
//easily in manual SQL
public class Setup {  
	private static Connection conn;
	static{
		conn = createConnection();
	}
   public static void main(String[] args) throws Exception{
     /*
	   createMainTable();
	   createTeamTable();
	   QueryGenerator test = new QueryGenerator();
	   
	   ArrayList<String> test1 = test.getDistinctColumn("Piece", "piecerecords");
	   for(int i=0; i<test1.size(); i++)
	   {
		   System.out.println(test1.get(i));
	   }
	   String[] columns = new String[] {"Piece", "Composer", "Movement", "Year"}; 
	   ArrayList<ArrayList<String>> test2 = test.getDistinctColumn(columns, "piecerecords");
	   for(int i = 0; i<test2.size(); i++)
	   {
		   System.out.println(test2.get(i).get(0) + " " + test2.get(i).get(1) + " " +
				   test2.get(i).get(2) + test2.get(i).get(3));
	   }
	   populateTeamsTable();
	   ArrayList<ArrayList<String>> test4 = test.getTable("teams", 22);
	   Team doge = new Team(test4.get(0));
	   System.out.println(doge.getMembers().size());
	   System.out.println(doge.toString());
     
	   
	   
     /*
	   Team test = new Team();
	   test.addMember(new Person("Gail", "MacColl", "violin"));
	   test.addMember(new Person("Vera", "Snheider", "cello"));

	   QueryController tester = new QueryController();
           
	   ArrayList<ArrayList<String>> doge = tester.checkForPlayerOverlaps(test);
	   for(int i = 0; i< doge.size(); i++)
	   {
		   for(int j = 0; j < doge.get(i).size(); j++)
		   {
			   System.out.print(doge.get(i).get(j) + " ");
		   }
		   System.out.println(" " + i);
	   }
               ArrayList<Person> tits = tester.getRoster("11");
        for(int i = 0; i<tits.size(); i++)
        {
            System.out.println(tits.get(i).toString());
        }
	    */
        QueryController tester = new QueryController();	   

        Person sarah = new Person("Carol", "Sheppard", "cello");
	   Person barb = new Person("Barb", "Lambdin", "viola");
	   Person gemma = new Person("Gemma", "Kuijpers", "violin");
	   Person mike = new Person("Michael", "Lav", "violin");
	   Team test = new Team();
	   test.addMember(sarah);
	   test.addMember(barb);
	   test.addMember(gemma);
	   test.addMember(mike);
           test.setPiece(new Piece("K.387","Mozart",""));
           System.out.println(tester.checkForPieceOverlaps(test).size());
           new mainPage();
      }
   public static void post() throws Exception{
	   
   }
   public static Connection getConnection(){
	   return conn;
   }
   private static Connection createConnection() {
	 try{
	   String driver = "com.mysql.jdbc.Driver";
	   String url = "jdbc:mysql://localhost/gettysburgchambermusic?useSSL=false";
	   String username = "root";
	   String password = "T201328t.";
	   Class.forName(driver);
	   Connection connection = DriverManager.getConnection(url,username,password);
	   return connection;
	 } catch(Exception e){System.out.println(e);}
	 return null;
   }
   public static void createMainTable() throws Exception{
	   try{
	  Connection con = getConnection();
	  String make = ("CREATE TABLE IF NOT EXISTS piecerecords(ID int NOT NULL AUTO_INCREMENT, LastName varchar(45), FirstName varchar(45), Instrument varchar(45), "
	  		+ "Year int, Composer varchar(45), Piece varchar(45), Movement varchar(45), PRIMARY KEY(ID))");
	  PreparedStatement create = con.prepareStatement(make);
	  create.executeUpdate();
	   }catch(Exception e){System.out.println(e);}
	   finally{System.out.println("Function Complete");}
   }
   public static void createTeamTable()
   {
	   try{
	  Connection con = getConnection();
	  String make = ("CREATE TABLE IF NOT EXISTS Teams"
	  		+ "(ID int NOT NULL AUTO_INCREMENT, Piece varchar(45), Composer varchar(45), Movement varchar(45), Year int, "
	  		+ "Last1 varchar(45), First1 varchar(45), Instrument1 varchar(45), "
	  		+  "Last2 varchar(45), First2 varchar(45), Instrument2 varchar(45), "
	  		+ "Last3 varchar(45), First3 varchar(45), Instrument3 varchar(45), "
	  		+ "Last4 varchar(45), First4 varchar(45), Instrument4 varchar(45), "
	  		+ "Last5 varchar(45), First5 varchar(45), Instrument5 varchar(45), "
	  		+ "Last6 varchar(45), First6 varchar(45), Instrument6 varchar(45), "
	  		+ "PRIMARY KEY(ID))");
	  PreparedStatement create = con.prepareStatement(make);
	  create.executeUpdate();
	   }catch(Exception e){System.out.println(e);}
	   finally{System.out.println("Function Complete");}
   }
	public static String updateMainTable(String csvEntry) throws Exception
	{
		//not fully working --  use MYSQL Workbench import wizard
		csvEntry.replaceAll(", ,", ",NULL,");
		String delims = ",";
		String update = "INSERT INTO piecerecords (LastName, FirstName, Instrument, Year, Composer, Piece, Movement)\nVALUES(";
		String [] entries = csvEntry.split(delims);
		for(int i =0; i< entries.length-1; i++)
		{
			update += "'" + entries[i] + "', ";
		}
		update = update + "'" + entries[entries.length -1] + "')";
		return update;	
	}
   public static void updateFromCSV(String filePath) throws Exception
	{
		try {
			Connection con = getConnection();
		
		   String line = null;
	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(filePath);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                String make = updateMainTable(line);
	                System.out.println(make);
	                PreparedStatement create = con.prepareStatement(make);
	          	  create.executeUpdate();
	            }   

	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                filePath + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + filePath + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   public static void populateTeamsTable()
   {
	   QueryGenerator query = new QueryGenerator();
	   String[] columns = new String[] {"Piece", "Composer", "Movement", "Year"}; 
	   ArrayList<ArrayList<String>> teamList = query.getDistinctColumn(columns, "piecerecords");
	   //populates teams table
	   for(int i = 0; i<teamList.size(); i++)
	   {
		   Team temp = new Team();
		   
		   //Sets the piece for the team
		   temp.setPiece(new Piece(teamList.get(i).get(0),teamList.get(i).get(1),teamList.get(i).get(2)));
		   temp.setYear(Integer.parseInt(teamList.get(i).get(3)));
		 //get members of each team using SELECTWITHCONDITIONALSMETHOD
		   temp.setMembersFromDB(query.getDistinctColumnWithConditionals(
				   new String[]{"LastName","FirstName","Instrument"}, new String[]{"Piece","Composer",
				   		 "Movement","Year"}, new String[]{temp.getPiece().getName(),temp.getPiece().getComposer(),
				   				 temp.getPiece().getMovement(),Integer.toString(temp.getYear())}, "piecerecords"));
		   query.insertIntoTeamTable(temp);
	   }
   }
}