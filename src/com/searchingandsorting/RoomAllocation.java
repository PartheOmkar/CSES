package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
 
public class RoomAllocation {
 
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
 
	private static class NumsPair{
		int start;
		int end;
		int index;
 
		public NumsPair(int start, int end, int index) {
			this.start = start;
			this.end = end;
			this.index = index;
		}
	}
 
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<NumsPair> customers = new ArrayList<RoomAllocation.NumsPair>();
		int start = 0;
		int end = 0;
 
		for (int i = 0; i < n; i++) {
			start = rd.nextInt();
			end = rd.nextInt();
			customers.add(new NumsPair(start, end, i));
		}
		rd.close();
 
		Collections.sort(customers, (NumsPair o1, NumsPair o2)->{
			if(o1.start == o2.start) {
				if(o1.end==o2.end) {
					return Integer.compare(o1.index,o2.index);
				}
				return Integer.compare(o1.end,o2.end);
			}
			return Integer.compare(o1.start,o2.start);
		});
		
		int[] output = new int[n];
		int count = 0;
		
		TreeSet<NumsPair> outputHelper = new TreeSet<RoomAllocation.NumsPair>((NumsPair o1, NumsPair o2)->{
			if(o1.end==o2.end) {
				if(o1.start==o2.start) {
					return Integer.compare(o1.index, o2.index);
				}
				return Integer.compare(o1.start, o2.start);
			}
			return Integer.compare(o1.end, o2.end);
		});
		
		for (NumsPair customer : customers) {
			if(outputHelper.isEmpty()) {
				count++;
				output[customer.index] = count;
//				outputHelper.add(customer);
			}else {
				NumsPair current = outputHelper.first();
				if(current.end<customer.start) {
					output[customer.index] = output[current.index];
					outputHelper.remove(current);
//					outputHelper.add(customer);
				}else {
					count++;
					output[customer.index] = count;
//					outputHelper.add(customer);
				}
			}
			outputHelper.add(customer);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int op : output) {
			sb.append(op).append(" ");
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}
 
}