package model;

public interface Interval extends Comparable {
    long getStart();
    long getEnd();
    boolean isIntersect(Interval anotherInterval);
}
