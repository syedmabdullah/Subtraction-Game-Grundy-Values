import java.util.*;

public class SGValueCalcV7 {

    // Function to calculate the Minimum Excludant (Mex)
    static int calculateMex(HashSet<Integer> set) {
        int mex = 0;
        while (set.contains(mex)) {
            mex++;
        }
        return mex;
    }

    // Iterative function to compute Grundy numbers
    static void calculateGrundyIterative(int maxN, int[] grundy, int[] moves) {
        grundy[0] = 0; // Base case

        for (int n = 1; n <= maxN; n++) {
            HashSet<Integer> nextStates = new HashSet<>();

            for (int move : moves) {
                if (n >= move) {
                    nextStates.add(grundy[n - move]);
                }
            }

            grundy[n] = calculateMex(nextStates);
        }
    }

    // Function to find periodicity starting from any index
    static void findTruePeriodicity(String grundySequence) {
        int n = grundySequence.length();

        for (int startIdx = 0; startIdx < n - 5; startIdx++) { // Try different starting points
            for (int p = 1; p <= (n - startIdx) / 5; p++) { // Try different period lengths
                String unit = grundySequence.substring(startIdx, startIdx + p);
                int count = 0;
                int index = startIdx + p;

                while (index + p <= n) {
                    if (grundySequence.substring(index, index + p).equals(unit)) {
                        count++;
                        if (count == 5) {  // Ensure periodicity appears at least 5 times
                            String nonPeriodicPart = grundySequence.substring(0, startIdx);

                            // Remove trailing copies of the periodic unit from the non-periodic part
                            while (nonPeriodicPart.endsWith(unit)) {
                                nonPeriodicPart = nonPeriodicPart.substring(0, nonPeriodicPart.length() - p);
                            }

                            System.out.println("✅ Correct Periodicity Found:");
                            System.out.println("Non-periodic part: " + (nonPeriodicPart.isEmpty() ? "-" : nonPeriodicPart));
                            System.out.println("Periodic part: " + unit);
                            System.out.println("Starts at index: " + startIdx);
                            System.out.println("Period length: " + p);
                            return;
                        }
                    } else {
                        break;  // Stop if periodicity is broken
                    }
                    index += p;
                }
            }
        }
        System.out.println("❌ No periodicity found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for allowed moves (4 instead of 3)
        System.out.println("Enter 4 integers representing the allowed moves (separated by spaces):");
        int[] allowedMoves = new int[4];
        for (int i = 0; i < 4; i++) {
            allowedMoves[i] = scanner.nextInt();
        }

        // Take user input for the maximum pile size to compute SG values
        System.out.print("Enter the maximum pile size to compute SG values: ");
        int maxPileSize = scanner.nextInt();

        // Initialize Grundy array with -1 for memoization
        int[] grundy = new int[maxPileSize + 1];
        Arrays.fill(grundy, -1);

        // Compute Grundy numbers iteratively
        calculateGrundyIterative(maxPileSize, grundy, allowedMoves);

        // Output all Grundy values as a single string (without spaces)
        StringBuilder grundySequence = new StringBuilder();
        for (int i = 0; i <= maxPileSize; i++) {
            grundySequence.append(grundy[i]); // Append without space
        }

        System.out.println("\n🔢 Full Grundy Sequence:");
        System.out.println(grundySequence.toString());

        // Find and display periodicity
        findTruePeriodicity(grundySequence.toString());

        scanner.close();
    }
}


