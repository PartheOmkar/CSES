package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class MovieFestivalII {

	private static class NumPairs {
		int start;
		int end;
		int index;
		public NumPairs(int start, int end, int index) {
			this.start = start;
			this.end = end;
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
		ArrayList<NumPairs> movies = new ArrayList<MovieFestivalII.NumPairs>();
		int start;
		int end;
		for (int i = 0; i < n; i++) {
			start = rd.nextInt();
			end = rd.nextInt();
			movies.add(new NumPairs(start, end, i));
		}
		rd.close();
		Collections.sort(movies, (NumPairs o1, NumPairs o2) -> {
			if (o1.end == o2.end) {
				if(o1.start==o2.start) {
					return o1.index-o2.index;
				}
				return o1.start - o2.start;
			}
			return o1.end - o2.end;
		});
		
		int total = 0;
		
		TreeSet<NumPairs> outputHelper = new TreeSet<MovieFestivalII.NumPairs>((NumPairs o1, NumPairs o2) -> {
			if (o1.end == o2.end) {
				if(o1.start==o2.start) {
					return o1.index-o2.index;
				}
				return o1.start - o2.start;
			}
			return o1.end - o2.end;
		});
		
		NumPairs current;
		for (int i = 0; i < n; i++) {
			current = movies.get(i);
			if (outputHelper.size() < k) {
				NumPairs lower = outputHelper.lower(new NumPairs(current.start, current.start,0));
				if (lower != null) {
					outputHelper.remove(lower);
					outputHelper.add(current);
				}else {
					outputHelper.add(current);
				}
				total++;
			} else {
				NumPairs lower = outputHelper.lower(new NumPairs(current.start, current.start,0));
				if (lower != null) {
					outputHelper.remove(lower);
					outputHelper.add(current);
					total++;
				}
			}
		}
		System.out.println(total);
	}

}
