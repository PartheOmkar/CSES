package com.searchingandsorting;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
 
public class Playlist
{
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
	    for(int i=0;i<n;i++){
	        int num = rd.nextInt();
	        nums.add(new NumsPair(num,i));
	    }
	    rd.close();
	    
	    TreeMap<Integer,Integer> uniqueNums = new TreeMap<Integer,Integer>();
	    int maxCount = 0;
	    int count = 0;
	    int lastRemoveindex = 0;
	    for(int i=0;i<n;i++){
	        NumsPair np = nums.get(i);
	        if(uniqueNums.containsKey(np.number)){
	            int prevIndex = uniqueNums.get(np.number);
	            if(lastRemoveindex>prevIndex) {
	            	count = i-lastRemoveindex;
	            }else {
	            	count = i-prevIndex;
	            	lastRemoveindex = prevIndex;
	            }
	            uniqueNums.put(np.number,np.index);
	        }else{
	            count++;
	            uniqueNums.put(np.number,np.index);
	        }
	        maxCount = Math.max(count,maxCount);
//	        System.out.println(" "+i+" -> "+count);
//	        System.out.println("size = "+uniqueNums.size());
	    }
	    System.out.println(maxCount);
	}
}
