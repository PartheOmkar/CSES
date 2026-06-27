package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MinimizingCoins {

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
		int sum = rd.nextInt();
		ArrayList<Integer> coins = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			coins.add(rd.nextInt());
		}
		rd.close();
		Collections.sort(coins);

		long dp[] = new long[sum + 1];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= sum; i++) {
			for (int coin : coins) {
				if (coin <= i) {
					if(dp[i-coin]==Long.MAX_VALUE) {
						continue;
					}
					dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
				} else {
					break;
				}
			}
		}
		if (dp[sum] == Long.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dp[sum]);
		}
	}

}
