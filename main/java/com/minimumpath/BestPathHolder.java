package com.minimumpath;


public class BestPathHolder {
    private PathState bestPath;
    private final PathStateComparator comparator = new PathStateComparator();

    public PathState getBestPath() {
        return bestPath;
    }

    public void addPath(PathState newPath) {
        if (comparator.compare(newPath, bestPath) < 0) {
            bestPath = newPath;
        }
    }
}
