import java.util.*;
import java.io.*;

public class boj2042 {
	static int n,m,k;
	static long arr[];
	static long tree[];
	//node의 왼쪽 자식 : node*2
	//node의 오른쪽 자식 : node*2+1
	static long init(int node,int start,int end) {
		// node가 reaf-node인 경우
		if(start==end) {
			return tree[node]=arr[start];
		}else {
			return tree[node]=init(node*2,start,(start+end)/2)
					+init(node*2+1,(start+end)/2+1,end);
		}
	}
	//node가 담당하는 구간이 [start,end]이고, 합을 구해야하는 구간이 [left,right]일 때
	static long sum(int node,int start,int end,int left,int right) {
		//1. 겹치는게 하나도 없는 경우
		if(left>end || right< start) {
			return 0;
		}
		//2. [left,right]가 [start,end]를 포함하는 경우
		if(left<=start && end<= right) {
			return tree[node];
		}
		//3. 그 외는 탐색을 계속 진행해야 한다.
		//왼쪽 자식과 오른쪽 자식을 루트로하는 트리에서 다시 탐색을 진행한다.
		return sum(node*2,start,(start+end)/2,left,right)
				+ sum(node*2+1, (start+end)/2+1, end, left, right);
	}
	/*1번 경우에는 if (left > end || right < start)로 나타낼 수 있습니다. 
	 * left > end는 [start,end] 뒤에 [left,right]가 있는 경우이고, 
	 * right < start는 [start,end] 앞에 [left,right]가 있는 경우입니다. 
	 * 이 경우에는 겹치지 않기 때문에, 더 이상 탐색을 이어나갈 필요가 없습니다.
	 * 따라서 0을 리턴해 탐색을 종료합니다.
	 * 2번 경우는 if (left <= start && end <= right)로 나타낼 수 있습니다. 
	 * 이 경우도 더 이상 탐색을 이어나갈 필요가 없습니다. 
	 * 구해야하는 합의 범위는 [left,right]인데, [start,end]는 그 범위에 모두 포함되고, 
	 * 그 node의 자식도 모두 포함되기 때문에 더 이상 호출을 하는 것은 비효율적입니다. 
	 * 따라서, tree[node]를 리턴해 탐색을 종료합니다.*/
	
	//인덱스는 항상 부모쪽에서 내려오도록 설계한다.
	static void update(int node,int start,int end,int index,long diff) {
		//2. 포함되지 않는 경우
		if(index<start ||index>end) return;
		
		//1. 포함되어 있는 경우 -> 자식까지 업데이트를 해주자.
		tree[node]+=diff;
		if(start!=end) {
			//왼쪽 자식 업데이트
			update(node*2,start,(start+end)/2,index,diff);
			//오른쪽 자식 업데이트
			update(node*2+1,(start+end)/2+1,end,index,diff);
		}
		
	}
	/*1. [start,end]에 index가 포함되는 경우
	 * node의 구간에 포함되는 경우에는 diff만큼 증가시켜 합을 변경해 줄 수 있습니다. 
	 * tree[node] = tree[node] + diff
	 * 
	 *2. [start,end]에 index가 포함되지 않는 경우
	 * 포함되지 않는 경우는 그 자식도 index가 포함되지 않기 때문에, 탐색을 중단해야 합니다.
	*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		arr=new long[n];
		
		//Binary Tree의 높이 : [lgN]
		int h=(int)Math.ceil(Math.log(n)/Math.log(2));
		tree=new long[1<<(h+1)];
		for(int i=0;i<n;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		init(1, 0, n-1);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<m+k;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken())-1;
			if(a==1) {
				long c=Long.parseLong(st.nextToken());
				long diff=c-arr[b];
				arr[b]=c;
				update(1, 0, n-1, b, diff);
			}else {
				int c=Integer.parseInt(st.nextToken())-1;
				sb.append(sum(1, 0, n-1, b, c)+"\n");
			}
		}
		System.out.println(sb);
	}
}