import java.util.Random;
import java.util.Stack;

public class RingMaze extends Maze {
	public RingMaze(int width, int height) {
		super(width, height);
		strategy = MazeGenerationStrategy.RING;
	}
	
	/**
	 * The process to creating a maze using a randomised depth-first search.
	 * @param rand
	 * The random generator.
	 */
	// TODO: This function.
	public void generateMaze(long seed) {
		this.seed = seed;
		Random rand = new Random(seed);
		Stack<Coord> currentPath = new Stack<Coord>();
		Coord current = start.clone();
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				maze[x][y] = new MazeGrid();
			}
		}
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x == y && (x < width / 2 || y < height / 2)) {
					maze[x][y].bottom = true;
					maze[x][y].right = true;
				} else if (width - x - 1 == y && (x >= width / 2 || y < height / 2)) {
					maze[x][y].bottom = true;
					maze[x][y].left = true;
				} else if (x == height - y - 1 && (x < width / 2 || y >= height / 2)) {
					maze[x][y].top = true;
					maze[x][y].right = true;
				} else if (width - x - 1 == height - y - 1 && (x >= width / 2 || y >= height / 2)) {
					maze[x][y].top = true;
					maze[x][y].left = true;
				} else if (x > y && width - x - 1 > y) {
					maze[x][y].left = true;
					maze[x][y].right = true;
				} else if (x < y && width - x - 1 < y) {
					maze[x][y].left = true;
					maze[x][y].right = true;
				} else if (x > y && width - x - 1 < y) {
					maze[x][y].top = true;
					maze[x][y].bottom = true;
				} else if (x < y && width - x - 1 > y) {
					maze[x][y].top = true;
					maze[x][y].bottom = true;
				}
				
				if (x + 1 == width / 2) {
					if (y % 2 == 0) {
						maze[x][y].right = false;
						maze[x + 1][y].left = false;
					}
				} else if (y + 1 == height / 2) {
					if (x % 2 == 1) {
						maze[x][y].bottom = false;
						maze[x][y + 1].top = false;
					}
				}
			}
		}
		
		placeCoins(rand);
	}

	/**
	 * Gets a random direction based on a random generator.
	 * @param r
	 * The random generator.
	 * @return
	 * The direction decided.
	 */
	private Direction getRandomDirection(Random r) {
		int randomInt = r.nextInt(4);
		switch(randomInt) {
		case 0:
		default:
			return Direction.UP;
		case 1:
			return Direction.RIGHT;
		case 2:
			return Direction.DOWN;
		case 3:
			return Direction.LEFT;
		}
	}

	protected void placeCoins(Random rand) {
		int totalCoins = width * height / 20;
		
		for (int i = 0; i < totalCoins; i++) {
			boolean foundSpot = false;
			Coord coinPos = null;
			while (!foundSpot) {
				foundSpot = true;
				coinPos = new Coord(rand.nextInt(width), rand.nextInt(height));
				if (!coinPos.equals(start) && !coinPos.equals(end)) {
					for (Coin c : coins) {
						if (coinPos.equals(c.getPos())) {
							foundSpot = false;
						}
					}
				} else {
					foundSpot = false;
				}
			}
			
			if (foundSpot) {
				coins.add(new Coin(coinPos));
			}
		}
	}
}