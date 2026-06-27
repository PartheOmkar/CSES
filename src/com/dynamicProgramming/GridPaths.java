package com.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GridPaths {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] grid = new char[n][n];
		for(int i=0;i<n;i++) {
			String row = br.readLine();
			for(int j=0;j<n;j++) {
				grid[i][j] = row.charAt(j);
			}
		}
		br.close();
		final int mod = 1000000007;
		int[][] dp = new int[n+1][n+1];
		dp[0][1] = 1;
		for(int row=0;row<n;row++) {
			for(int col=0;col<n;col++) {
				if(grid[row][col]=='*') {
					continue;
				}
				dp[row+1][col+1] = (dp[row][col+1] +dp[row+1][col])%mod;
			}
		}
		
		System.out.println(dp[n][n]);
	}

}
