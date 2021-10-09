package yogiyo;

import java.util.HashMap;
import java.util.Map;

class yogiyo4 {
    public boolean solution(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            map.put(A[i], B[i]); // start, next
        }
        if (map.size() != A.length) {return false;}

        int visited = 0;
        int start = A[0];
        while (map.size() > 0) {
            final int next = map.getOrDefault(start, -1);
            if (next == -1) {break;}
            visited++;
            map.remove(start);
            start = next;
        }

        return visited == A.length;
    }
}
