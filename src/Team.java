import java.util.ArrayList;

public class Team {
	//Constructor for creating a team from the input from the SQL database
	public Team(ArrayList<String> input)
	{
		this.piece = new Piece(input.get(1),input.get(2),input.get(3));
		this.year = Integer.parseInt(input.get(4));
		int lastIndex = input.indexOf(null);
		int currentIndex = 5;
		this.members = new ArrayList<Person>();
		while(currentIndex<lastIndex)
		{
			this.addMember(new Person(input.get(currentIndex),input.get(++currentIndex),input.get(++currentIndex)));
			currentIndex++;
		}
	}
	public Team()
	{
		members = new ArrayList<Person>();
		coach = new Person();
	}
private ArrayList<Person> members;
private Person coach;
private int year;
private Piece piece;
public Piece getPiece() {
	return piece;
}
public void setPiece(Piece piece) {
	this.piece = piece;
}
public Person getCoach() {
	return coach;
}
public void setCoach(Person coach) {
	this.coach = coach;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public void addMember(Person newMember)
{
	members.add(newMember);
}
public void removeMember(Person delete)
{
	if(this.isMember(delete)!=-1)
	{
		members.remove(this.isMember(delete));
	}
}
public ArrayList<Person> getMembers()
{
	return members;
}
public int isMember(Person person)
{
	for(int i = 0; i < members.size(); i++)
	{
		if(person.toString().equals(members.get(i).toString()))
		{
			return i;
		}
	}
	return -1;
}
//Expected order of memberList: LastName, FirstName, Instrument
public void setMembersFromDB(ArrayList<ArrayList<String>> memberList)
{
	for(int i =0; i < memberList.size(); i++)
	{
		Person newMember = new Person();
		//arbitrary order
		//TODO generalize this method
		newMember.setLastName(memberList.get(i).get(0));
		newMember.setFirstName(memberList.get(i).get(1));
		newMember.setInstrument(memberList.get(i).get(2));
		this.addMember(newMember);
	}
}
public int numMembers()
{
	return this.members.size();
}
@Override
public String toString()
{
	String temp = new String();
	for(int i = 0; i<members.size();i++)
	{
		temp += members.get(i).toString() + " ";
	}
	temp +=piece.toString();
	temp += year;
	return temp;
}
}

