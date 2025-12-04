import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CodeLock {
	private static int counter = 0;
	private static int lock = 50;
    static void Combo(char letter, int number) {
    	int prevLock = lock;
    	boolean TF = false;
        if(letter == 'L') {
        	lock -= number;
        }
        else if (letter == 'R') {
        	lock += number;
        }
        else {
        	System.out.println("ERROR: Wrong letter");
        }
        while (lock >= 100) {
        	//System.out.println("lock was" + " " +(lock - number) + " now is " + lock + " Upp");
        	lock -= 100;
    		counter += 1;
    		TF = true;
        	
        }
        while (lock < 0) {
    		counter += 1;
        	lock += 100;
        	TF = true;
        }
        if(lock == 0) {
        	counter += 1;
        	lock = 0;
        }
        if(prevLock == 0 && TF) {
    		counter -= 1;
    	}
        
    }
    static void Combo2(char letter, int number) {
        int start = lock;

        if (letter == 'R') {
            // first t>=1 with (start + t) % 100 == 0
            int first = (100 - start) % 100;
            if (first == 0) first = 100;                 // if start==0 the first real hit is at t=100
            int hits = (number < first) ? 0 : 1 + (number - first) / 100;
            counter += hits;
            lock = (start + number) % 100;

        } else if (letter == 'L') {
            // first t>=1 with (start - t) % 100 == 0  ->  t ≡ start (mod 100)
            int first = start % 100;
            if (first == 0) first = 100;
            int hits = (number < first) ? 0 : 1 + (number - first) / 100;
            counter += hits;
            // safe modulo to keep lock in 0..99
            lock = ((start - (number % 100)) % 100 + 100) % 100;

        } else {
            System.out.println("ERROR: Wrong letter");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("lockNums.txt");
    	//File file = new File("example.txt");
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                
                char first = line.charAt(0);
                int value = Integer.parseInt(line.substring(1));

                Combo2(first, value);
            }
        }
        System.out.println(counter);
    }
}