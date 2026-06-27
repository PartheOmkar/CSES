package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.TreeMap;

public class FerrisWheel {
	
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

		TreeMap<Integer, Integer> weights = new TreeMap<Integer, Integer>();
		for(int i=0;i<n;i++) {
			int key = rd.nextInt();
			if(weights.containsKey(key)) {
				weights.put(key, weights.get(key)+1);
			}else {
				weights.put(key, 1);
			}
		}
		rd.close();
		
		int count = n;
		while(!weights.isEmpty()) {
			Integer foundKey = weights.firstKey();
			if(foundKey!=null) {
				Integer val = weights.get(foundKey);
				if(val>1) {
					weights.put(foundKey, val-1);
				}else {
					weights.remove(foundKey);
				}
			}
			Integer key = Math.abs(foundKey-x);
			Integer getKey = weights.floorKey(key);
			if(getKey!=null) {
				count--;
				Integer val = weights.get(getKey);
				if(val>1) {
					weights.put(getKey, val-1);
				}else {
					weights.remove(getKey);
				}
			}
		}
		System.out.println(count);
		
 	}
}
