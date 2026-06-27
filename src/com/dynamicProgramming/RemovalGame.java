package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RemovalGame {

	private static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ') {
				c = read();
			}
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}

	public static int ans;
	public static long outputhelper(ArrayList<Integer> nums,int start, int end) {
		if(start==end) {
			return nums.get(start);
		}
		if(end-start<=1) {
			return Math.max(nums.get(start), nums.get(end));
		}
//		4 5 1 3 
//		chose 4 then 4+(max 5-dp[],3-dp[]);
//		chose 3 then 3+(max 4-dp[],1-dp[]);
		long choseStart = nums.get(start)+ 
				Math.max(
						nums.get(start+1)-outputhelper(nums, start+1, end),
						nums.get(end)-outputhelper(nums, start+1, end));
		
		long choseEnd = nums.get(end)+
				Math.max(
						nums.get(end-1)-outputhelper(nums, start, end-1), 
						nums.get(start)-outputhelper(nums, start, end-1));
		return Math.max(choseStart, choseEnd);
	}
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int num;
		for(int i=0;i<n;i++) {
			num = rd.nextInt();
			nums.add(num);
		}
		rd.close();
		long ans = outputhelper(nums,0,n-1);
		System.out.println(ans);
	}

}
