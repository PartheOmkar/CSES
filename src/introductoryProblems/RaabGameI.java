package introductoryProblems;

import java.util.Scanner;

public class RaabGameI {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0) {
			int n = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(n < (a+b) || ((n>=(a+b)) && ((a!=0 && b==0) || (a==0 && b!=0)))){
				System.out.println("NO");
				continue;
			}
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			
			int diff = (n-(a+b));
			int newN = a+b;
			if(a>b) {
				for(int i=1; i<=n; i++) {
					sb2.append(i).append(" ");
				}
				for(int i= b+1; i<=newN; i++) {
					sb1.append(i).append(" ");
				}
				
				for(int i=1; i<=b; i++) {
					sb1.append(i).append(" ");
				}
				
				for(int i = newN+1;i<=n;i++) {
					sb1.append(i).append(" ");
				}
			}else {
				for(int i=1; i<=n; i++) {
					sb1.append(i).append(" ");
				}
				for(int i= a+1; i<=newN; i++) {
					sb2.append(i).append(" ");
				}
				for(int i=1; i<=a; i++) {
					sb2.append(i).append(" ");
				}
				for(int i = newN+1;i<=n;i++) {
					sb2.append(i).append(" ");
				}
			}
			
			System.out.println("YES");
			System.out.println(sb1.toString());
			System.out.println(sb2.toString());
			
			
		}
		sc.close();
	}

}

