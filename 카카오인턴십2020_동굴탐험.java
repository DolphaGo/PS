import java.util.*;
class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
ArrayList<Integer> list[]=new ArrayList[n];
		for(int i=0;i<n;i++) list[i]=new ArrayList<Integer>();
		for(int i=0;i<path.length;i++) {
			int[] p=path[i];
			int a=p[0];
			int b=p[1];
			list[a].add(b);
			list[b].add(a);
		}
		
		int[] prev=new int[n];
		boolean[] check=new boolean[n];
		for(int i=0;i<order.length;i++) {
			int[] p=order[i];
			int a=p[0];//a를 방문하고 b를 방문해야 한다.
			int b=p[1];
			prev[b]=a;
			check[b]=true;
		}
		Queue<Integer> q=new LinkedList<Integer>();
		HashMap<Integer, Integer> wait=new HashMap<>();
		
		boolean visit[]=new boolean[n];
		int len=0;
		if(!check[0]) {
			++len;
			q.add(0);
			visit[0]=true;
		}
		while(!q.isEmpty()) {
			int cur=q.poll();
			for(int i=0;i<list[cur].size();i++) {
				int next=list[cur].get(i);
				if(!visit[next]) {
					//이전에 방문해야 하는 곳이 있을 때
					if(check[next]) {
						int there=prev[next];
						//이전에 방문해야 하는 곳을 이미 방문을 했다면
						if(visit[there]) {
							++len;
							q.add(next);
							visit[next]=true;
							check[next]=false;
						}else wait.put(there,next);
					}else {
						visit[next]=true;
						q.add(next);
						++len;
					}
				}
				//wait list를 탐색(연결성 없기에 가능)
				if(wait.get(next)!=null) {
					int val=wait.get(next);
					++len;
					q.add(val);
					visit[val]=true;
					wait.remove(next);
				}
			}
		}
		return len==n?true:false;
	}
}