package com.dynamicProgramming;

import java.util.Scanner;

public class FillingShapes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] outputHelper = new int[n+3];
		outputHelper[0] = outputHelper[1] = 0;
		outputHelper[2] = 2;
		if(n<=2) {
			 System.out.println(outputHelper[n]);
			 return;
		}
		for(int i=3;i<=n;i++) {
			outputHelper[i] = outputHelper[i-2]*2;
		}
		System.out.println(outputHelper[n]);
	}

}
