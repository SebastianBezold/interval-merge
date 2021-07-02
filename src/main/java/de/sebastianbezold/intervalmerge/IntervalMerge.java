package de.sebastianbezold.intervalmerge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

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
        Stack<Interval> stack = new Stack<>();
        stack.push(intervalsToMerge.get(0));

        // On a sorted list, we can just check if the upper bound of the n'th element is bigger
        // than the n+1'th elements lower bound. If so, they do overlap.
        for (int i = 1; i < intervalsToMerge.size(); i++) {
            Interval top = stack.peek();
            Interval current = intervalsToMerge.get(i);

            // in ordered list -> first elements upper does not reach second elements lower means the do not overlap
            // so add it as fully merged interval
            if (top.upperBound < current.lowerBound) {
                stack.push(current);
            }
            // if the upper of the first element inside the second elements range push a merged element on top
            else if (top.upperBound < current.upperBound) {
                stack.pop();
                stack.push(new Interval(top.lowerBound, current.upperBound));
            }
        }

        return new ArrayList<>(stack);
    }
}
