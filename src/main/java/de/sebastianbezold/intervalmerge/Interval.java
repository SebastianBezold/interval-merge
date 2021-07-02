package de.sebastianbezold.intervalmerge;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Interval {

    public final int lowerBound;
    public final int upperBound;

    public Interval(int lowerBound, int upperBound) {
        this.lowerBound = min(lowerBound, upperBound);
        this.upperBound = max(lowerBound, upperBound);
    }

    public boolean overlaps(Interval other) {
        return lowerBound <= other.upperBound && upperBound >= other.lowerBound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Interval interval = (Interval) o;
        return lowerBound == interval.lowerBound && upperBound == interval.upperBound;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerBound, upperBound);
    }

    @Override
    public String toString() {
        return "Interval{" +
            "lowerBound=" + lowerBound +
            ", upperBound=" + upperBound +
            '}';
    }
}
