package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Apartments {
	
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
  
//        public Reader(String file_name) throws IOException 
//        { 
//            din = new DataInputStream( 
//                new FileInputStream(file_name)); 
//            buffer = new byte[BUFFER_SIZE]; 
//            bufferPointer = bytesRead = 0; 
//        } 
  
//        public String readLine() throws IOException 
//        { 
//            byte[] buf = new byte[64]; // line length 
//            int cnt = 0, c; 
//            while ((c = read()) != -1) { 
//                if (c == '\n') { 
//                    if (cnt != 0) { 
//                        break; 
//                    } 
//                    else { 
//                        continue; 
//                    } 
//                } 
//                buf[cnt++] = (byte)c; 
//            } 
//            return new String(buf, 0, cnt); 
//        } 
  
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
  
//        public long nextLong() throws IOException 
//        { 
//            long ret = 0; 
//            byte c = read(); 
//            while (c <= ' ') 
//                c = read(); 
//            boolean neg = (c == '-'); 
//            if (neg) 
//                c = read(); 
//            do { 
//                ret = ret * 10 + c - '0'; 
//            } while ((c = read()) >= '0' && c <= '9'); 
//            if (neg) 
//                return -ret; 
//            return ret; 
//        } 
  
//        public double nextDouble() throws IOException 
//        { 
//            double ret = 0, div = 1; 
//            byte c = read(); 
//            while (c <= ' ') 
//                c = read(); 
//            boolean neg = (c == '-'); 
//            if (neg) 
//                c = read(); 
//  
//            do { 
//                ret = ret * 10 + c - '0'; 
//            } while ((c = read()) >= '0' && c <= '9'); 
//  
//            if (c == '.') { 
//                while ((c = read()) >= '0' && c <= '9') { 
//                    ret += (c - '0') / (div *= 10); 
//                } 
//            } 
//  
//            if (neg) 
//                return -ret; 
//            return ret; 
//        } 
  
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
//		long start2 = System.currentTimeMillis();
		Reader rd = new Reader();
		int n = rd.nextInt();
		int m = rd.nextInt();
		int k = rd.nextInt();
		
		ArrayList<Integer> nums1 = new ArrayList<Integer>();
		ArrayList<Integer> nums2 = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			nums1.add(rd.nextInt());
		}
		for(int i=0;i<m;i++) {
			nums2.add(rd.nextInt());
		}
		rd.close();

		Collections.sort(nums1);
		Collections.sort(nums2);
		
		int j = 0;
		int i = 0;
		int count = 0;
		n--;
		m--;
		while(i<=n&&j<=m) {
            int rent1 = nums1.get(i);//nums1[i];
            int rent2 = nums2.get(j);//nums2[j];
            
            if (Math.abs(rent1 - rent2) <= k) {
                count++;
                i++;
                j++;
            } else if (rent1 < rent2) {
                i++;
            } else {
                j++;
            }
		}
		System.out.print(count);
	}

}

