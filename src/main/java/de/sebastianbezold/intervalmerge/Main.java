package de.sebastianbezold.intervalmerge;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class Main {

    /**
     * This main function is just for demonstration purpose. There was not much effort put in.
     * It relies heavily on the input format, which is a space separated list of intervals.
     * The intervals are surrounded by sqare brackets. Lower- and upperBound are devided by comma (,).
     * The interval must not contain spaces because this is treated as command separator and would reuslt in a new argument
     * @param args string representation of the intervals to merge.
     */
    public static void main(String[] args) {
        try {
            System.out.println("Reading intervals to merge from command line arguments..." + LocalDateTime.now());
            List<Interval> intervalsToMerge = readIntervalFromArgs(args);
            intervalsToMerge.forEach(System.out::println);

            System.out.println(lineSeparator() + "Merging intervals..." + LocalDateTime.now());
            List<Interval> mergedIntervals = new IntervalMerge().merge(intervalsToMerge);

            System.out.println(lineSeparator() + "Merged intervals are: " + LocalDateTime.now());
            mergedIntervals.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error while reading interval inputs occured: ");
            System.out.println("example usage java -jar interval-merge-1.0-SNAPSHOT.jar \"[1,3]\" \"[4,5]\" \"[2,7]\"");
            System.out.println(
                "Intervals defined with square brackets, lower and upper bound divided by comma (,) and no space in between");
            e.printStackTrace();
        }
    }

    private static List<Interval> readIntervalFromArgs(String[] args) {
        List<Interval> result = new ArrayList<>();
        for (String arg : args) {
            String numbers = arg.replace("[", "")
                .replace("]", "")
                .replace(" ", "");

            int lowerBound = Integer.parseInt(numbers.substring(0, numbers.indexOf(",")));
            int upperBound = Integer.parseInt(numbers.substring(numbers.indexOf(",") + 1));

            result.add(new Interval(lowerBound, upperBound));
        }
        return result;
    }
}
