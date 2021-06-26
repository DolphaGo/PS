import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, Integer> conv = new HashMap<>() {{
            put("-", 0);
            put("cpp", 1); put("java", 2); put("python", 3);
            put("backend", 1); put("frontend", 2);
            put("junior", 1); put("senior", 2);
            put("chicken", 1); put("pizza", 2);
        }};

        List<Integer> list[][][][] = new ArrayList[4][3][3][3];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        list[i][j][k][l] = new ArrayList<>();
                    }
                }
            }
        }

        StringTokenizer st;
        for (String s : info) {
            st = new StringTokenizer(s);
            int i = conv.get(st.nextToken());
            int j = conv.get(st.nextToken());
            int k = conv.get(st.nextToken());
            int l = conv.get(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            for (int a = 0; a <= i; a += i) {
                for (int b = 0; b <= j; b += j) {
                    for (int c = 0; c <= k; c += k) {
                        for (int d = 0; d <= l; d += l) {
                            list[a][b][c][d].add(score);
                        }
                    }
                }
            }

        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Collections.sort(list[i][j][k][l]);
                    }
                }
            }
        }

        int[] answer = new int[query.length];
        int idx = 0;
        for (String q : query) {
            st = new StringTokenizer(q);
            int lang = conv.get(st.nextToken());
            st.nextToken(); //and
            int group = conv.get(st.nextToken());
            st.nextToken(); //and
            int career = conv.get(st.nextToken());
            st.nextToken(); //and
            int food = conv.get(st.nextToken());

            int score = Integer.parseInt(st.nextToken());

            int s = 0;
            int e = list[lang][group][career][food].size();
            //lowerbound
            while (s < e) {
                int m = (s + e) >> 1;
                if (list[lang][group][career][food].get(m) < score) {
                    s = m + 1;
                } else {
                    e = m;
                }
            }
            answer[idx++] = list[lang][group][career][food].size() - e;
        }
        return answer;
    }
}
