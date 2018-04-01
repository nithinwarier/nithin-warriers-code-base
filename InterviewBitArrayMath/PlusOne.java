package com.androidians.interviewarraymath;

import java.util.ArrayList;

/**
 * Created by nithin on 07/03/18.
 */

public class InterviewBitArrayMath {

    public static void main (String[] args) {
        int[] res = plusOne(new int[] {9,9});

        for (int i=0;i<res.length;i++) {
            System.out.print("\t"+res[i]);
        }
    }

    private static int[] plusOne(int[] A) {
        if (A == null || A.length == 0) return null;
        int[] result = new int[A.length+1];
        int additionFactor = 0;

        for (int i=A.length-1;i>=0;i--) {
            if (A[i] == 9) {
                result[i+1] = 0;
                additionFactor = 1;
            } else {
                if (i == A.length-1) {
                    additionFactor = 1;
                }

                result[i+1] = A[i] + additionFactor;
                additionFactor = 0;
            }
        }

        if (additionFactor == 1) {
            result[0] = 1;
        }

        return result;
    }

}
