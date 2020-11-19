import java.util.*;
public class Solution {
    public int solution(int p) {
        int answer = 0;
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=1000;i<=10000;i++){
            int val=i;
            boolean flag=true;
            boolean rest[]=new boolean[10];
            while(val>0){
                int res=val%10;
                if(!rest[res]){
                    rest[res]=true;
                    val/=10;
                }else{
                    flag=false;
                    break;
                }
            }
            if(flag) list.add(i);
        }
        //upper-bound
        int s=0;
        int e=list.size()-1;
        while(s<e){
            int m=(s+e)/2;
            int mid=list.get(m);
            if(mid<=p) s=m+1;
            else e=m;
        }
        answer=list.get(e);
        return answer;
    }
}