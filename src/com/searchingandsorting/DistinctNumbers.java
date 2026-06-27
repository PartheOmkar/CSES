package com.searchingandsorting;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DistinctNumbers {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Set<Integer> nums = new HashSet<Integer>();
		for(int i=0;i<n;i++) {
			nums.add(sc.nextInt());
		}
		sc.close();
		System.out.println(nums.size());
	}

}
