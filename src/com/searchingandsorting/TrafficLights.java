package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class TrafficLights {

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
		int x = rd.nextInt();
		int n = rd.nextInt();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			nums.add(rd.nextInt());
		}
		rd.close();
		
//		long startTime = System.nanoTime();
		TreeMap<Integer, Integer> trafficLightDifferance = new TreeMap<Integer, Integer>();
		TreeSet<Integer> trafficLight = new TreeSet<Integer>();
		StringBuilder sb = new StringBuilder();
		trafficLightDifferance.put(x, 2);
		trafficLight.add(0);
		trafficLight.add(x);
		
		for (int i = 0; i < n; i++) {
			int key = nums.get(i);//rd.nextInt();
			int upperBound = trafficLight.higher(key);
			int lowerBound = trafficLight.lower(key);
//			add key in set 
			trafficLight.add(key);
//			remove previous difference
//			trafficLightDifferance.put(upperBound - lowerBound, trafficLightDifferance.getOrDefault(upperBound - lowerBound, 1) - 1);
//			if (trafficLightDifferance.get(upperBound - lowerBound) == 1) {
//				trafficLightDifferance.remove(upperBound - lowerBound);
//			}
			if(trafficLightDifferance.containsKey(upperBound-lowerBound)) {
				int val = trafficLightDifferance.get(upperBound-lowerBound);
				if(val==1) {
					trafficLightDifferance.remove(upperBound-lowerBound);
				}else {
					trafficLightDifferance.put((upperBound-lowerBound),val-1);
				}
			}
//			add new difference
			trafficLightDifferance.put(key - lowerBound, trafficLightDifferance.getOrDefault(key - lowerBound, 1) + 1);
			trafficLightDifferance.put(upperBound - key, trafficLightDifferance.getOrDefault(upperBound - key, 1) + 1);
			
			sb.append(trafficLightDifferance.lastKey()).append(" ");
		}
//		System.out.write(sb.toString().getBytes());
		System.out.println(sb);
//		long endTime = System.nanoTime();
//		long executionTime = (endTime - startTime) / 1000000;
//		System.out.println(executionTime);
	}

}
