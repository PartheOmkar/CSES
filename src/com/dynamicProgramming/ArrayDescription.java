package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ArrayDescription {

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

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					} else {
						continue;
					}
				}
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
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

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
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

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

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
		final int MOD = 1000000007;
		int n = rd.nextInt();
		int m = rd.nextInt();
		int[] nums = new int[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i] = rd.nextInt();
		}
		rd.close();
		int[][] outputHelper = new int[n+1][m + 1];

		if (nums[0] == 0) {
			for (int i = 0; i <= m; i++) {
				outputHelper[0][i] = 1;
			}
		} else {
			outputHelper[0][nums[0]] = 1;
		}
		outputHelper[0][0] = 0;
		for (int row = 1; row < n; row++) {
			if (nums[row] == 0) {
				for (int col = 1; col <= m; col++) {
					if (col >= 1) {
						outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col - 1]) % MOD;
					}
					if (col < m) {
						outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col + 1]) % MOD;
					}
					outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col]) % MOD;
				}
			} else {
				int col = nums[row];
				if (col >= 1) {
					outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col - 1]) % MOD;
				}
				if (col < m) {
					outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col + 1]) % MOD;
				}
				outputHelper[row][col] = (outputHelper[row][col] + outputHelper[row - 1][col]) % MOD;
			}
		}

		int output = 0;
		if(nums[n-1]==0) {
			for(int i=0;i<=m;i++) {
				output = (output + outputHelper[n-1][i])%MOD;
			}
		}else {
			output = outputHelper[n-1][nums[n-1]];
		}
		
		System.out.println(output);
	}

}
