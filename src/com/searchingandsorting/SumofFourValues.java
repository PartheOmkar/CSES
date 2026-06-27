package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SumofFourValues {

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
		int index2;

		public NumPair(int num, int index) {
			this.num = num;
			this.index = index;
		}

		public NumPair(int num, int index, int index2) {
			this.num = num;
			this.index = index;
			this.index2 = index2;
		}
	}

	private static NumPair binarySearch(ArrayList<NumPair> nums, int key, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;
		if (nums.get(mid).num == key) {
			return nums.get(mid);
		} else if (nums.get(mid).num < key) {
			return binarySearch(nums, key, mid + 1, end);
		} else {
			return binarySearch(nums, key, start, mid - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		int totalSum = rd.nextInt();

		ArrayList<NumPair> nums = new ArrayList<NumPair>();
		for (int i = 0; i < n; i++) {
			nums.add(new NumPair(rd.nextInt(), i + 1));
		}
		rd.close();

		ArrayList<NumPair> nums2 = new ArrayList<NumPair>();
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if (i == j) {
					continue;
				}
				nums2.add(new NumPair(nums.get(i).num + nums.get(j).num, nums.get(i).index, nums.get(j).index));
			}
		}
		
		Collections.sort(nums2, (NumPair o1, NumPair o2) -> {
			if (o1.num == o2.num) {
				if(o1.index==o2.index) {
					return Integer.compare(o1.index2,o2.index2);
				}
				return Integer.compare(o1.index, o2.index);
			}
			return Integer.compare(o1.num, o2.num);
		});
		n = nums2.size();
		for (int i = 0; i < n; i++) {
			NumPair current = nums2.get(i);
			if (current.num > totalSum) {
				break;
			}
			int requiredSum = totalSum-current.num;
			NumPair np = binarySearch(nums2, requiredSum, 0, n-1);
			if(np!=null&&(current.index != np.index && current.index != np.index2 
						&&current.index2 != np.index && current.index2 !=np.index2)) {
				System.out.println(nums2.get(i).index+" "+nums2.get(i).index2+" "+np.index+" "+np.index2);
				return;
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}
