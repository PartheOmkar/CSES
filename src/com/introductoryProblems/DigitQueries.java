package com.introductoryProblems;

import java.util.Scanner;

public class DigitQueries {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        //sc.nextLine(); // Consume the newline character

        while (t-- > 0) {
            long n = sc.nextLong();
            //sc.nextLine(); // Consume the newline character

            long start = 1;
            long length = 1;
            long count = 9;

            // Find the group of numbers to which the digit belongs
            while (n > length * count) {
                n -= length * count;
                start *= 10;
                length++;
                count *= 10;
            }

            // Find the specific number and digit within that group
            long number = start + (n - 1) / length;
            int digitIndex = (int) ((n - 1) % length);

            // Extract the digit and print it
            String numberStr = Long.toString(number);
            System.out.println(numberStr.charAt(digitIndex));
        }
    }
}
