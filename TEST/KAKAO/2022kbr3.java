import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 기본 시간, 기본 요금, 단위 시간, 단위 요금 => 다 분 단위로 주어짐

        StringTokenizer st;

        Map<Integer, Integer> use = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (String record : records) {
            st = new StringTokenizer(record);
            final String time = st.nextToken();
            final int number = Integer.parseInt(st.nextToken());
            final boolean state = st.nextToken().equals("IN"); // 들어왔을 때 = true / 나갔을 때 = false

            if (state) {
                map.put(number, convert(time));
            } else {
                final int outTime = convert(time);
                final int inTime = map.get(number);
                use.put(number, use.getOrDefault(number, 0) + outTime - inTime); // 사용시간 추가
                map.remove(number); // 나간 것 처리
            }
        }

        // 그래도 아직 map에 남아있는 것들은(in했는데 안나간 것들은 23:59분에 출차한 것으로 인식함)
        final int outTime = convert("23:59");
        for (int number : map.keySet()) {
            final int inTime = map.get(number);
            use.put(number, use.getOrDefault(number, 0) + outTime - inTime); // 사용시간 추가
        }
        map.clear();

        // 돈 계산
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // 번호 낮은 순으로 정리하려고
        for (int number : use.keySet()) {
            final int useTime = use.get(number);
            final int fee = getFee(fees, useTime);
            treeMap.put(number, fee);
        }

        int[] answer = new int[treeMap.size()];
        int idx = 0;
        for (int key : treeMap.keySet()) {
            answer[idx++] = treeMap.get(key);
        }

        return answer;
    }

    // 문제에서 주어진 요금제로 계산한다.
    private int getFee(final int[] fees, final int useTime) {
        final int defaultTime = fees[0];
        final int defaultFee = fees[1];
        final int unitTime = fees[2];
        final int unitFee = fees[3];

        if (useTime <= defaultTime) {
            return defaultFee;
        }

        final int over = (int) Math.ceil((1.0) * (useTime - defaultTime) / unitTime);
        return defaultFee + over * unitFee;
    }

    private int convert(String s) {
        // s : HH:mm 형태로 들어온다. 이를 0시0분 기준으로 분 단위로 리턴한다.
        StringTokenizer st = new StringTokenizer(s, ":");
        final int HH = Integer.parseInt(st.nextToken());
        final int mm = Integer.parseInt(st.nextToken());
        return HH * 60 + mm;
    }
}
