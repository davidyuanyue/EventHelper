package util;

import model.Interval;
import model.IntervalTree;

import java.util.LinkedList;
import java.util.List;

public class IntervalUtil {

    public static List<List<Interval>>  findAllConflictingEvents(final List<Interval> intervals) {
        IntervalTree  intervalTree = new IntervalTree();
        List<List<Interval>> conflictingPairs = new LinkedList<>();
        for (Interval interval : intervals) {

            List<Interval> conflictsForTheInterval = intervalTree.getConflict(interval);
            for (Interval conflictingInterval: conflictsForTheInterval) {
                List<Interval> conflictPair = new LinkedList<>();
                conflictPair.add(interval);
                conflictPair.add(conflictingInterval);
                conflictingPairs.add(conflictPair);
            }
            intervalTree.add(interval);
            intervalTree.printTree();
        }
        return conflictingPairs;
    }
}
