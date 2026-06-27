package com.searchingandsorting;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class CollectingNumbersII {

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
	
	public static int getCount(ArrayList<NumsPair> sortedNums,int first, int second) {
		int count = 0;
		int n = sortedNums.size();
		
		int beforeFirst = first-1;
		int afterFirst = first+1;
		int beforeSecond = second-1;
		int afterSecond = second+1;
		Set<NumsPair> sNums = new TreeSet<NumsPair>(new Comparator<NumsPair>() {
			@Override
			public int compare(NumsPair o1, NumsPair o2) {
				if(o1.number==o2.number) {
					return o1.index-o2.index;
				}
				return o1.number-o2.number;
			}
		});
		
		if(beforeFirst>=0) {
			sNums.add(new NumsPair(beforeFirst, first));
		}
		if(n>afterFirst) {
			sNums.add(new NumsPair(first, afterFirst));
		}
		if(beforeSecond>=0) {
			sNums.add(new NumsPair(beforeSecond, second));
		}
		if(n>afterSecond) {
			sNums.add(new NumsPair(second, afterSecond));
		}
		
		for(NumsPair np:sNums) {
			if(sortedNums.get(np.number).index>sortedNums.get(np.index).index) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args)throws IOException {
		Reader rd = new Reader();
		int n = rd.nextInt();
		int m = rd.nextInt();
		
		ArrayList<NumsPair> nums = new ArrayList<CollectingNumbersII.NumsPair>();
		for(int i=0;i<n;i++) {
			int num = rd.nextInt();
			nums.add(new NumsPair(num, i));
		}

		ArrayList<NumsPair> sortedNums = new ArrayList<CollectingNumbersII.NumsPair>();
		sortedNums.addAll(nums);
		
		Collections.sort(sortedNums, (NumsPair o1,NumsPair o2)->(int)(o1.number-o2.number));
		
		int count = 1;
		int previousIndex = sortedNums.get(0).index;
		for(int i=1;i<n;i++) {
			if(previousIndex>sortedNums.get(i).index) {
				count++;
			}
			previousIndex = sortedNums.get(i).index;
		}
		//System.out.println("count = "+count);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<m;i++) {
			int first = nums.get(rd.nextInt()-1).number;
			int second = nums.get(rd.nextInt()-1).number;

			count -= getCount(sortedNums, first-1, second-1);
			
			int tempIndex = sortedNums.get(first-1).index;
			sortedNums.get(first-1).index = sortedNums.get(second-1).index;
			sortedNums.get(second-1).index = tempIndex;
			
			count+= getCount(sortedNums, first-1, second-1);
			sb.append(count+"\n");
		}
		rd.close();
		System.out.println(sb);
	}

}
