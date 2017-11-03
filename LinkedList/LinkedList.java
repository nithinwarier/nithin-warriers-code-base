package com.smartandroidians.myloci.utils;

/**
 * Created by vichu on 24/10/17.
 */

public class LinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args) {
        Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);

        Node n1 = new Node(5);
        n1.next = new Node(3);
        n1.next.next = new Node(6);

        reverseInPairs(n1);
    }

    static int count(Node head) {
        if (head == null) return 0;
        return 1 + count(head.next);
    }

    static void reverse(Node head) {
        if (head == null) return;
        reverse(head.next);
        System.out.println(head.data);
    }

    static void reverseInPairs(Node head) {
        if (head == null) return;
        if (head != null && head.next != null) {
            System.out.println(head.next.data);
            System.out.println(head.data);
        } else if (head != null){
            System.out.println(head.data);
        }

        if (head.next != null)
            reverseInPairs(head.next.next);
        else
            reverseInPairs(head.next);
    }

    static void printValues(Node head) {
        if (head == null) return;
        System.out.println(head.data);
        printValues(head.next);
    }

    static void reverse(Node head, int count) {
        if (head == null) return;
        count += 1;
        reverse(head.next, count);
        System.out.println(count);
    }
}
