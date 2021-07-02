package de.sebastianbezold.intervalmerge;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntervalOverlapTest {

    @ParameterizedTest
    @CsvSource({
        "0, 1, 2, 3",
        "10, 20, 100, 101",
        "20, 30, 0, 10",
        "100, 101, 98, 99",
    })
    void shouldNotOverlap(String lowerA, String upperA, String lowerB, String upperB) {
        int lowerBoundA = Integer.parseInt(lowerA);
        int upperBoundA = Integer.parseInt(upperA);
        int lowerBoundB = Integer.parseInt(lowerB);
        int upperBoundB = Integer.parseInt(upperB);

        Interval first = new Interval(lowerBoundA, upperBoundA);
        Interval second = new Interval(lowerBoundB, upperBoundB);

        assertThat(first.overlaps(second), is(false));
    }

    @ParameterizedTest
    @CsvSource({
        "13, 20, 15, 40",
        "50, 60, 49, 51",
        "1, 2, 1, 2"
    })
    void shouldOverlap(String lowerA, String upperA, String lowerB, String upperB) {
        int lowerBoundA = Integer.parseInt(lowerA);
        int upperBoundA = Integer.parseInt(upperA);
        int lowerBoundB = Integer.parseInt(lowerB);
        int upperBoundB = Integer.parseInt(upperB);

        Interval first = new Interval(lowerBoundA, upperBoundA);
        Interval second = new Interval(lowerBoundB, upperBoundB);

        assertThat(first.overlaps(second), is(true));
    }
}
