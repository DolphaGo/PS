import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        StringTokenizer st;
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < report.length; i++) {
            String s = report[i];
            st = new StringTokenizer(s);
            final String from = st.nextToken();
            final String to = st.nextToken();

            final Set<String> set = map.getOrDefault(from, new HashSet<>());
            set.add(to);
            map.put(from, set);
        }

        // 신고 결과 검사
        Map<String, Integer> abused = new HashMap<>();
        for (String name : map.keySet()) {
            final Set<String> set = map.getOrDefault(name, new HashSet<>());
            for (String s : set) {
                abused.put(s, abused.getOrDefault(s, 0) + 1); // 나 얼마나 신고 받았는지
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            final String id = id_list[i];
            final Set<String> set = map.getOrDefault(id, new HashSet<>());
            for (String s : set) {
                if (abused.getOrDefault(s, 0) >= k) {
                    answer[i] += 1;
                }
            }
        }
        return answer;
    }
}
