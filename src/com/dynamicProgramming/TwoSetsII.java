package com.dynamicProgramming;

import java.util.Scanner;

public class TwoSetsII {

	public static final int MOD = 1000000007;
	
	public static int findAns(int n, int totalSum) {
		int[][] data = new int[n+1][totalSum+1];

		data[0][0] = 1;
		for(int row = 1;row<=n;row++) {
			for(int col=0;col<=totalSum;col++) {
				data[row][col] = data[row-1][col];
				if(col>=row) {
					data[row][col] = (data[row][col]%MOD+data[row-1][col-row]%MOD)%MOD;
				}
			}
		}
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=totalSum;j++) {
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
		return data[n-1][totalSum];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		int totalSum = (n*(n+1))/2;
		if(totalSum%2!=0) {
			System.out.println(0);
			return;
		}
		
		totalSum = totalSum/2;
	
		long count = findAns(n, totalSum);
		
		System.out.println(count);
	}

}
