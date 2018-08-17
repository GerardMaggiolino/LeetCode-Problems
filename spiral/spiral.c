/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements 
 * of the matrix in spiral order.
 *
 * Example 1:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Example 2:
 * Input:
 * [
 *  [1, 2, 3, 4],
 *  [5, 6, 7, 8],
 *  [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * Solution: 
 * General algorithm idea - for an m by n grid, set bounds x, y = 0,  xb = m,
 * and yb = n. Traversal from 
 * 1. (x,y) to (xb, y), y++ 
 * 2. (xb, y) to (xb, yb), xb--
 * 3. (xb, yb) to (x, yb), yb--
 * 4. (x, yb) to (x, y), x++
 * Repeat. To prevent traversing backwards onto the same row / column, steps
 * 3, 4, and 1 check number of elements traversed prior to execution.
 */

#include <stdio.h>
#include <stdlib.h>

#define COLS 4
#define ROWS 3

int* spiralOrder(int** matrix, int matrixRowSize, int matrixColSize);

int main (int argc, char* argv[]) { 

    int A1[COLS] = {1, 2, 3, 4};
    int A2[COLS] = {5, 6, 7, 8};
    int A3[COLS] = {9, 10, 11, 12};
    int* A[ROWS] = {A1, A2, A3};

    int* result = spiralOrder(A, ROWS, COLS); 
    for (int i = 0; i < ROWS * COLS; i++) { 
        printf("%d ", result[i]); 
    }
    printf("\n"); 

    return 0; 
}


int* spiralOrder(int** matrix, int matrixRowSize, int matrixColSize) { 
    int numElem = matrixRowSize * matrixColSize; 
    int* spiral = (int*)malloc(sizeof(int) * numElem);
    int row = 0, col = 0;
    int rowBound = matrixRowSize - 1, colBound = matrixColSize -1; 
    int ind = 0; 

    while (ind < numElem) { 
        for (int x = col; x <= colBound; x++) { 
            spiral[ind++] = matrix[row][x];
        }
        row++; 

        for (int y = row; y <= rowBound; y++) { 
            spiral[ind++] = matrix[y][colBound];
        }
        colBound--;

        if (ind >= numElem) break;
        for (int x = colBound; x >= col; x--) { 
            spiral[ind++] = matrix[rowBound][x];
        }
        rowBound--; 
        
        if (ind >= numElem) break;
        for (int y = rowBound; y >= row; y--) { 
            spiral[ind++] = matrix[y][col];
        }
        col++; 
    }

    return spiral; 
}
