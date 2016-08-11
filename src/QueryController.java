import java.util.ArrayList;

public class QueryController {

private QueryGenerator generator;
public QueryController()
{
	this.generator = new QueryGenerator();
}
Person getPersonFromDB(String last, String first){
	
	return null;
}
ArrayList<ArrayList<String>> checkForPlayerOverlaps(Team team){
	//Returns a list of the most recent case of members sharing a team; all combinations considered
	ArrayList<Person> members = team.getMembers();
	ArrayList<ArrayList<String>> overlaps = new ArrayList<ArrayList<String>>();
	int year=-1;
	for(int i = 0; i < members.size()-1; i++)
	{
		for(int j = i+1; j<members.size(); j++)
		{
			year = this.isTeammate(members.get(i), members.get(j));
			if( year != -1)
			{
				ArrayList<String> additions = new ArrayList<String>();
				additions.add(members.get(i).toString());
				additions.add(members.get(j).toString());
				additions.add(Integer.toString(year));
				overlaps.add(additions);
		}
	}
	}
	return overlaps;
}
ArrayList<ArrayList<String>> checkForPieceOverlaps(Team team){
	//Returns a list of the most recent case of members sharing a team; all combinations considered
	ArrayList<Person> members = team.getMembers();
	ArrayList<ArrayList<String>> overlaps = new ArrayList<ArrayList<String>>();
        String [] columns = {"Year"};
        String [] conditionals = {"Composer","Piece","Movement","LastName","FirstName"};
        String [] values = {team.getPiece().getComposer(), team.getPiece().getName(), team.getPiece().getMovement(), null,null};
	ArrayList<ArrayList<String>> years = new ArrayList<ArrayList<String>>();
	for(int i = 0; i < members.size()-1; i++)
	{
            values[3] = members.get(i).getLastName();
            values[4] = members.get(i).getFirstName();
            System.out.println(values[0]+values[1]+values[2]+values[3]+values[4]);
            //all the years where the member played a piece.
            years = generator.getDistinctColumnWithConditionals(columns,conditionals,values,"pieceRecords");
            System.out.println(years.size());
            if(years.size()> 0)
            {
                int temp = -1;
                for(int j = 0; j< years.size(); j++)
                {
                    if(Integer.parseInt(years.get(j).get(0))> temp)
                        temp = Integer.parseInt(years.get(j).get(0));
                }
                ArrayList<String> mostRecentOverlap = new ArrayList<String>();
                mostRecentOverlap.add(members.get(i).getFirstName());
                mostRecentOverlap.add(members.get(i).getLastName());
                mostRecentOverlap.add(Integer.toString(temp));
                overlaps.add(mostRecentOverlap);
            }
        }
	return overlaps;
}
public ArrayList<Person> getRoster(String year)
{
String [] columns = new String[3];
columns[0] = "LastName";
columns[1] = "FirstName";
columns[2] = "Instrument";
String [] conditional = new String[1];
conditional[0] = "Year";
String[] value = new String[1];
value[0] = year;

ArrayList<ArrayList<String>> rosterList = generator.getDistinctColumnWithConditionals(columns,conditional,value, "pieceRecords");
ArrayList<Person> output = new ArrayList<Person>();
for(int i = 0; i< rosterList.size(); i++)
{
    output.add(new Person(rosterList.get(i).get(1), rosterList.get(i).get(0), rosterList.get(i).get(2)));
}
return output;
}
public void getRoster(ArrayList<Person> members)
{
	
}
int isTeammate(Person member1, Person member2)
{
	String [] columns = new String [1];
	columns[0] = "year";
	String [] conditionalColumn = new String [6];
	String [] conditionalValue = new String [6];
	for(int i =1; i<=6; i++)
	{
		conditionalColumn[i-1] = "Last"+ i;
		conditionalValue[i-1] = member2.getLastName();
	}
	String table = "teams";
	ArrayList<ArrayList<String>> years = generator.getDistinctColumnWithConditionals(columns, conditionalColumn, conditionalValue, table, "OR");
	int year = -1;
	int temp = -2;
	if(years.size()>0)
	{
		for(int i = 0; i < years.get(0).size();i++)
		{
			temp = Integer.parseInt(years.get(0).get(i));
			if(temp > year)
				year=temp;
		}
	}
	return year;
}
}


		
		