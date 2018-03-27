package com.androidians.sorting;

/**
 * Created by nithin on 01/03/18.
 */

public class Sorting {

    private static void insertionSort(int[] A) {
        for (int i=1;i<A.length; i++) {
            int value = A[i];
            int hole = i;

            while (hole > 0 && A[hole-1] > value) {
                A[hole] = A[hole-1];
                hole = hole - 1;
            }

            A[hole] = value;
        }

        for (int i=0;i<A.length;i++) {
            System.out.print("\t"+A[i]);
        }
    }

    private static void merge(int[] A) {
        if (A == null || A.length < 2)
            return;
        int mid = A.length/2;
        int[] L = new int[mid];
        int[] R = new int[A.length-mid];
        int i=0;
        for (i=0; i<mid; i++) {
            L[i] = A[i];
        }
        for (; i<A.length; i++) {
            R[i-mid] = A[i];
        }

        merge(L);
        merge(R);
        mergeSort(A, L, R);
    }

    private static void mergeSort(int[] A, int[] L, int[] R) {
        int i = 0, j=0, k = 0;
        int nL = L.length;
        int nR = R.length;

        while (i<nL && j<nR) {
            if (L[i] < R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            k++;
        }

        while (i<nL) {
            A[k] = L[i];
            i++;
            k++;
        }
        while (j<nR) {
            A[k] = R[j];
            k++;
            j++;
        }
    }

    private static void quickSort(int[] A, int sIndex, int eIndex) {
        if (sIndex < eIndex) {
            int pIndex = partition(A, sIndex, eIndex);
            quickSort(A, 0, pIndex-1);
            quickSort(A, pIndex+1, eIndex);
        }
    }

    private static int partition(int[] A, int sIndex, int eIndex) {
        int pIndex = sIndex;
        int pivot = A[eIndex];
        for (int i=sIndex; i<=eIndex-1; i++) {
            if (A[i] <= pivot) {
                int temp = A[pIndex];
                A[pIndex] = A[i];
                A[i] = temp;

                pIndex++;
            }
        }
        int temp = A[eIndex];
        A[eIndex] = A[pIndex];
        A[pIndex] = temp;

        return pIndex;
    }

    private static int swap(int a, int b) {  // usage: y = swap(x, x=y);
        return a;
    }

    public static void main (String[] args) {
        int[] A = {7,2,1};
        System.out.println("Insertion Sort");
        insertionSort(A);
        System.out.println();
        System.out.println("Merge Sort");
        int[] B = {6,7,10,1,3,4,12,13};
        merge(B);

        for (int i=0;i<B.length;i++) {
            System.out.print("\t"+B[i]);
        }

        A = new int[] {7,2,1,6,8,5,3,4};
        System.out.println();
        System.out.println("Quick Sort");
        quickSort(A, 0, A.length-1);

        for (int i=0;i<A.length;i++) {
            System.out.print("\t"+A[i]);
        }
    }
}
