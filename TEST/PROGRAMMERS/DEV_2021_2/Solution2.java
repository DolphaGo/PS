import java.util.*;

class Solution {
    static int max = 0;
    static Map<Integer, Boolean> map = new HashMap<>();

    public int solution(int leave, String day, int[] holidays) {
        int value = getDayValue(day);
        for (int i = 1; i <= 30; i++) {
            map.put(i, isWeekend(value++));
        }

        for (int hday : holidays) {
            map.put(hday, true);
        }

        System.out.println("map = " + map);
        go(1, 0, leave);

        return max;
    }

    private static void go(int day, int use, int leave) {
        int count = 0;
        for (int i = 1; i <= 30; i++) {
            if (map.get(i)) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        max = Math.max(max, count);
        if (day == 31) {return;}

        if (map.get(day)) {
            go(day + 1, use, leave);
        } else {
            go(day + 1, use, leave); // 쉬지 않는 경우

            if (use < leave) {
                map.put(day, true);
                go(day + 1, use + 1, leave); // 쉬는 경우
                map.put(day, false);
            }

        }

    }

    private static int getDayValue(String day) {
        switch (day) {
            case "MON":
                return 0;
            case "TUE":
                return 1;
            case "WED":
                return 2;
            case "THU":
                return 3;
            case "FRI":
                return 4;
            case "SAT":
                return 5;
            case "SUN":
                return 6;
            default:
                return -1;
        }
    }

    private static boolean isWeekend(int val) {
        int value = val % 7;
        if (value == 5 || value == 6) {return true;}
        return false;
    }
}
