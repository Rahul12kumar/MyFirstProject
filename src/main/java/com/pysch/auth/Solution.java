package com.pysch.auth;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int p = 0; p < n; p++) {
			String str = sc.next();
			if(check(str))
			{
			String st = (cal(str));
			long N = 0;
			long E = 0;
			long S = 0;
			long W = 0;
			long mod = (long) 1e7;
			for (char c : st.toCharArray())
				switch (c) {
				case 'N':
					N = N+1 % mod;
					break;
				case 'W':
					W = W+1% mod;;
					break;
				case 'E':
					E = E+1% mod;;
					break;
				case 'S':
					S = S+1% mod;
					break;
				default:

				}

			long r = S + 1  % mod;;
			long c = E + 1 % mod;;
			if (N > S) {
				long temp = N - S;
				r = 1000000001 - temp;
			} else {
				r -= N;
			}

			if (W > E) {
				long temp = W - E;
				c = 1000000001 - temp;
			} else {
				c -= W;
			}

			System.out.println("Case #" + (p+1) + ": " + c + " " + r);
		}
			else
				System.out.println("Case #" + (p+1) + ": " + 1 + " " + 1);
		}
		sc.close();
	}
   public static boolean check(String str)
   {
	   int value = 0;
	   for(int i=0;i<str.length();i++)
	   {
		   if(str.charAt(i)=='(')
			   ++value;
		   else if(str.charAt(i)==')')
			   --value;
	   }
	   return value==0;
   }
	public static String cal(String s) {
		String res = "";
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				ArrayList<String> temp = new ArrayList<String>();
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					temp.add(0, stack.pop());
				}
				stack.pop();
				String t = "";
				for (String st : temp)
					t += st;
				int val = Integer.parseInt(stack.pop());
				String re = t;
				while (val-- > 1) {
					re = re+t;
				}
				stack.push(re);
			} else {
				stack.push(Character.toString(s.charAt(i)));
			}
		}
		ArrayList<String> temp = new ArrayList<String>();
		while (!stack.isEmpty()) {
			temp.add(0, stack.pop());
		}
		String t = "";
		for (String st : temp)
			t += st;
		res = res+t; 
		return res;
	}
}