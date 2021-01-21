import java.util.ArrayList;
import java.util.List;

public class Solution {
    static int max=1000;
    public static int[] solution(int area) {
        List<Integer> list=new ArrayList<>();
        go(list,area);
        return list.stream().mapToInt(i->i).toArray();
    }
    static void go(List<Integer> list, int area){
        if(area == 0) return;
        int i = max;
        while(i*i>area) i--;
        int res=i*i;
        list.add(res);
        go(list, area-res);
    }
}