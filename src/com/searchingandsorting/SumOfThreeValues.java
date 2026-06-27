package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SumOfThreeValues {

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

//		public Reader(String file_name) throws IOException {
//			din = new DataInputStream(new FileInputStream(file_name));
//			buffer = new byte[BUFFER_SIZE];
//			bufferPointer = bytesRead = 0;
//		}

//		public String readLine() throws IOException {
//			byte[] buf = new byte[64]; // line length
//			int cnt = 0, c;
//			while ((c = read()) != -1) {
//				if (c == '\n') {
//					if (cnt != 0) {
//						break;
//					} else {
//						continue;
//					}
//				}
//				buf[cnt++] = (byte) c;
//			}
//			return new String(buf, 0, cnt);
//		}

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

//		public long nextLong() throws IOException {
//			long ret = 0;
//			byte c = read();
//			while (c <= ' ')
//				c = read();
//			boolean neg = (c == '-');
//			if (neg)
//				c = read();
//			do {
//				ret = ret * 10 + c - '0';
//			} while ((c = read()) >= '0' && c <= '9');
//			if (neg)
//				return -ret;
//			return ret;
//		}

//		public double nextDouble() throws IOException {
//			double ret = 0, div = 1;
//			byte c = read();
//			while (c <= ' ')
//				c = read();
//			boolean neg = (c == '-');
//			if (neg)
//				c = read();
//
//			do {
//				ret = ret * 10 + c - '0';
//			} while ((c = read()) >= '0' && c <= '9');
//
//			if (c == '.') {
//				while ((c = read()) >= '0' && c <= '9') {
//					ret += (c - '0') / (div *= 10);
//				}
//			}
//
//			if (neg)
//				return -ret;
//			return ret;
//		}

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

	public static NumPair binarySearch(ArrayList<NumPair> nums, int key, int min, int max) {
		if(min>max) {
			return null;
		}
		int mid = (min+max)/2;
		if(nums.get(mid).num==key) {
			return nums.get(mid);
		}else if(nums.get(mid).num < key) {
			return binarySearch(nums, key, mid+1, max);
		}else {
			return binarySearch(nums, key, min, mid-1);
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
		
		Collections.sort(nums, (NumPair o1, NumPair o2) -> {
			if (o1.num == o2.num) {
				return Integer.compare(o1.index, o2.index);
			}
			return Integer.compare(o1.num, o2.num);
		});

		for (int i = 0; i < n; i++) {
			if (nums.get(i).num > totalSum) {
				break;
			}
			int requiredSum = totalSum - nums.get(i).num;
			for (int j = i + 1; j < n; j++) {
				if (nums.get(j).num > requiredSum) {
					break;
				}
				int requiredSum2 = requiredSum - nums.get(j).num;
				NumPair np = binarySearch(nums,requiredSum2,j + 1, n-1);
				if(np!=null) {
					System.out.println(nums.get(i).index+" "+nums.get(j).index+" "+np.index);
					return;
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
	}

}
