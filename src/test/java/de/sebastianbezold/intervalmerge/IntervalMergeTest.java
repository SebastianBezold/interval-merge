package de.sebastianbezold.intervalmerge;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class IntervalMergeTest {

    private List<Interval> intervalsToMerge = null;
    private List<Interval> mergedIntervals = null;

    @Test
    void shouldReturnEmptyListForEmptyInput() {
        givenIntervalsToMerge(null);

        whenMergingIntervals();

        assertThat(mergedIntervals, is(not(nullValue())));
        assertThat(mergedIntervals.isEmpty(), is(true));
    }

    @Test
    void shouldReturnUnchangedIntervalOnSingleIntervalInput() {
        givenIntervalsToMerge(new Interval(0, 1));

        whenMergingIntervals();

        thenMergedIntervalsContain(new Interval(0, 1));
    }

    @Test
    void shouldMergeIntervals() {
        givenIntervalsToMerge(
            new Interval(0, 5),
            new Interval(3, 7),
            new Interval(4, 9)
        );

        whenMergingIntervals();

        thenNumberOfResultingMergedIntervalsIs(1);
        thenMergedIntervalsContain(new Interval(0, 9));
    }

    private void givenIntervalsToMerge(Interval... intervals) {
        if (intervals != null) {
            this.intervalsToMerge = Arrays.asList(intervals);
        }
    }

    private void whenMergingIntervals() {
        this.mergedIntervals = new IntervalMerge().merge(intervalsToMerge);
    }

    private void thenNumberOfResultingMergedIntervalsIs(int count) {
        assertThat(mergedIntervals.size(), is(count));
    }

    private void thenMergedIntervalsContain(Interval interval) {
        assertThat(mergedIntervals, hasItem(interval));
    }
}
