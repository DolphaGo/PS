import java.util.*;

class Solution {
    public static int[] solution(String[] gems) {
        Set<String> set=new HashSet<>();
        for(String gem:gems) set.add(gem);

        Deque<Integer> q=new ArrayDeque<>();
        HashMap<String,Integer> map=new HashMap<>();
        int start=0;
        int end=0;
        int minLength=Integer.MAX_VALUE;
        for(int i=0;i<gems.length;i++){
            String gem=gems[i];
            if(map.get(gem)==null) {
                map.put(gem,1);
            }else{
                int cnt=map.get(gem);
                map.put(gem,cnt+1);
            }
            q.addLast(i);

            while(true){
                int p=q.peekFirst();
                int cnt=map.get(gems[p]);
                if(cnt>1){
                    q.pollFirst();
                    map.put(gems[p],cnt-1);
                }else break;
            }

            if(map.size()==set.size()){
                if(minLength>q.size()){
                    minLength=q.size();
                    start=q.peekFirst();
                    end=q.peekLast();
                }
            }
        }
        return new int[]{start+1,end+1};
    }

    public static void main(String[] args) {
        String gems[]={"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));
    }

}