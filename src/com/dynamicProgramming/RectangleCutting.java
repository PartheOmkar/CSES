package com.dynamicProgramming;

import java.util.Scanner;

public class RectangleCutting {

	public static int dp[][];

	public static int outputHelper(int h, int w) {
		if (dp[h][w] != 0 || h==w) {
			return dp[h][w];
		}
		int ans = 250000;
		for (int row = 1; row <= (h>>1); row++) {
			ans = Math.min(outputHelper(row, w) + outputHelper(h - row, w) + 1, ans);
		}
		for (int col = 1; col <= (w>>1); col++) {
			ans = Math.min(outputHelper(h, col) + outputHelper(h, w - col) + 1, ans);
		}
		dp[h][w] = ans;
		return dp[h][w];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		dp = new int[h + 1][w + 1];
		System.out.println(outputHelper(h, w));
	}

}
