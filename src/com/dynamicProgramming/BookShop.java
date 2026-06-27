package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BookShop {

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
		int n = rd.nextInt();
		int x = rd.nextInt();
		int[] prices = new int[n+1];
		int[] pages = new int[n+1];
		for(int i=0;i<n;i++) {
			prices[i] = rd.nextInt();
		}
		for(int i=0;i<n;i++) {
			pages[i] = rd.nextInt();
		}
		rd.close();
		
		int[] outputhelperCurrent = new int[x+1];
		int[] outputhelperPrevious = new int[x+1];
		int currentPrice;
		int currentPages;
		
		for(int row=1;row<=n;row++) {
			currentPrice = prices[row-1];
			currentPages = pages[row-1];
			for(int col=1;col<=x;col++) {
				outputhelperCurrent[col] = Math.max(outputhelperCurrent[col-1],outputhelperPrevious[col]);
				if(currentPrice<=col) {
					outputhelperCurrent[col] = Math.max(outputhelperCurrent[col], outputhelperPrevious[col-currentPrice]+currentPages);
				}
			}
			for(int i=0;i<=x;i++) {
				outputhelperPrevious[i] = outputhelperCurrent[i];
				outputhelperCurrent[i] = 0;
			}
		}
		System.out.println(outputhelperPrevious[x]);
	}

}
