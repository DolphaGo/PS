import java.util.*;

class Solution {
    public int[] solution(int total_sp, int[][] skills) {
	        int[] answer = {};
	        int n=skills.length+1;
	        ArrayList<Integer> list[]=new ArrayList[n+1];
	        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
	        int tmp[]=new int[n+1];
	        for(int i=0;i<skills.length;i++){
	            int[] cur=skills[i];
	            list[cur[1]].add(cur[0]);
	            tmp[cur[0]]++;
	        }
	        int res[]=new int[n+1];
	        Queue<Integer> q=new LinkedList<>();
	        for(int i=1;i<=n;i++){
	            if(tmp[i]==0) {
	                res[i]=1;
	                q.add(i);
	            }
	        }
	        while(!q.isEmpty()){
	            int p=q.poll();
	            for(int i=0;i<list[p].size();i++){
	                int now=list[p].get(i);
	                --tmp[now];
	                res[now]+=res[p];
	                if(tmp[now]==0){
	                    q.add(now);
	                }
	            }
	        }
            int cnt=0;
	        for(int i=1;i<=n;i++) {
	        	cnt+=res[i];
	        }
	        int dec=total_sp/cnt;
	        answer=new int[n];
	        for(int i=1;i<=n;i++) {
	        	answer[i-1]=res[i]*dec;
	        }
	        return answer;
	}
}