package com.minimumpath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathState {

    public static int MAXIMUM_COST = 50;
    private List<Integer> rowsTraversed = new ArrayList();
    private int totalCost = 0;
    private int expectedLength = 0;

    public PathState(int expectedLength) {
        this.expectedLength = expectedLength;
    }

    public PathState(PathState anotherPathState) {
        totalCost = anotherPathState.totalCost;
        expectedLength = anotherPathState.expectedLength;
        Iterator<Integer> iterator = anotherPathState.rowsTraversed.iterator();

        while (iterator.hasNext()) {
            int rowTraversed = iterator.next().intValue();
            rowsTraversed.add(Integer.valueOf(rowTraversed));
        }
    }

    public List<Integer> getRowsTraversed() {
        return rowsTraversed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getPathLength() {
        return rowsTraversed.size();
    }

    public void addRowWithCost(int row, int cost) {
        rowsTraversed.add(Integer.valueOf(row));
        totalCost += cost;
    }

    public boolean isComplete() {
        return rowsTraversed.size() == expectedLength;
    }

    public boolean isSuccessful() {
        return isComplete() && !isOverCost();
    }

    public boolean isOverCost() {
        return totalCost > MAXIMUM_COST;
    }
}
