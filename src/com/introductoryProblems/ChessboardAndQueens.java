package com.introductoryProblems;

import java.util.Scanner;

public class ChessboardAndQueens {
	
	public static int placeQueen(int n,char[][] board,boolean[] cols,boolean[] leftCross, boolean[] rightCross, int row) {
		if(row==n) {
			return 1;
		}
		int count = 0;
		for(int i=0;i<n;i++) {
			int tempIndex = n-1+(i-row);
			if(cols[i] || leftCross[tempIndex]|| rightCross[i+row] || board[row][i]=='*') {
				continue;
			}
			
			board[row][i] = '#';
			cols[i] = true;
			leftCross[tempIndex] = true;
			rightCross[i+row] = true;
			
			count += placeQueen(n,board,cols,leftCross, rightCross,row+1);
	
			board[row][i] = '.';
			cols[i] = false;
			leftCross[tempIndex] = false;
			rightCross[i+row] = false;
		}
		return count;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 8;
		char[][] board = new char[n][n];
		for(int i=0;i<n;i++) {
			String s = sc.nextLine();
			for(int j=0;j<n;j++) {
				board[i][j] = s.charAt(j);
			}
		}
		boolean[] cols = new boolean[n];
		// abs(n-1+row-col)
		boolean[] leftCross = new boolean[15];
		// row+col
		boolean[] rightCross = new boolean[15];
		sc.close();
		System.out.println(placeQueen(n,board,cols,leftCross,rightCross,0));
		//System.out.println(Math.abs(7-6));
		
	}
}
