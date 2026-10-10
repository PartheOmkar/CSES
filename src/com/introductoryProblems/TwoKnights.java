package com.introductoryProblems;


// count the 3*2 and 2*3 square in matrix
import java.util.Scanner;

public class TwoKnights {

	public static long getCount(int n) {
		
		return (((n*n)*((n*n)-1))-((((n-2)*(n-1))*2)*4))/2;
	}
	
	public static long getCount2(int n) {
		
		double x = n*n;
		
		return (long) ((((x-1)*(x/2))) - (((n-2)*(n-1))*4));
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		
		
		for(int i=1;i<=n;i++) {
			if(i==1) {
				sb.append("0\n");
				continue;
			}
			long ans = getCount2(i);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

}
