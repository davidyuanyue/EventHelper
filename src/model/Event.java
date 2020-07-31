package model;

public class Event implements Interval {
    private long start;
    private long end;

    public Event(long start, long end) {
        if (start > end) {
            throw new RuntimeException("start can not be greater than end");
        }
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    @Override
    public boolean isIntersect(Interval anotherInterval) {
        return (this.start <= anotherInterval.getStart() && anotherInterval.getStart() <= this.end)
                || (this.start <= anotherInterval.getEnd() &&  anotherInterval.getEnd() <= this.end);
    }

    @Override
    public int compareTo(Object other) {
        if (other == null || ! (other instanceof Event)) {
            return 0;
        }
        Event that = (Event)other;
        long delta = that.start - this.start;

        if (delta > 0) {
            return 1;
        } else if (delta < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
