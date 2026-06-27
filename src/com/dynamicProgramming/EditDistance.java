package com.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int rown = str1.length();
		int coln = str2.length();
		
		int[][] outputHelper = new int[rown+1][coln+1];
		
		for(int i=0;i<=rown;i++) {
			outputHelper[i][0] = i;
		}
		for(int i=0;i<=coln;i++) {
			outputHelper[0][i] = i;
		}
		
		for(int row=1;row<=rown;row++) {
			for(int col=1;col<=coln;col++) {
				if(str1.charAt(row-1)==str2.charAt(col-1)) {
					outputHelper[row][col] = outputHelper[row-1][col-1];
				}else {
					// add or remove
					outputHelper[row][col] = Math.min(outputHelper[row][col-1]+1, outputHelper[row-1][col]+1);
					// replace
					outputHelper[row][col] = Math.min(outputHelper[row][col], outputHelper[row-1][col-1]+1);
				}
			}
		}
		System.out.println(outputHelper[rown][coln]);
	}

}
