
public class Person {
private String firstName;
private String lastName;
private String instrument;
public Person(){
	
}
public Person(String first, String last, String inst){
	this.firstName = first;
	this.lastName = last;
	this.instrument = inst;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getInstrument() {
	return instrument;
}
public void setInstrument(String primaryInstrument) {
	this.instrument = primaryInstrument;
}
//returns most recent year the two members were teammates
public int isTeammate(Person teammate)
{
	//SELECT * FROM teams WHERE FirstName=member1.getFirstName...
	//For results from above, create team objects use new team constructor
	//For each team, if(team.isMember(member2)>-1)
	//					if(team.year > year)
	//						year=team.year;
	String make = "SELECT year FROM teams WHERE (";
	QueryGenerator query = new QueryGenerator();
	//ArrayList<ArrayList<String>> years = query.getDistinctColumnWithConditionals({year}, conditionalColumn, conditionalValue, table)
	for(int i=1;i<=6;i++)
	{
		make += "Last" + i + "='" + this.getLastName() + "'";
		if(i<6)
			make += " OR ";
		else
			make+= ")";
	} 
	System.out.println(make);
	int year = -1;
	return year;
}
@Override
public String toString()
{
	String name = firstName + " " + lastName;
	return name;
}
}
