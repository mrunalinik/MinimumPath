package com.minimumpath;

import java.util.ArrayList;
import java.util.Collections;

public class MatrixVisitor {

    private Matrix matrix;
    private PathStateComparator pathComparator;

    public MatrixVisitor(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("A visitor requires a matrix");
        } else {
            this.matrix = matrix;
            pathComparator = new PathStateComparator();
        }
    }

    public PathState getBestPathForMatrix() {
        ArrayList<PathState> allPaths = new ArrayList<PathState>();

        for (int row = 1; row <= this.matrix.getRowCount(); ++row) {
            MatrixRowVisitor visitor = new MatrixRowVisitor(row, matrix, new BestPathHolder());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, pathComparator);
        return allPaths.get(0);
    }
}
