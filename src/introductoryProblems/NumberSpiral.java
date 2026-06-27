package introductoryProblems;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

class NumberSpiral {
	
	static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') ;
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
	
	
	public static long addition(long i, long j) {
		long base = (i*(i-1))+1;
		return (base+ (i-j));
	}
	
	public static long substraction(long i, long j) {
		long base = ((i*(i-1))+1);
		return (base-(i-j)); 
	}
	
	public static void main(String args[]) throws IOException {
		FastScanner sc = new FastScanner();
		StringBuilder sb = new StringBuilder();
		int n = sc.nextInt();
		while(n-->0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			long ans = 0;
			if(x>y) {
				if((x&1)==0) {
					ans = addition(x, y);
				}else {
					ans = substraction(x, y);
				}
			}else {
				if((y&1)==0) {
					ans = substraction(y, x);
				}else {
					ans = addition(y, x);
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}
