import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] solution(int startNumber, int endNumber) {
        List<String> list = new ArrayList<>();
        int currentValue = startNumber;
        list.add(String.format("%010d", currentValue));
        while (startNumber != endNumber) {
            if (startNumber > endNumber) {
                startNumber -= 1;
            } else {
                startNumber += 1;
            }
            currentValue = 10 * currentValue + startNumber;
            list.add(String.format("%010d", currentValue));
        }
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
