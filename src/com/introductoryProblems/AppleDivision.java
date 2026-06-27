package com.introductoryProblems;

import java.util.Scanner;

public class AppleDivision {

	public static long rec(int[] pi,long current_sum, long total_sum, int i) {
		if(i==0) {
			return Math.abs((total_sum-current_sum)-current_sum);
		}
		return Math.min(rec(pi,current_sum+pi[i],total_sum,i-1)
				, rec(pi,current_sum,total_sum,i-1));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] pi = new int[n];
		long total_sum = 0;
		for(int i=0;i<n;i++) {
			pi[i] = sc.nextInt();
			total_sum+=pi[i];
		}
		sc.close();
		System.out.println(rec(pi,0,total_sum,n-1));
	}

}
