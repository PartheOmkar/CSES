package com.dynamicProgramming;
/*
 * i have an idea to solve this problem 
 * we need to store the sequence in sorted set
 * the node is contain {num, index, max_count} till now
 * i the node is same then we need to increasing the max_count+1 of previous node. and next.
 * 
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class IncreasingSubsequence {

	private static class BinaryTree{
		static int[] tree;
		static int n;
		
		private BinaryTree(int n) {
			this.n = n;
			tree = new int[n+1];
		}
		
		private static void add(int index, int value) {
			while(index<=n) {
				tree[index] += value;
				index += index&(-index);
			}
		}
		
		private static int getSum(int index) {
			int sum = 0;
			while(index>0) {
				sum += tree[index];
				index -= index&(-index);
			}
			return sum;
		}
	}
	
	private static class NumPair{
		int num;
		int index;
		private NumPair(int num, int index) {
			this.num = num;
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
	
	public static int solution2(ArrayList<Integer> nums, int n) {
		int[] outputHelper = new int[n+1];
		
		Arrays.fill(outputHelper, 1);
		int max = 0;
		for(int i=0;i<n;i++) {
			for(int j=i+1;j<n;j++) {
				if(nums.get(i)<nums.get(j)) {
					outputHelper[j] = Math.max(outputHelper[j], outputHelper[i]+1);
				}
			}
			if(outputHelper[i]>max) {
				max = outputHelper[i];
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<NumPair> nums = new ArrayList<NumPair>();
		for(int i=0;i<n;i++) {
			nums.add(new NumPair(rd.nextInt(), i));
		}
		rd.close();
//		System.out.println(solution2(nums, n));
		
		Collections.sort(nums, (NumPair o1, NumPair o2)->{
			return o1.num - o2.num;
		});
		
		BinaryTree bt = new BinaryTree(9);
		int max = 0;
		for(int i=0;i<n;i++) {
			
		}
	}
}
