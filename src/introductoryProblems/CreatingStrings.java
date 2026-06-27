package introductoryProblems;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CreatingStrings {
	static Set<String> op = new TreeSet<String>();
	public static void rec(String str, int bitMask,String output) {
		if(output.length()==str.length()) {
			op.add(output);
			return;
		}
		for(int i=0;i<str.length();i++) {
			if(((1<<i)&bitMask)>0) {
				continue;
			}
			rec(str,(bitMask|(1<<i)),output+str.charAt(i));
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		sc.close();
		rec(str,0,"");
		System.out.println(op.size());
		for(String output:op) {
			System.out.println(output);
		}
	}

}
