package com.introductoryProblems;

import java.util.Scanner;

public class MexGridConstruction {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i == 0) {
					sb.append(j).append(" ");
				}else {
					sb.append(i^j).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

}
