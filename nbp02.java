import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int min=Integer.MAX_VALUE;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			int user = arr[i];
			if (map.get(user) == null) {
				map.put(user, i);
			} else {
				int diff=i-map.get(user);
				min=min>diff?diff:min;
				map.replace(user, i);
			}
		}
		return min==Integer.MAX_VALUE?-1:min;
    }
}