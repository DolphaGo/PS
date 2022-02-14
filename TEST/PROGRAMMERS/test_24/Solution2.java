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
        return list.toArray(new String[0]);
    }
}
