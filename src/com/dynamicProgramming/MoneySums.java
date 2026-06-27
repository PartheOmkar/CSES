package com.dynamicProgramming;

import java.util.ArrayList;
import java.util.Scanner;

public class MoneySums {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		ArrayList<Integer> coins = new ArrayList<Integer>();
		int max = 0;
		int num;
		for (int i = 0; i < n; i++) {
			num = sc.nextInt();
			coins.add(num);
			max += num;
		}
		sc.close();
		
		boolean[][] outputHelper = new boolean[n + 1][max + 1];
		outputHelper[0][0] = true;
		for (int row = 1; row <= n; row++) {
			int coin = coins.get(row - 1);
			for (int col = 0; col <= max; col++) {
				outputHelper[row][col] = outputHelper[row - 1][col];
				if (col >= coin) {
					outputHelper[row][col] = outputHelper[row][col] | outputHelper[row - 1][col - coin];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 1; i <= max; i++) {
			if (outputHelper[n][i]) {
				count++;
				sb.append(i).append(" ");
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}

}
