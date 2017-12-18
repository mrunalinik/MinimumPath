package com.minimumpath;

import java.util.Iterator;
import java.util.List;

public class MatrixRowVisitor {
    private int row;
    private Matrix matrix;
    private BestPathHolder bestPathHolder;

    public MatrixRowVisitor(int startRow, Matrix matrix, BestPathHolder bestPathHolder) {
        if (matrix == null) {
            throw new IllegalArgumentException("A visitor requires a matrix");
        } else if (bestPathHolder == null) {
            throw new IllegalArgumentException("A visitor requires a collector");
        } else if (startRow > 0 && startRow <= matrix.getRowCount()) {
            this.row = startRow;
            this.matrix = matrix;
            this.bestPathHolder = bestPathHolder;
        } else {
            throw new IllegalArgumentException("Cannot visit a row outside of matrix boundaries");
        }
    }

    public PathState getBestPathForRow() {
        PathState initialPath = new PathState(matrix.getColumnCount());
        visitPathsForRow(row, initialPath);
        return bestPathHolder.getBestPath();
    }

    private void visitPathsForRow(int row, PathState path) {
        if (canVisitRowOnPath(row, path)) {
            visitRowOnPath(row, path);
        }

        List<Integer> adjacentRows = matrix.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;
        Iterator<Integer> iterator = adjacentRows.iterator();

        while (iterator.hasNext()) {
            int adjacentRow = iterator.next().intValue();
            if (canVisitRowOnPath(adjacentRow, path)) {
                PathState pathCopy = new PathState(path);
                visitPathsForRow(adjacentRow, pathCopy);
            } else if (!currentPathAdded) {
                bestPathHolder.addPath(path);
                currentPathAdded = true;
            }
        }
    }

    private boolean canVisitRowOnPath(int row, PathState path) {
        return !path.isComplete() && !nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    private void visitRowOnPath(int row, PathState path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, matrix.getValueForRowAndColumn(row, columnToVisit));
    }

    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, PathState path) {
        int nextColumn = path.getPathLength() + 1;
        return path.getTotalCost() + matrix.getValueForRowAndColumn(row, nextColumn) > PathState.MAXIMUM_COST;
    }
}
