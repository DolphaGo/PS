import java.io.*;
import java.util.*;

public class boj17825_0602 {
	static int map[][]= {
			{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
			{10,13,16,19,25,30,35,40},
			{20,22,24,25,30,35,40},
			{30,28,27,26,25,30,35,40}
	};
	static int[] arr,turn;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		arr=new int[10];
		turn=new int[10];
		for(int i=0;i<10;i++) arr[i]=Integer.parseInt(st.nextToken());
		answer=0;
		go(0);
		System.out.println(answer);
	}
	
	static void go(int v) {
		if(v==10) {
			int res=Test();
			answer=answer<res?res:answer;
			return;
		}
		//중복 순열
		for(int i=0;i<4;i++) {
			turn[v]=i;
			go(v+1);
		}
	}
	static int Test() {
		int res=0;
		int horse[][]=new int[4][2]; //모두 0,0에 있음
		for(int i=0;i<10;i++) {
			int user=turn[i];
			int move=arr[i];
			
			int mapidx=horse[user][0];
			int loc=horse[user][1];
			//이미 도착한 말을 움직이려고 하면 Out
			if(loc==-1) return 0;
			
			//맵 업데이트(0번째 맵의 10,20,30에 있는 말을 업데이트해주자)
			if(mapidx==0&&map[mapidx][loc]!=40&&map[mapidx][loc]%10==0) {
				mapidx=map[mapidx][loc]/10;
				loc=0;
			}
			
			loc+=move;
			if(loc<map[mapidx].length) {
				//다른 말들과 겹치는 것이 없는지 검사.
				
				//Type 1. 맵 중간의 25,30,35,40 구간 검사(맵번호가 1,2,3인 경우에 대해서) - 맵 번호가 다른데 겹칠 가능성이 있을 때 1
				if(1<=mapidx&&mapidx<=3) {
					//중간의 25,30,35,40 구간 검사, loc는 이동한 후이므로 시작점이 아니기에 검사하지 않아도 된다.
					for(int j=0;j<4;j++) {
						//자신과 동일한 말이거나, 이미 도착한 다른 말은 탐색 대상에서 제외
						//시작점인 곳도 탐색대상에서 제외한다.(내가 알고 싶은 부분은 중간 25,30,35,40인데, 3번맵 첫번째가 30임)
						if(j==user || horse[j][1]==-1 || horse[j][1]==0) continue;
						//25,30,35,40 은 유일함(맵번호 1~3 중에) 따라서 겹치면 Out
						if(1<=horse[j][0]&&horse[j][0]<=3&&map[mapidx][loc]==map[horse[j][0]][horse[j][1]])
							return 0;
					}
				}
				
				//Type 2. 도착 직전의 40 구간 검사(맵번호 상관없음) - 맵 번호가 다른데 겹칠 가능성이 있을 때 2
				for(int j=0;j<4;j++) {
					//자신과 동일한 말이거나, 이미 도착한 다른 말은 탐색 대상에서 제외
					if(j==user || horse[j][1]==-1) continue;
					//어느 맵번호든 간에, 40은 유일하기 때문에 겹치면 Out
					if(map[mapidx][loc]==40&&map[horse[j][0]][horse[j][1]]==40)
						return 0;
				}
				
				//Type 3. 맵 번호와 위치가 같은 말 검사
				for(int j=0;j<4;j++) {
					//자신과 동일한 말이거나, 이미 도착한 다른 말은 탐색 대상에서 제외
					if(j==user || horse[j][1]==-1) continue;
					if(mapidx==horse[j][0]&&loc==horse[j][1]) return 0;
				}
				
				res+=map[mapidx][loc];
				//말의 상태 업데이트
				horse[user][0]=mapidx;
				horse[user][1]=loc;
			}else {
				//도착지점
				horse[user][1]=-1;
			}
		}
		return res;
	}
}