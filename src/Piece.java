
public class Piece {
	private String name;
	private String movement;
	private String composer;
	public Piece(String piece, String composerInput,String movementInput)
	{
		this.name = piece;
		this.composer = composerInput;
		this.movement= movementInput;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public String getComposer() {
		return composer;
	}
	public void setComposer(String composer) {
		this.composer = composer;
	}
	@Override
	public String toString()
	{
		return name + movement + composer;
	}
	
}
