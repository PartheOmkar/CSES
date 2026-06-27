package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class SubarrayDivisibility {

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
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			nums.add(rd.nextInt()%n);
		}
		rd.close();
		TreeMap<Long,Integer> outputHelper = new TreeMap<Long,Integer>();
		outputHelper.put(0L,1);
		long prefixSum = 0L;
		long count = 0L;
		for(int num:nums) {
			prefixSum += num;
			prefixSum = prefixSum%n;
			count += outputHelper.getOrDefault((prefixSum%n), 0);
			outputHelper.put(prefixSum, outputHelper.getOrDefault(prefixSum,0)+1);
		}
		System.out.println(count);
	}
}
