import java.util.*;
import java.io.*;

public class line03 {
	public static void main(String[] args) throws IOException {
		String road = "111011110011111011111100011111";
		int n = 3;
		//String road = "001100";
		//int n=5;
		System.out.println(solution(road, n));
	}

	static int n, ans;

	static int solution(String road, int n) {
		int result = Integer.MIN_VALUE;
		int len = 0;
		int use = 0;
		int j = 0;
		for (int i = 0; i < road.length();) {
			while (j < road.length()) {
				if (road.charAt(j) == '1')
					++len;
				else {
					if (use < n) {
						use += 1;
						++len;
					} else break;
				}
				result = Math.max(len, result);
				++j;
			}
			if(road.charAt(i)=='0') --use;
			--len;
			++i;
		}
		return result;
	}
}