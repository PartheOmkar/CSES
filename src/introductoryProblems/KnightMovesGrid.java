package introductoryProblems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KnightMovesGrid {
	
	public static class Pair{
		int row;
		int col;
		int count;
		
		public Pair(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	
	public static boolean isValid(int row, int col, int n) {
		if(row >= n || col >= n || row < 0 || col < 0) {
			return false;
		}
		if(row ==0 && col == 0 ) {
			return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		
		int[][] board = new int[n+1][n+1];
	
		Queue<Pair> moves = new LinkedList<KnightMovesGrid.Pair>(); 
		
		Pair p = new Pair(0,0,0);
		moves.add(p);
		
		while(!moves.isEmpty()) {
			Pair p2 = moves.poll();
			
			//move up
			if(isValid(p2.row-2, p2.col-1, n) && board[p2.row-2][p2.col-1] == 0) {
				board[p2.row-2][p2.col-1] = p2.count+1;
				moves.add(new Pair(p2.row-2, p2.col-1, p2.count+1));
			}
			if(isValid(p2.row-2, p2.col+1, n) && board[p2.row-2][p2.col+1] == 0) {
				board[p2.row-2][p2.col+1] = p2.count+1;
				moves.add(new Pair(p2.row-2, p2.col+1, p2.count+1));
			}
			
			//move left
			if(isValid(p2.row-1, p2.col-2, n) && board[p2.row-1][p2.col-2] == 0) {
				board[p2.row-1][p2.col-2] = p2.count+1;
				moves.add(new Pair(p2.row-1, p2.col-2, p2.count+1));
			}
			if(isValid(p2.row+1, p2.col-2, n) && board[p2.row+1][p2.col-2] == 0) {
				board[p2.row+1][p2.col-2] = p2.count+1;
				moves.add(new Pair(p2.row+1, p2.col-2, p2.count+1));
			}
			
			//move right
			if(isValid(p2.row-1, p2.col+2, n) && board[p2.row-1][p2.col+2] == 0) {
				board[p2.row-1][p2.col+2] = p2.count+1;
				moves.add(new Pair(p2.row-1, p2.col+2, p2.count+1));
			}
			if(isValid(p2.row+1, p2.col+2, n) && board[p2.row+1][p2.col+2] == 0) {
				board[p2.row+1][p2.col+2] = p2.count+1;
				moves.add(new Pair(p2.row+1, p2.col+2, p2.count+1));
			}
			
			//move down
			if(isValid(p2.row+2, p2.col-1, n) && board[p2.row+2][p2.col-1] == 0) {
				board[p2.row+2][p2.col-1] = p2.count+1;
				moves.add(new Pair(p2.row+2, p2.col-1, p2.count+1));
			}
			if(isValid(p2.row+2, p2.col+1, n) && board[p2.row+2][p2.col+1] == 0) {
				board[p2.row+2][p2.col+1] = p2.count+1;
				moves.add(new Pair(p2.row+2, p2.col+1, p2.count+1));
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
