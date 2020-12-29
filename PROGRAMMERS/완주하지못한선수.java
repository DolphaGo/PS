import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String,Integer> map=new HashMap<>();
        for(String p : participant){
            map.put(p,map.getOrDefault(p,0)+1);
        }
        for(String c : completion){
            if(map.get(c)==1) {
                map.remove(c);
            } else {
                map.replace(c, map.get(c) - 1);
            }
        }
        return String.join("", map.keySet());
    }
}