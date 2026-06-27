package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.IOException;

public class CountingTowers {

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

	final static int MOD = (int) (1E9 + 7);
	final static int SIZE = (int) 1E6;

	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int t = rd.nextInt();
		long[][] outputHelper = new long[SIZE][2];
		outputHelper[0][0] = outputHelper[0][1] = 1;
		for (int i = 1; i < SIZE; i++) {
			outputHelper[i][1] = ((2*outputHelper[i-1][1])%MOD+(outputHelper[i-1][0])%MOD)%MOD;
			outputHelper[i][0] = ((4*outputHelper[i-1][0])%MOD+(outputHelper[i-1][1])%MOD)%MOD;
		}
		while(t-->0) {
			int n = rd.nextInt();
			n = (int) (((outputHelper[n-1][0])%MOD+(outputHelper[n-1][1])%MOD)%MOD);
			System.out.println(n);
		}
	}

}
