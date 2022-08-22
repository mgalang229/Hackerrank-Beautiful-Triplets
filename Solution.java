import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*

10 3
1 6 7 7 8 10 12 13 13 13 14 19

7 10 13
7 10 13
7 10 13

7 10 13
7 10 13
7 10 13

= frequency of left * frequency of right

 */

public class Solution {
	
	static Scanner fs;
	
	public static void main(String[] args) {
		fs = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		//T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), d = fs.nextInt();
			int[] a = readArray(n);
			int max = Arrays.stream(a).max().getAsInt() + 1;
			int[] freq = new int[max];
			Arrays.fill(freq, 0);
			for (int i = 0; i < n; i++) {
				freq[a[i]]++;
			}
			int count = 0;
			for (int i = 1; i < n - 1; i++) {
				int leftNum = a[i] - d;
				int rightNum = a[i] + d;
				int leftNumFreq = getFreq(a, freq, 0, i - 1, leftNum);
				int rightNumFreq = getFreq(a, freq, i + 1, n - 1, rightNum);
				if (leftNumFreq > 0 && rightNumFreq > 0) {
					count +=  leftNumFreq * rightNumFreq;
				}
			}
			out.println(count);
		}
		fs.close();
		out.close();
	}
	
	static int getFreq(int[] a, int[] freq, int low, int high, int num) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (a[mid] == num) {
				return freq[a[mid]];
			} else if (a[mid] > num) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return 0;
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static int[] readArray(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = fs.nextInt();
		}
		return a;
	}
}
