import java.util.*;
import java.util.Map.Entry;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        List<String> list = new ArrayList<>();
        List<char[]> dataList = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            final char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            dataList.add(arr);
        }

        for (int i = 0; i < course.length; i++) {
            int max = 2;
            int count = course[i];
            Map<String, Integer> map = new HashMap<>();
            for (char[] data : dataList) {
                List<String> comb = getCombs(data, count);
                for (String s : comb) {
                    final int countUp = map.getOrDefault(s, 0) + 1;
                    map.put(s, countUp);
                    max = Math.max(max, countUp);
                }
            }

            for (Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max) {
                    list.add(entry.getKey());
                }
            }
        }

        Collections.sort(list);
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private List<String> getCombs(final char[] arr, final int count) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (1 << arr.length); i++) {
            sb.setLength(0);
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) > 0) {
                    sb.append(arr[j]);
                }
            }
            if (sb.length() == count) {
                result.add(sb.toString());
            }
        }
        return result;
    }
}
