package introductoryProblems;

import java.util.Scanner;

public class TwoSets {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Long n = sc.nextLong();
		sc.close();
		long sequeanceSum = (n*(n+1))/2;
		//System.out.println("sequeance = "+sequeanceSum);
		StringBuilder sb1  = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		long sum1 = 0;
		long sum2 = 0;
		int c1  = 0;
		int c2 = 0;
		while(sequeanceSum>0) {
			sequeanceSum = Math.abs(sequeanceSum-n);
			if(sum1 < sum2) {
				sb1.append(n).append(" ");
				sum1 += n;
				c1++;
			}else {
				sb2.append(n).append(" ");
				sum2+= n;
				c2++;
			}
			n--;
		}
		if(sum1==sum2) {
			System.out.println("YES");
			System.out.println(c1);
			System.out.println(sb1.toString());
			System.out.println(c2);
			System.out.println(sb2.toString());
		}else {
			System.out.println("NO");
		}
	}
}
