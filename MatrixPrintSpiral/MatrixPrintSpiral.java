package com.androidians.linkedlist;

/**
 * Created by nithin on 25/02/18.
 */

public class MatrixPrintSpiral {

    static int[][] A = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16} };

    public static void main(String[] args) {
        printSpiral(A, 4, 4);
    }

    private static void printSpiral(int[][] A, int row, int col) {
        int TOP= 0, LEFT= 0, BOTTOM= row-1, RIGHT= col-1;
        int dir = 0;

        while (TOP<=BOTTOM && LEFT<=RIGHT) {
            if (dir == 0) {
                for (int i = LEFT;i<=RIGHT; i++)  {
                    System.out.println(A[TOP][i]);
                }
                TOP++;
                dir++;
            } else if (dir == 1) {
                for (int i = TOP;i<=BOTTOM; i++)  {
                    System.out.println(A[i][RIGHT]);
                }
                RIGHT--;
                dir++;
            } else if (dir == 2) {
                for (int i=RIGHT;i>=LEFT; i--) {
                    System.out.println(A[BOTTOM][i]);
                }
                BOTTOM--;
                dir++;
            } else if (dir == 3) {
                for (int i=BOTTOM;i>=TOP; i--) {
                    System.out.println(A[i][LEFT]);
                }
                LEFT++;
                dir++;
            }

            if (dir > 3)
                dir = dir%4;
        }
    }
}
