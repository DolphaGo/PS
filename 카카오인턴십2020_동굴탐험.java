import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
    	ArrayList<Integer> list[]=new ArrayList[n];
		for(int i=0;i<n;i++) list[i]=new ArrayList<Integer>();
		
		//연결리스트를 만들어줍니다.
		for(int i=0;i<path.length;i++) {
			int[] p=path[i];
			int a=p[0];
			int b=p[1];
			list[a].add(b);
			list[b].add(a);
		}
		//먼저 방문해야 할 위치를 저장할 배열입니다.
		int[] prev=new int[n];
		//제약조건이 있는지 확인하는 용도입니다.
		boolean[] check=new boolean[n];
		for(int i=0;i<order.length;i++) {
			int[] p=order[i];
			int a=p[0];//a를 방문하고 b를 방문해야 한다.
			int b=p[1];
			prev[b]=a;
			check[b]=true;
		}
		//그래프 탐색을 위해 Queue를 이용하여 BFS를 시행하였습니다.  
		Queue<Integer> q=new LinkedList<Integer>();
		
		//제약조건이 걸려서 방문하지 못한 방들을 저장합니다.
		HashMap<Integer, Integer> wait=new HashMap<>();
		
		//BFS에서 단 한번만 방문하게 하기 위해 선언한 방문배열입니다.
		boolean visit[]=new boolean[n];
		int len=0;
		if(!check[0]) {
			//Queue에 넣을 때마다 방문을 했다는 뜻이므로 len을 증가해줍니다.
			++len;
			q.add(0); 
			visit[0]=true;
		}
		
		while(!q.isEmpty()) {
			int cur=q.poll();
			for(int i=0;i<list[cur].size();i++) {
				int next=list[cur].get(i);
				//방문하지 않은 방에 대해서만 확인합니다.
				if(!visit[next]) {
					//이전에 방문해야 하는 곳이 있을 때(제약조건이 있을 때)
					if(check[next]) {
						int there=prev[next];
						//이전에 방문해야 하는 곳을 이미 방문을 했다면
						//제약조건이 없는 방과 동일하게 탐색해주면 됩니다.
						if(visit[there]) {
							++len;
							q.add(next);
							visit[next]=true;
 							//사실 visit처리로 인해 단 한번만 방문하므로 check해제는 필요 없습니다.
							//check[next]=false;
						}else wait.put(there,next); //제약조건을 만족하지 못한 것은 wait에 넣어줍니다. 
					}else {
						visit[next]=true;
						q.add(next);
						++len;
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
		}
		return len==n?true:false;
	}
}