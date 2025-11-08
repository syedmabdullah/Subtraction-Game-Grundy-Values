import java.util.*;

public class SGValueCalcV3 {

    // Function to calculate the Minimum Excludant (Mex)
    static int calculateMex(HashSet<Integer> set) {
        int mex = 0;
        while (set.contains(mex)) {
            mex++;
        }
        return mex;
    }

    // Function to compute Grundy numbers for all positions up to maxN
    static int calculateGrundy(int n, int[] grundy, int[] moves) {
        if (grundy[n] != -1) return grundy[n]; // Return memoized result

        HashSet<Integer> nextStates = new HashSet<>();

        for (int move : moves) {
            if (n >= move) {
                nextStates.add(calculateGrundy(n - move, grundy, moves));
            }
        }

        grundy[n] = calculateMex(nextStates);
        return grundy[n];
    }

    // Function to find periodicity in a string
    static void findPeriodicity(String grundySequence) {
        int n = grundySequence.length();

        for (int p = 1; p <= n / 2; p++) { // p is the potential periodic unit length
            String unit = grundySequence.substring(0, p);

            // Check if the sequence consists of the repeating unit
            boolean isPeriodic = true;
            for (int i = 0; i + p <= n; i += p) {
                if (!grundySequence.substring(i, i + p).equals(unit)) {
                    isPeriodic = false;
                    break;
                }
            }

            // If periodicity is found, print results and return
            if (isPeriodic) {
                System.out.println("Periodicity detected:");
                System.out.println("Repeated unit: " + encodeUnit(unit));
                System.out.println("p = " + p);
                return;
            }
        }
        System.out.println("Couldn't find periodicity.");
    }

    // Function to encode the unit in the desired format (e.g., "0*1, 1*1, 2*1, 3*1, 2*1")
    static String encodeUnit(String unit) {
        StringBuilder encoded = new StringBuilder();
        int count = 1;

        for (int i = 1; i < unit.length(); i++) {
            if (unit.charAt(i) == unit.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(unit.charAt(i - 1)).append("*").append(count).append(", ");
                count = 1;
            }
        }
        // Append the last segment
        encoded.append(unit.charAt(unit.length() - 1)).append("*").append(count);

        return encoded.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for allowed moves
        System.out.println("Enter 3 integers representing the allowed moves (separated by spaces):");
        int[] allowedMoves = new int[3];
        for (int i = 0; i < 3; i++) {
            allowedMoves[i] = scanner.nextInt();
        }

        // Take user input for the maximum pile size to compute SG values
        System.out.print("Enter the maximum pile size to compute SG values: ");
        int maxPileSize = scanner.nextInt();

        // Initialize Grundy array with -1 for memoization
        int[] grundy = new int[maxPileSize + 1];
        Arrays.fill(grundy, -1);
        grundy[0] = 0; // Base case: Grundy(0) = 0

        // Compute Grundy numbers for all values up to maxPileSize
        for (int i = 1; i <= maxPileSize; i++) {
            calculateGrundy(i, grundy, allowedMoves);
        }

        // Output all Grundy values as a single string (without spaces)
        StringBuilder grundySequence = new StringBuilder();
        for (int i = 0; i <= maxPileSize; i++) {
            grundySequence.append(grundy[i]); // Append without space
        }

        System.out.println("\nGrundy Sequence: " + grundySequence.toString());

        // Find and display periodicity
        findPeriodicity(grundySequence.toString());

        scanner.close();
    }
}
