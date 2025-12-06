import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintingDepo {
	private static int rows;
	private static int cols;
	private static boolean moreRolls = true;
	
    public static boolean[][] parseGrid(File file) throws IOException {
        List<boolean[]> rows = new ArrayList<>();
        int width = -1;
        int rowIndex = 0;

        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (width == -1) {
                    width = line.length();
                } else if (line.length() != width) {
                    System.out.println("Inconsistent line length");
                }

                boolean[] row = new boolean[width];
                for (int i = 0; i < width; i++) {
                    char c = line.charAt(i);
                    if (c == '@') {
                        row[i] = true;
                    } else if (c == '.') {
                        row[i] = false;
                    } else {
                    	System.out.println("Invalid character '" + c + "' at line " + rowIndex + ", column " + i);
                    }
                }

                rows.add(row);
                rowIndex++;
            }
        }

        // convert list to array
        boolean[][] grid = new boolean[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            grid[i] = rows.get(i);
        }
        return grid;
    }

    public static void main(String[] args) throws Exception {
        //File file = new File("example.txt");
        File file = new File("PrinterRolls.txt");
        boolean[][] grid = parseGrid(file);
        rows = grid.length;
        cols = grid[0].length;
        int totalRollsRemoved = 0;
        while(moreRolls) {
        	totalRollsRemoved += countRolls(grid);
        }
        System.out.println(totalRollsRemoved);

    }
    
    public static int countRolls(boolean[][] grid) {
    	int count = 0;
    	//System.out.println("got here");
    	moreRolls = false;
    	// 8 positions from current roll
    	//{-1, -1}, {-1, 0}, {-1, 1},
        //{0, -1},           {0, 1},
        //{1, -1},  {1, 0},  {1, 1}
    	for(int i = 0; i < rows; i++) {
    		for(int j = 0; j < cols; j++) {
    			if(grid[i][j]) {
	    			int rollsNear = countAdjacent(grid, i, j);
	    			if(rollsNear < 4) {
	    				count++;
	    				moreRolls = true;
	    				grid[i][j] = false;
	    			}
    			}
        	}
    	}
    	
    	return count;
    }
    private static int countAdjacent(boolean[][] rolls, int r, int c) {
    	int count = 0;

        // top row
        int nr = r - 1;
        int nc = c - 1;
        if (nr >= 0 && nc >= 0 && rolls[nr][nc]) count++;//{-1, -1}
        nc = c;
        if (nr >= 0 && nc >= 0 && nc < cols && rolls[nr][nc]) count++; //{-1, 0}
        nc = c + 1;
        if (nr >= 0 && nc < cols && rolls[nr][nc]) count++; //{-1, 1}
        
        nr = r;
        nc = c - 1;
        if (nc >= 0 && rolls[nr][nc]) count++;//{0, -1}
        nc = c + 1;
        if (nc < cols && rolls[nr][nc]) count++;//{0, 1}

        nr = r + 1;
        nc = c - 1;
        if (nr < rows && nc >= 0 && rolls[nr][nc]) count++; //{1, -1}
        nc = c;
        if (nr < rows && nc >= 0 && nc < cols && rolls[nr][nc]) count++; //{1, 0}
        nc = c + 1;
        if (nr < rows && nc < cols && rolls[nr][nc]) count++; //{1, 1}

        return count;
    }
    
}