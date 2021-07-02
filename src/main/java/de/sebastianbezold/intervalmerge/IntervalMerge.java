package de.sebastianbezold.intervalmerge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class IntervalMerge {

    /**
     * {@code IntervalMerge#merge} takes a list of intervals as input and merges all overlapping intervals so that resulting output
     * will only contain mutually exclusive intervals.
     *
     * Examples:
     *  merge([1,3], [2,5])          -> [1,5]
     *  merge([1,3], [2,5], [7, 10]) -> [1,5], [1,10]
     *  merge([1,3], [3,4])          -> [1,4]
     *
     * @param intervalsToMerge the list of intervals to merge
     * @return a list of intervals that only contains mutually exclusive intervals build from the intervalsToMerge
     */
    public List<Interval> merge(List<Interval> intervalsToMerge) {
        if (intervalsToMerge == null || intervalsToMerge.isEmpty()) {
            return new ArrayList<>();
        }

        if (intervalsToMerge.size() == 1) {
            return new ArrayList<>(intervalsToMerge);
        }

        intervalsToMerge.sort(Comparator.comparingInt(a -> a.lowerBound));

        List<Interval> resultingIntervals = new ArrayList<>();
        for (int i = 0; i < intervalsToMerge.size() - 1; i++) {
            Interval current = intervalsToMerge.get(i);
            Interval next = intervalsToMerge.get(i + 1);
            if (current.overlaps(next)) {
                int newLowerBound = min(current.lowerBound, next.lowerBound);
                int newUpperBound = max(current.upperBound, next.upperBound);
                intervalsToMerge.set(i + 1, new Interval(newLowerBound, newUpperBound));
            } else {
                resultingIntervals.add(current);
            }
        }
        resultingIntervals.add(intervalsToMerge.get(intervalsToMerge.size() - 1));

        return resultingIntervals;
    }

    private static List<Interval> internalMerge(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>(intervals);

        for (int i = mergedIntervals.size() - 1; i > 0; i--) {
            Interval current = mergedIntervals.get(i);
            Interval previous = mergedIntervals.get(i - 1);
            if (current.overlaps(previous)) {
                int newLowerBound = min(current.lowerBound, previous.lowerBound);
                int newUpperBound = min(current.upperBound, previous.upperBound);
                mergedIntervals.set(i - 1, new Interval(newLowerBound, newUpperBound));
                mergedIntervals.remove(i);
            }
        }

        return mergedIntervals;
    }
}
