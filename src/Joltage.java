import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Joltage {
	private static long sum = 0;
    static int maxJoltage(String line) {
    	int r = 0;
    	int i = -1;
    	int iIndex = 0;
    	int j = -1;
    	for (int n = 0; n < line.length(); n++) {
            char c = line.charAt(n);
            if (!Character.isDigit(c)) continue;
            int d = c - '0';
            if (d > i && n < line.length() - 1) {
            	i = d;
            	iIndex = n;
            }
        }
    	for (int n = iIndex + 1; n < line.length(); n++) {
            char c = line.charAt(n);
            if (!Character.isDigit(c)) continue;
            int d = c - '0';
            if (d > j) {
            	j = d;
            }
        }
    	
    	
    	//System.out.println(i + ", i --> iIndex ," + iIndex + ", j:" + j);
    	r = Integer.valueOf(String.valueOf(i) + String.valueOf(j));
    	return r;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Joltage.txt");
    	//File file = new File("example.txt");
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += maxJoltage(line);
            }
            
        }
        System.out.println(sum);
    }
}