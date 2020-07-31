package model;

import java.util.LinkedList;
import java.util.List;

public class IntervalTree {

    private IntervalTreeNode root;

    private static class IntervalTreeNode {
        private Interval value;
        private IntervalTreeNode left;
        private IntervalTreeNode right;
        private long max;

        public IntervalTreeNode(Interval value) {
            this.value = value;
        }

        public IntervalTreeNode getLeft() {
            return left;
        }

        public void setLeft(IntervalTreeNode left) {
            this.left = left;
        }

        public IntervalTreeNode getRight() {
            return right;
        }

        public void setRight(IntervalTreeNode right) {
            this.right = right;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public Interval getValue(){
            return this.value;
        }
    }

    public List<Interval> getConflict(Interval intervalToCheck) {
        List<Interval> conflicts = new LinkedList<>();
        System.out.println(intervalToCheck.toString());
        getConflictRecursive(intervalToCheck, conflicts, root);
        return conflicts;
    }

    public void printTree() {
        printTreeRecursive(root, ">>");
    }

    public void printTreeRecursive(IntervalTreeNode node, String prefix) {
           if (node.getLeft() != null) {
               printTreeRecursive(node.getLeft(), prefix+">>");
           }

        if (node.getRight() != null) {
            printTreeRecursive(node.getRight(), prefix+">>");
        }
    }

    private void getConflictRecursive(Interval intervalToCheck, List<Interval> conflicts, IntervalTreeNode subRoot) {
        IntervalTreeNode maybeIntersectNode = subRoot;

        while (maybeIntersectNode != null) {

            if (maybeIntersectNode.getValue().isIntersect(intervalToCheck)) {

                conflicts.add(maybeIntersectNode.getValue());
                if (maybeIntersectNode.getLeft() != null) {
                    getConflictRecursive(intervalToCheck, conflicts, maybeIntersectNode.getLeft());
                }
                if  (maybeIntersectNode.getRight() != null) {
                    getConflictRecursive(intervalToCheck, conflicts, maybeIntersectNode.getRight());
                }
                maybeIntersectNode = null;
            } else if (maybeIntersectNode.getLeft() == null) {
                maybeIntersectNode = maybeIntersectNode.getRight();
            } else if (maybeIntersectNode.getLeft().getMax() < intervalToCheck.getStart()){
                maybeIntersectNode = maybeIntersectNode.getRight();
            } else {
                maybeIntersectNode = maybeIntersectNode.getLeft();
            }
        }
    }

    public void add(Interval intervalToAdd) {
        if (root == null) {
            root = new IntervalTreeNode(intervalToAdd);
            root.setMax(intervalToAdd.getEnd());
            return;
        }

        IntervalTreeNode currentNode = root;
        while (true) {
            final long mayNewMax = intervalToAdd.getEnd();
            if (mayNewMax > currentNode.getMax()) {
                currentNode.setMax(mayNewMax);
                break;
            }
            if (currentNode.getValue().compareTo(intervalToAdd) >= 0) {
                if (currentNode.getRight() == null) {
                    IntervalTreeNode rightChild = new IntervalTreeNode(intervalToAdd);
                    rightChild.setMax(intervalToAdd.getEnd());
                    currentNode.setRight(rightChild);
                    break;
                } else {
                    currentNode = currentNode.getRight();
                }
            } else {
                if (currentNode.getLeft() == null) {
                    IntervalTreeNode leftChild = new IntervalTreeNode(intervalToAdd);
                    leftChild.setMax(intervalToAdd.getEnd());
                    currentNode.setLeft(leftChild);
                    break;
                } else {
                    currentNode = currentNode.getLeft();
                }

            }
        }
    }
}
