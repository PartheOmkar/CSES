package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class RemovingDigits {

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
	
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		rd.close();
		int dp[] = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		int current = 0;
		for(int i=0;i<=n;i++) {
			if(i<10) {
				dp[i] = 1;
			}
			current = i;
			while(current!=0) {
				if(current%10!=0) {
					dp[i] = Math.min(dp[i], dp[i-(current%10)]+1);
				}
				current /= 10;
			}
		}
		System.out.println(dp[n]);
	}

}
