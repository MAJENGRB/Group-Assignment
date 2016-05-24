import java.sql.*;
import java.util.ArrayList;

public class QueryGenerator {
	private Connection con;
	public QueryGenerator()
	{
		con = Setup.getConnection();
	}
	//The following set of functions will generate queries used for creating the initial
	//set of tables for the database
	public ArrayList<String> getDistinctColumn(String column, String table)
	{
		String make = "SELECT DISTINCT " + column + " FROM " + table;
		try {
			Statement state = con.createStatement();
		  ResultSet rs = state.executeQuery(make);
		  ArrayList<String> distinctColumn = new ArrayList<String>();
		  while(rs.next())
		  {
			  distinctColumn.add(rs.getString(column));
		  }
		  return distinctColumn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ArrayList<String>> getDistinctColumn(String [] columns, String table)
	{
		String make = "SELECT DISTINCT ";
		for(int i = 0; i< columns.length; i++)
		{
			if(i<columns.length-1)
				make+= columns[i] + ", ";
			else
				make+= columns[i];
		}
			make+= " FROM " + table;
		try {
			Statement state = con.createStatement();
		  ResultSet rs = state.executeQuery(make);
		  ArrayList<ArrayList<String>> distinctColumn = new ArrayList<ArrayList<String>>();
		 
		  while(rs.next())
		  {
			  ArrayList<String> entries = new ArrayList<String>();
			  for(int i = 0; i< columns.length; i++)
				{
				  entries.add(rs.getString(columns[i]));
				}
			  distinctColumn.add(entries);
		  }
		  return distinctColumn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<ArrayList<String>> getDistinctColumnWithConditionals(String [] columns, String [] conditionalColumn, String[] conditionalValue, String table)
	{
		String make = "SELECT DISTINCT ";
		for(int i = 0; i< columns.length; i++)
		{
			if(i<columns.length-1)
				make+= columns[i] + ", ";
			else
				make+= columns[i];
		}
			make+= " FROM " + table;
			//add conditional
			make+= " WHERE " + conditionalColumn[0] + "='" + conditionalValue[0] + "'"; 
			if(conditionalColumn.length > 1)
			{
				for(int i = 1; i< conditionalColumn.length; i++)
				{
					make+= " AND " + conditionalColumn[i] + "='" + conditionalValue[i] + "'";
				}
			}
		try {
			Statement state = con.createStatement();
		  ResultSet rs = state.executeQuery(make);
		  ArrayList<ArrayList<String>> distinctColumn = new ArrayList<ArrayList<String>>();
		 
		  while(rs.next())
		  {
			  ArrayList<String> entries = new ArrayList<String>();
			  for(int i = 0; i< columns.length; i++)
				{
				  entries.add(rs.getString(columns[i]));
				}
			  distinctColumn.add(entries);
		  }
		  return distinctColumn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void insertIntoTeamTable(Team team)
	{
		String make = "INSERT INTO teams (Piece, Composer, Movement, Year";
		for(int i=0;i<team.numMembers();i++)
		{
			make += ", First" + (i+1) + ", Last" + (i+1) + ", Instrument" + (i+1);
		}
		make += ") VALUES ('" + team.getPiece().getName() + "', '" + team.getPiece().getComposer() + "', '"
				+ team.getPiece().getMovement() + "', " + team.getYear();
		ArrayList<Person> members = team.getMembers();
		for(int i=0;i<team.numMembers();i++)
		{
			make += ", '" +  members.get(i).getFirstName() + "', '" + members.get(i).getLastName() + "', '"
					+ members.get(i).getInstrument() + "'";
		}
		make += ")";
		try {
			Statement state = con.createStatement();
		 state.executeUpdate(make);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> getTable(String table, int numColumns)
	{
		String make = "SELECT * FROM " + table;
		try {
			Statement state = con.createStatement();
		  ResultSet rs = state.executeQuery(make);
		  ArrayList<ArrayList<String>> distinctColumn = new ArrayList<ArrayList<String>>();
		 
		  while(rs.next())
		  {
			  ArrayList<String> entries = new ArrayList<String>();
			  for(int i = 1; i<= numColumns; i++)
				{
				  entries.add(rs.getString(i));
				}
			  distinctColumn.add(entries);
		  }
		  return distinctColumn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}