package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RestaurantCustomers {
	
	static class CustomerPair{
		int start;
		int end;
		public CustomerPair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "Pairs [start=" + start + ", end=" + end + "]";
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
	
    static class SortCustomers implements Comparator<CustomerPair>{

		@Override
		public int compare(CustomerPair o1, CustomerPair o2) {
			if(o1.start==o2.start) {
				return 0;
			}else if(o1.start>o2.start) {
				return 1;
			}else {
				return -1;
			}
		}
    	
    }
    
    public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		ArrayList<CustomerPair> customers = new ArrayList<CustomerPair>();
		
		for(int i=0;i<n;i++) {
			int start = rd.nextInt();
			customers.add(new CustomerPair(start,1));
			int end = rd.nextInt();
			customers.add(new CustomerPair(end, -1));
		}
		rd.close();
		Collections.sort(customers,new SortCustomers());
		int maxValue = 0;
		int value = 0;
		for(CustomerPair cp:customers) {
			value += cp.end;
			maxValue = Math.max(value, maxValue);
		}
		System.out.println(maxValue);
	}

}
