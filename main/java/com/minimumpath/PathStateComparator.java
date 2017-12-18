package com.minimumpath;

import java.util.Comparator;

public class PathStateComparator implements Comparator<PathState> {
    private static int SORT_LEFT_FIRST = -1;
    private static int SORT_RIGHT_FIRST = 1;
    private static int SORT_EQUAL = 0;

    @Override
    public int compare(PathState leftPath, PathState rightPath) {
        int comparedLength = compareLengths(leftPath, rightPath);
        return comparedLength != 0 ? comparedLength : compareCosts(leftPath, rightPath);
    }

    private int compareLengths(PathState leftPath, PathState rightPath) {
        int leftLength = getLengthFromPath(leftPath);
        int rightLength = getLengthFromPath(rightPath);
        return leftLength > rightLength ? SORT_LEFT_FIRST : (leftLength == rightLength ? SORT_EQUAL : SORT_RIGHT_FIRST);
    }

    private int compareCosts(PathState leftPath, PathState rightPath) {
        int leftCost = getCostFromPath(leftPath);
        int rightCost = getCostFromPath(rightPath);
        return leftCost < rightCost ? SORT_LEFT_FIRST : (leftCost == rightCost ? SORT_EQUAL : SORT_RIGHT_FIRST);
    }

    private int getLengthFromPath(PathState path) {
        return path != null ? path.getPathLength() : Integer.MIN_VALUE;
    }

    private int getCostFromPath(PathState path) {
        return path != null ? path.getTotalCost() : Integer.MAX_VALUE;
    }
}
