package com.minimumpath;

import android.text.TextUtils;

public class Utils {

    public static Matrix createMatrixFrom(String data) throws Exception {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        String[] rowsData = data.split("[\\r\\n]+");
        String[] firstRowData = rowsData[0].split("\\s+");
        final int rows = rowsData.length;
        final int columns = firstRowData.length;
        int[][] values = new int[rows][columns];
        for (int i = 0; i < columns; i++) {
            values[0][i] = Integer.parseInt(firstRowData[i]);
        }
        for (int i = 1; i < rows; i++) {
            final String[] rowData = rowsData[i].split("\\s+");
            if (rowData.length != columns) {
                throw new Exception("Invalid Data");
            }
            for (int j = 0; j < columns; j++) {
                values[i][j] = Integer.parseInt(rowData[j]);
            }
        }
        Matrix matrix = new Matrix(values);
        return matrix;
    }
}
