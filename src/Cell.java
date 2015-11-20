import java.util.Random;
public class Cell {
	public String alive = determineLiving();
	public int liveNeighbors;
	
	private String determineLiving(){
		Random rand = new Random();
	    int randomNum = rand.nextInt((1 - 0) + 1) + 0;
		if(randomNum == 1){
			return "+";
		} else {
			return "X";
		}
	}
	
	public String returnLiving(){
		return alive;
	}
	public int returnNeighbors(){
		return liveNeighbors;
	}
	public void getNeighbors(int input){
		liveNeighbors = liveNeighbors + input;
	}
	public void clearNeighbors(){
		liveNeighbors = 0;
	}
}
