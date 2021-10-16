import java.util.*;

class Solution {
    public String solution(String[] registered_list, String new_id) {
        Set<String> set = new HashSet<>();
        for(String s : registered_list){
            set.add(s);
        }

        if(!set.contains(new_id)){
            return new_id;
        }

        StringBuilder S = new StringBuilder();
        for(int i=0;i<new_id.length();i++){
            char c = new_id.charAt(i);
            if('a'<=c && c<='z'){
                S.append(c);
            }
            else break;
        }

        String stringN = new_id.substring(S.length());
        if(stringN.length() == 0) stringN = "0";
        int N = Integer.parseInt(stringN);

        while(set.contains(S.toString()+(++N))){

        }

        return S.toString()+N;

    }
}
