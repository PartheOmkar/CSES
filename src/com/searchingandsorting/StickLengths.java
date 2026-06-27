package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class StickLengths {

	private static class Reader { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public int nextInt() throws IOException 
        { 
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
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, 
                                 BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
	
	private static long ternarySearch(ArrayList<Integer> nums, int n) {
		int m = n/2;
		int key = nums.get(m);
		long ans = 0;
		for(int x:nums) {
			ans += Math.abs(x-key);
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			nums.add(rd.nextInt());
		}
		rd.close();
		Collections.sort(nums);
		System.out.println(ternarySearch(nums, n-1));
	}

}
