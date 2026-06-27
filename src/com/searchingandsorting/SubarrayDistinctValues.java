package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class SubarrayDistinctValues {

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
		for(int i=0;i<n;i++) {
			nums.add(rd.nextInt());
		}
		rd.close();
		TreeMap<Integer, Integer> unique = new TreeMap<Integer, Integer>();
		unique.put(nums.get(0), 1);
		int j = 0;
		long count = nums.size();
		int currentCount = 0;
		for(int i=1;i<n;i++) {
			int currentNum = nums.get(i);
			if(unique.containsKey(currentNum)) {
				unique.put(currentNum, unique.get(currentNum)+1);
				currentCount++;
			}else {
				while(unique.size()==k) {
					int follow = nums.get(j);
					if(unique.get(follow)==1) {
						unique.remove(follow);
					}else {
						unique.put(follow, unique.get(follow)-1);
					}
					currentCount--;
					j++;
				}
				unique.put(currentNum, unique.getOrDefault(currentNum,0)+1);
				currentCount++;
			}
			count+=currentCount;
		}
		System.out.println(count);
	}
}
