import model.Event;
import model.Interval;
import util.IntervalUtil;

import java.util.LinkedList;
import java.util.List;

public class Main {

  public static void main(String[] args) {

      Event e1= new Event(2, 5);
      Event e2= new Event(4, 7);
      Event e3= new Event(2, 7);
      Event e4= new Event(10, 15);
      Event e5= new Event(4, 5);
      Event e6= new Event(4, 50);

      List<Interval> intervals = new LinkedList<>();
      intervals.add(e1);
      intervals.add(e2);
      intervals.add(e3);
      intervals.add(e4);
      intervals.add(e5);
      intervals.add(e6);
      List<List<Interval>> conflictingPairs = IntervalUtil.findAllConflictingEvents(intervals);
      System.out.println(conflictingPairs);

      //should be [({start=4, end=7}, {start=2, end=5}), ({start=2, end=7}, {start=2, end=5}), ({start=4, end=5}, {start=2, end=5}), ({start=4, end=5}, {start=2, end=7}), ({start=4, end=50}, {start=2, end=5}), ({start=4, end=50}, {start=2, end=7}), ({start=4, end=50}, {start=4, end=5})]
      // Java doesn't have a built in unit test framework, in order not to use any 3rd party dependencies, I'll skill unit test for now
  }
}
