import java.util.*;

class Solution {
    public String solution(int n, int[][] delivery) {
        char[] answer=new char[n+1];
        Arrays.fill(answer,'?');
        Arrays.sort(delivery, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[2],o1[2]);
            }
        });
        for(int i=0;i<delivery.length;i++){
            int[] d=delivery[i];
            int a=d[0];
            int b=d[1];
            if(d[2]==1){
                answer[a]='O';
                answer[b]='O';
            }else{
                if(answer[a]=='O') answer[b]='X';
                else if(answer[b]=='O') answer[a]='X';
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++) sb.append(answer[i]);
        return sb.toString();
    }
}