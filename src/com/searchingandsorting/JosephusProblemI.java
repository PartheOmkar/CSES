package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class JosephusProblemI {

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

	public static void rec(ArrayList<Integer> data,StringBuffer sb,boolean flag) {
		int n = data.size();
		if(n==0) {
			return;
		}
		ArrayList<Integer> newData = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			if(flag) {
				flag = !flag;
				newData.add(data.get(i));
			}else {
				flag = !flag;
				sb.append(data.get(i)+" ");
			}
		}
		rec(newData,sb,flag);
	}
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		rd.close();
		ArrayList<Integer> data = new ArrayList<Integer>();
		for(int i=1;i<=n;i++) {
			data.add(i);
		}
		StringBuffer sb = new StringBuffer();
		rec(data,sb,true);
		System.out.println(sb);
	}
}
