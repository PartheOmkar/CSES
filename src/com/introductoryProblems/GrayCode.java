package com.introductoryProblems;

import java.util.Scanner;

public class GrayCode {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		sc.close();
		int z = (1<<n);
		for(int i=0;i<z;i++) {
			String s = Integer.toBinaryString((i^(i>>1))|((1<<n)));
			sb.append(s.substring(1));
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
