package com.ncr.test.pyramid.solver.impl;

import com.ncr.test.pyramid.data.Pyramid;
import com.ncr.test.pyramid.solver.PyramidSolver;

/**
 * TASK: This is your 3rd task.
 * Please implement the class to satisfy the interface. *
 */
public class YourSolver implements PyramidSolver {

    @Override
    public long pyramidMaximumTotal(Pyramid pyramid) {
        return getTotalAbove(pyramid);
    }

    private long getTotalAbove(Pyramid pyramid) {
        long[][] cache = new long[pyramid.getRows()][pyramid.getRows()];
        int rowsOfPyramid = pyramid.getRows();
        // take row 0, which is end(inverse base) of pyramid and insert it in cache on first row
        for (int c = 0; c < rowsOfPyramid; c++) {
            cache[0][c] = pyramid.get(0, c);
        }

        // We start with second row, index 1, so we can start counting paths, and go downwards.
        for (int r = 1; r < rowsOfPyramid; r++) {
            int columnsInRow = rowsOfPyramid - r; // every time we go down, we have to remove one column
            for (int c = 0; c < columnsInRow; c++) {
                int val = pyramid.get(r, c);
                long greaterParent = Math.max(cache[r - 1][c], cache[r - 1][c + 1]);
                cache[r][c] = greaterParent + val;
            }
        }
        // we go from top to bottom, so last row, column 0 is the answer
        return cache[rowsOfPyramid - 1][0];
    }
}
