import java.sql.*;   // Use classes in java.sql package
 

public class JdbcSelectTest {  
   public static void main(String[] args) throws Exception{
     createTable();
      }
   public static void post() throws Exception{
	   
   }
   public static Connection getConnection() throws Exception{
	 try{
	   String driver = "com.mysql.jdbc.Driver";
	   String url = "jdbc:mysql://localhost:3306/MYSQL?useSSL=false";
	   String username = "root";
	   String password = "Jarv2013.";
	   Class.forName(driver);
	   Connection conn = DriverManager.getConnection(url,username,password);
	   System.out.println("YAY");
	   return conn;
	 } catch(Exception e){System.out.println(e);}
	 return null;
   }
   public static void createTable() throws Exception{
	   try{
	  Connection con = getConnection();
	  String make = ("CREATE TABLE IF NOT EXISTS PieceRecords(ID int NOT NULL AUTO_INCREMENT, LastName varchar(45), FirstName varchar(45), Instrument varchar(45), "
	  		+ "Year int, Piece varchar(45), Movement varchar(45), PRIMARY KEY(ID))");
	  PreparedStatement create = con.prepareStatement(make);
	  create.executeUpdate();
	   }catch(Exception e){System.out.println(e);}
	   finally{System.out.println("Function Complete");}
   }
}