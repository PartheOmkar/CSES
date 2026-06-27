package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SumOfTwoValues {
	
	private static class ValueAndIndex{
		int value;
		int index;
		public ValueAndIndex(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}
	}
	
	private static class ValuesSortBy implements Comparator<ValueAndIndex>{

		@Override
		public int compare(ValueAndIndex o1, ValueAndIndex o2) {
			if(o1.value==o2.value) {
				return 0;
			}else if(o1.value>o2.value) {
				return 1;
			}else {
				return -1;
			}
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

	public static void main(String[] args) throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		int x = rd.nextInt();
		ArrayList<ValueAndIndex> values = new ArrayList<SumOfTwoValues.ValueAndIndex>();
		
		for(int i=0;i<n;i++) {
			int value = rd.nextInt();
			values.add(new ValueAndIndex(value, i+1));
		}
		rd.close();
		Collections.sort(values,new ValuesSortBy());
		
		for(int i=0;i<n;i++) {
			int key = Math.abs(values.get(i).value-x);
			int min = i;
			int max = n-1;
			int flag = -1;
			while(min<=max) {
				int mid = (min+max)/2;
				if(key==values.get(mid).value && values.get(i).index!=values.get(mid).index) {
					flag = values.get(mid).index;
					break;
				}else if(key>=values.get(mid).value) {
					min = mid+1;
				}else {
					max = mid-1;
				}
			}
			if(flag!=-1) {
				System.out.println(values.get(i).index+" "+flag);
				return;
			}
		}
		System.out.println("IMPOSSIBLE");
	}

}
