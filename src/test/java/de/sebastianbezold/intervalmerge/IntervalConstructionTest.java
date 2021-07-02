package de.sebastianbezold.intervalmerge;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntervalConstructionTest {

    @Test
    void shouldSetLowerAndUpperBoundCorrectly() {
        assertThat(new Interval(5, 1).lowerBound, is(1));
        assertThat(new Interval(5, 1).upperBound, is(5));
        assertThat(new Interval(-1, -5).lowerBound, is(-5));
        assertThat(new Interval(-1, -5).upperBound, is(-1));
    }

}
