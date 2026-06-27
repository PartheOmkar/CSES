package com.introductoryProblems;

import java.io.IOException;
import java.util.Scanner;

public class TowerOfHanoi {
	static int count = 0;
	public static void towerRec(int n, String source, String des, String mid) {
		if(n==0) {
			return;
		}
		towerRec(n-1, source,mid,des);
		System.out.println(source+" "+des);
		count++;
		towerRec(n-1, mid, des,source);
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String source = "1";
		String mid = "2";
		String des = "3";
		int n = sc.nextInt();
		System.out.println((1<<n)-1);
		towerRec(n,source,des,mid);
	}

}
