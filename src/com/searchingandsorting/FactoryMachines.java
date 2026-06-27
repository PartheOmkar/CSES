package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FactoryMachines {

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

	public static boolean valid(int n, int t, ArrayList<Integer> nums, long time) {
		long count = 0;
		for (int i = 0; i < n; i++) {
			count += (time / nums.get(i));
			if(count>=t) {
				return true;
			}
		}
		return false;
	}
 
	static long ans = -1;
	
	public static void binarySearch(ArrayList<Integer> nums, int n, int t, long min, long max) {
		if (min > max) {
			return;
		}
		long mid = (min+(max-min)/ 2);
		if (valid(n, t, nums, mid)) {
			ans = mid;
			binarySearch(nums, n, t, min, mid - 1);
		} else {
			binarySearch(nums, n, t, mid + 1, max);
		}
	}
 
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		int t = rd.nextInt();
		long max = 1000000000000000000L;
		int num = 0;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			num = rd.nextInt();
			nums.add(num);
		}
		rd.close();
		binarySearch(nums, n, t, 0, max);
		System.out.println(ans);
	}
}
