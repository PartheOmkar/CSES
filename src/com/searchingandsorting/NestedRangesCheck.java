package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NestedRangesCheck {

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
 
	public static class PairNums {
		int start;
		int end;
		int index;
 
		public PairNums(int start, int end, int index) {
			this.start = start;
			this.end = end;
			this.index = index;
		}
	}
 
	public static class SortPairNums implements Comparator<PairNums> {
 
		@Override
		public int compare(PairNums o1, PairNums o2) {
			if (o1.start == o2.start) {
				return o2.end - o1.end;
			}
			return o1.start - o2.start;
		}
	}
 
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<PairNums> nums = new ArrayList<NestedRangesCheck.PairNums>();
		int s = 0;
		int e = 0;
		for (int i = 0; i < n; i++) {
			s = rd.nextInt();
			e = rd.nextInt();
			nums.add(new PairNums(s, e, i));
		}
		rd.close();
		
		Collections.sort(nums, new SortPairNums());
		
		int[] ans1 = new int[n];
		int minEnd = nums.get(n-1).end;
		for(int i=n-2;i>=0;i--) {
			PairNums current = nums.get(i);
			if(current.end>=minEnd) {
				ans1[current.index] = 1;
			}else {
				minEnd = current.end;
			}
		}
		
		int[] ans2 = new int[n];
		int maxEnd = nums.get(0).end;
		for(int i=1;i<n;i++) {
			PairNums current = nums.get(i);
			if(current.end<=maxEnd) {
				ans2[current.index] = 1;
			}else {
				maxEnd = current.end;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int x:ans1) {
			sb.append(x).append(" ");
		}
		sb.append("\n");
		for(int x:ans2) {
			//System.out.print(x+" ");
			sb.append(x).append(" ");
		}
		System.out.println(sb.toString());
	}
}
