
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {
	static int maxX = 0;
	static int maxY = 0;
	static Object[][] currentGeneration = establishCurrentGeneration();
	static Object[][] nextGeneration = currentGeneration;
	static int generationNumber = 0;
	
	public static void main(String[] args){
		for (int i = 0; i < maxY; ++i) System.out.println();
		
		displayGeneration();
		detectNeighbors();
		applyFourRules();
		resetGeneration();
		
		try {
		    TimeUnit.MILLISECONDS.sleep(300);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		main(args);
	}

	private static void displayGeneration() {
		for(int y = 1; y < maxY+1; y++){
			for(int x = 1; x < maxX+1; x++){
				System.out.print(((Cell) currentGeneration[y][x]).returnLiving() + " ");
			}
			System.out.println();
		}
		System.out.println(generationNumber);
	}
	
	private static void detectNeighbors() {
		for(int x = 1; x < maxX+1; x++){
			for(int y = 1; y < maxY+1; y++){
				int liveNeighbors = 0;
				if((((Cell) currentGeneration[y-1][x-1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y+1][x-1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y-1][x+1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y+1][x+1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y][x-1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y][x+1]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y-1][x]).alive).equals("X")){
					liveNeighbors++;
				}
				if((((Cell) currentGeneration[y+1][x]).alive).equals("X")){
					liveNeighbors++;
				}
				((Cell) currentGeneration[y][x]).getNeighbors(liveNeighbors);
			}
		}
	}
	
	private static void applyFourRules() {
		for(int x = 1; x < maxX+1; x++){
			for(int y = 1; y < maxY+1; y++){
				if((((Cell) currentGeneration[y][x]).alive).equals("X")){
					if(((Cell) currentGeneration[y][x]).liveNeighbors < 2 || ((Cell) currentGeneration[y][x]).liveNeighbors > 3){
						((Cell) nextGeneration[y][x]).alive = "+";
					} 
				} else {
					if(((Cell) currentGeneration[y][x]).liveNeighbors == 3){
						((Cell) nextGeneration[y][x]).alive = "X";
					} 
				}
			}
		}
	}

	private static void resetGeneration() {
		for(int x = 1; x < maxX+1; x++){
			for(int y = 1; y < maxY+1; y++){
				currentGeneration[y][x] = nextGeneration[y][x];
			}
		}
		for(int x = 1; x < maxX+1; x++){
			for(int y = 1; y < maxY+1; y++){
				((Cell) currentGeneration[y][x]).clearNeighbors();
			}
		}
		generationNumber++;
	}

	private static Object[][] establishCurrentGeneration(){
		determineSize();
		Object[][] tempCellArray = new Object[maxY+2][maxX+2];
		for(int x = 0; x < maxX+2; x++){
			for(int y = 0; y < maxY+2; y++){
				tempCellArray[y][x] = new Cell();
				if(x == 0 || x == maxX+1 || y == 0 || y == maxY+1){
					((Cell) tempCellArray[y][x]).alive = "+";
				}
			}
		}
		return tempCellArray;
	}

	private static void determineSize(){
		for (int i = 0; i < maxY; ++i) System.out.println();
		System.out.println("Welcome to the 'Game of Life' simulator");
		System.out.println("What is this environment's size?");
		
		System.out.println("Length:");
		Scanner input1 = new Scanner(System.in);
		maxY = input1.nextInt();
		
		System.out.println("Width:");
		Scanner input2 = new Scanner(System.in);
		maxX = input2.nextInt();
	}
}