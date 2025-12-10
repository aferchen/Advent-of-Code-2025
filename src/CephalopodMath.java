import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CephalopodMath {

	public static void main(String[] args) throws FileNotFoundException {
		
		
		//File file = new File("example.txt");
        File file = new File("CephalopodMath.txt");
		try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name())) {
                int[] row1 = parseNumRow(scanner.nextLine());
                int[] row2 = parseNumRow(scanner.nextLine());
                int[] row3 = parseNumRow(scanner.nextLine());
                int[] row4 = parseNumRow(scanner.nextLine());
                String[] operators = scanner.nextLine().trim().split("\\s+");
                long overallSum = 0L;
                for(int i = 0; i < row1.length; i++) {
                	
                	if(operators[i].equals("*")) {
                		long product = (long) row1[i] * row2[i] * row3[i] * row4[i];
                		overallSum += product;
                	}
                	else if (operators[i].equals("+")) {
                		overallSum += row1[i] + row2[i] + row3[i] + row4[i];
                	}
                }
                System.out.println(overallSum);
		}
	

	}
	public static int[] parseNumRow(String s) {
		String[] stringArray = s.trim().split("\\s+");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
        	intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
	}

}
