package de.sebastianbezold.intervalmerge;

import java.util.ArrayList;
import java.util.List;

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

        return List.of(
            mergeRecursive(intervalsToMerge.get(0), intervalsToMerge.subList(1, intervalsToMerge.size()))
        );
    }

    private static Interval mergeRecursive(Interval interval, List<Interval> rest) {
        if (rest.size() == 0) {
            return interval;
        }

        if (rest.size() == 1) {
            return mergeIntervals(interval, rest.get(0));
        }

        return mergeRecursive(mergeIntervals(interval, rest.get(0)), rest.subList(1, rest.size()));
    }

    private static Interval mergeIntervals(Interval a, Interval b) {
        return new Interval(a.lowerBound, b.upperBound);
    }

}
