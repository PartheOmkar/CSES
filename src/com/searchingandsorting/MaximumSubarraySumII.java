package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class MaximumSubarraySumII {

	private static class NumsPair {
		int num;
		int index;

		public NumsPair(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}

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
		int min = rd.nextInt();
		int max = rd.nextInt();
		ArrayList<NumsPair> nums = new ArrayList<MaximumSubarraySumII.NumsPair>();
		int num;
		for (int i = 0; i < n; i++) {
			num = rd.nextInt();
			nums.add(new NumsPair(num, i));
		}
		rd.close();
		TreeSet<NumsPair> outputHelper = new TreeSet<MaximumSubarraySumII.NumsPair>((NumsPair o1, NumsPair o2) -> {
			return o1.index - o2.index;
		});
		NumsPair current;
		long maxSize = Long.MIN_VALUE;
		long currentSize = 0;
		for (int i = 0; i < n; i++) {
			current = nums.get(i);
			if (outputHelper.size() < min) {
				outputHelper.add(current);
				currentSize += current.num;
			}else {
				if((currentSize+current.num)>=current.num && outputHelper.size()<max) {
					outputHelper.add(current);
					currentSize += current.num;
				}else {
					while((currentSize+current.num)<current.num && !outputHelper.isEmpty()) {
						currentSize -= outputHelper.pollFirst().num;
						if(outputHelper.size()>=min) {
							maxSize = maxSize<currentSize?currentSize:maxSize;
						}
					}
					outputHelper.add(current);
					currentSize += current.num;
				}
			}
			if(outputHelper.size()>=min) {
				maxSize = maxSize<currentSize?currentSize:maxSize;
			}
		}
		while(outputHelper.size()>min) {
			currentSize -= outputHelper.pollFirst().num;
			maxSize = maxSize<currentSize?currentSize:maxSize;
		}
		System.out.println(maxSize);
	}

}
