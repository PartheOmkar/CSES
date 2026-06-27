package com.dynamicProgramming;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Projects {
	
	private static class Node {
		int start;
		int end;
		int value;
		public Node(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
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
		Reader sc = new Reader();
		int n = sc.nextInt();
		ArrayList<Node> projects = new ArrayList<Node>();
		for(int i=0;i<n;i++) {
			projects.add(new Node(sc.nextInt(),sc.nextInt(),sc.nextInt()));
		}
		sc.close();
		Collections.sort(projects,(Node n1, Node n2)->{
			return n1.end-n2.end;
		});
		
		TreeMap<Integer, Long> outputHelper = new TreeMap<Integer, Long>();
		outputHelper.put(0, (long)0);
		long previousOutput = 0;
		for(int i=0;i<n;i++) {
			Node node = projects.get(i);
			Entry<Integer, Long> sm= outputHelper.lowerEntry(node.start);
			long currentOutput = sm.getValue()+node.value;
			previousOutput = Math.max(currentOutput, previousOutput);
			outputHelper.put(node.end,previousOutput);
		}
		System.out.println(previousOutput);
	}
}
