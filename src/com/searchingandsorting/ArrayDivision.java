package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayDivision {

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

	private static boolean check(ArrayList<Integer> nums, int k, int n, long requiredSum) {
		long tempSum = 0;
		int tempCount = 1;
		for (int i = 0; i < n; i++) {
			int current = nums.get(i);
			
			if ((current + tempSum) <= requiredSum) {
				tempSum += current;
			} else {
				tempSum = current;
				tempCount++;
			}
			if (tempCount > k || current>requiredSum) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		int k = rd.nextInt();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			nums.add(rd.nextInt());
		}
		rd.close();
		long min = 0;
		long max = Long.MAX_VALUE;
		long minSum = Long.MAX_VALUE;
		while (min <= max) {
			long mid = (min + max) / 2L;
			if (check(nums, k, n, mid)) {
				minSum = mid;
//				System.out.println("min  sum = "+minSum);
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		System.out.println(minSum);
	}

}
