import java.util.*;
import java.io.*;

public class boj17825 {
	static int answer;
	static int data[]=new int[10];
	static int list[]=new int[10];
	static int map[][]= {
			{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
			{10,13,16,19,25,30,35,40},
			{20,22,24,25,30,35,40},
			{30,28,27,26,25,30,35,40},
			{25,30,35,40}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<10;i++) data[i]=Integer.parseInt(st.nextToken());
		
		answer=0;
		go(0);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==10) {
			int ret=Test();
			if(answer<ret) answer=ret;
			return;
		}
		
		for(int i=0;i<4;i++) {
			list[v]=i;
			go(v+1);
		}
	}
	static int Test() {
		int ret=0;
		//4개의 말이 있다.
		int horse[][]=new int[4][2]; //처음엔 모두 0,0으로 세팅
		for(int i=0;i<10;i++) {
			int cur=list[i];//현재 이동할 말
			int move=data[i];//이동거리
		
			//이미 도착한 말을 이동하려하면 종료
			if(horse[cur][1]==-1) return 0;
			
			//맵 업데이트
			if(horse[cur][0]==0) {
				int val=horse[cur][1];
				if(map[0][val]==10) {
					horse[cur][0]=1;
					horse[cur][1]=0;
				}else if(map[0][val]==20) {
					horse[cur][0]=2;
					horse[cur][1]=0;
				}else if(map[0][val]==30) {
					horse[cur][0]=3;
					horse[cur][1]=0;
				}
			}
			//이동
			horse[cur][1]+=move;
			
			//이동할 수 있는 범위 내라면
			if(horse[cur][1]<map[horse[cur][0]].length) {
				//★★★다른 말이 있는지 검사하기.
				for(int j=0;j<4;j++) {
					int[] other=horse[j];
					//같은 말이거나 도착했으면 제외
					if(cur==j||other[1]==-1) continue;
					//같은 맵에서 같은 위치에 있을 때 => 분기점 포함 검사 가능
					if(horse[cur][0]==other[0]) {
						if(horse[cur][1]==other[1]) return 0;
					}
					//다른 맵에서 같은 위치에 있을 때 => 즉, 분기점+공통경로(25,30,35,40)
					else {
						if(isRange(horse[cur][0],horse[cur][1])&&map[horse[cur][0]][horse[cur][1]]==map[other[0]][other[1]]) return 0;
					}
				}
				ret+=map[horse[cur][0]][horse[cur][1]];
			}else horse[cur][1]=-1;//장외 처리
		}
		return ret;
	}
	static boolean isRange(int mapIdx,int idx) {
		int val=map[mapIdx][idx];
		if(val==25||val==30||val==35||val==40) return true;
		return false;
	}
}