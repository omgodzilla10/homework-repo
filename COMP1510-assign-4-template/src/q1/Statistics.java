package q1;

import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * <p>
 * This program accepts user input for a sample of integers, and prints out the
 * standard deviation of the numbers entered.
 * </p>
 *
 * @author Trevor Hoefsloot
 * @version 1.0
 */
public class Statistics {
    /** The maximum number of integers that can be inputted by the user. */
    static final int MAX_SAMPLES = 50;

    /**
     * A power that an integer sample is raised to in the process of determining
     * the variance of a sample of numbers.
     */
    static final int SQUARE = 2;

    /** The current number of integers entered into the array. */
    private int numValues;

    /** The array in which the sample of integers are stored. */
    private int[] intSamples;

    /** A scanner object, for scanning in the integer samples. */
    private Scanner scan;

    /**
     * Used to format the final standard deviation to two decimal places.
     */
    private NumberFormat decFormat;

    /** The constructor method. */
    public Statistics() {
        numValues = 0;

        intSamples = new int[MAX_SAMPLES];
        scan = new Scanner(System.in);
        decFormat = new DecimalFormat("0.##");
    }

    /**
     * <p>
     * This method is used to gather all of the integer samples inputted through
     * System.in, and store each integer into an array.
     * </p>
     */
    private void scanData() {
        // Store each integer in the array's next available index.
        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                if (numValues < MAX_SAMPLES) {
                    intSamples[numValues] = scan.nextInt();
                    numValues++;
                } else {
                    // If more than 50 integers are entered.
                    throw new IllegalArgumentException("Exceeded maximum " 
                    + "number of integers (Max: 50).");
                }
            } else {
                // If a scanned value is not an integer.
                throw new IllegalArgumentException("All entered values must be" 
                + " integers.");
            }
        }
    }

    /**
     * <p>
     * This method is used to calculate and print out the standard deviation of
     * the number sample.
     * </p>
     */
    private void printStandardDeviation() {
        double mean = 0;
        double standardDev = 0;

        /*
         * A temporary array for holding the integer samples while performing
         * calculations on them.
         */
        double[] tempSamples = new double[numValues];

        // Copy each value of the original array into the temporary array.
        for (int i = 0; i < numValues; i++) {
            tempSamples[i] = intSamples[i];

            // Sum up all of the numbers sampled, in order to find the mean.
            mean += tempSamples[i];
        }

        // Calculate the mean.
        mean /= numValues;

        // Subtract the mean from each integer and square the result.
        for (int i = 0; i < numValues; i++) {
            tempSamples[i] -= mean;
            tempSamples[i] = Math.pow(tempSamples[i], SQUARE);

            // Add value onto standard deviation variable.
            standardDev += tempSamples[i];
        }

        // Calculate the standard deviation.
        standardDev /= (numValues - 1);
        standardDev = Math.sqrt(standardDev);

        // Print out the final answer.
        System.out.println("The standard deviation is: " 
        + decFormat.format(standardDev));
    }

    /**
     * <p>
     * This is the main method (entry point) that gets called by the JVM.
     * </p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Statistics stats = new Statistics();

        stats.scanData();
        stats.printStandardDeviation();

        System.out.println("Question one was called and ran successfully.");
    }
};
