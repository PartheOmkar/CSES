package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class NearestSmallerValues {

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

	private static class NumPair {
		int num;
		int index;

		public NumPair(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<NumPair> nums = new ArrayList<NumPair>();
		for (int i = 0; i < n; i++) {
			nums.add(new NumPair(rd.nextInt(), i + 1));
		}
		rd.close();

		Collections.sort(nums, (NumPair o1, NumPair o2) -> {
			return Integer.compare(o1.num, o2.num);
		});

		TreeSet<NumPair> outputHelpar = new TreeSet<NumPair>((NumPair o1,NumPair o2)->{
			return Integer.compare(o1.index, o2.index);
		});
		
		int[] output = new int[n];
		
		for (int i = 0; i < n; i++) {
			NumPair current = nums.get(i);
			NumPair lowerBound = outputHelpar.floor(new NumPair(current.num-1, current.index));
			
			if (lowerBound != null) {
				output[current.index - 1] = lowerBound.index;
				if(lowerBound.num==current.num) {
					output[current.index-1] = output[lowerBound.index-1];
				}
			} else {
				output[current.index - 1] = 0;
			}
			outputHelpar.add(current);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(output[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

}
