package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CollectingNumbers {

	private static class NumsPair{
		int number;
		int index;
		public NumsPair(int number, int index) {
			super();
			this.number = number;
			this.index = index;
		}
	}
	
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
	
	public static void main(String[] args)throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<NumsPair> nums = new ArrayList<NumsPair>();
		for(int i=0;i<n;i++) {
			int x = rd.nextInt();
			nums.add(new NumsPair(x, i));
		}
		rd.close();
		Collections.sort(nums,(NumsPair o1,NumsPair o2)->(int)(o1.number-o2.number));
		//System.out.println(nums);
		int previousIndex = nums.get(0).index;
		int count = 1;
		for(int i=1;i<n;i++) {
			if(previousIndex>nums.get(i).index) {
				count++;
			}
			previousIndex = nums.get(i).index;
		}
		System.out.println(count);
	}
}
