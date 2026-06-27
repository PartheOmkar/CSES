package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class SlidingWindowMedian {

	private static class NumPair {
		int num;
		int index;

		public NumPair(int num, int index) {
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
		int k = rd.nextInt();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			nums.add(rd.nextInt());
		}
		rd.close();
		
		int mid = 0;
		if ((k & 1) == 0) {
			mid = k / 2;
		} else {
			mid = (k / 2) + 1;
		}
		
		TreeSet<NumPair> outputHelper = new TreeSet<NumPair>((NumPair o1,NumPair o2)->{
			if(o1.num==o2.num) {
				return o1.index-o2.index;
			}
			return o1.num-o2.num;
		});
		
		for (int i = 0; i < k; i++) {
			outputHelper.add(new NumPair(nums.get(i), i));
		}
		
		int j = 1;
		NumPair key=null;
		for(NumPair x:outputHelper) {
			key = x;
			if(j==mid) {
				break;
			}
			j++;
		}
		
		j=0;
		StringBuilder sb = new StringBuilder();
		for(int i=k;i<n;i++) {
			sb.append(key.num).append(" ");
			outputHelper.add(new NumPair(nums.get(i), i));
			outputHelper.remove(new NumPair(nums.get(j), j));
			
			if(nums.get(i) < key.num && (key.num < nums.get(j) || key.index==j)) {
				key = outputHelper.lower(key);
			}
			if((nums.get(j) <= key.num || key.index==j)&& key.num <= nums.get(i)) {
				key = outputHelper.higher(key);
			}
			j++;
		}
		sb.append(key.num);
		System.out.println(sb);
	}
}
