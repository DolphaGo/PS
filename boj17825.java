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
			{30,28,27,26,25,30,35,40}
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
			answer=Math.max(answer,ret);
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
			int cur=list[i];
			int move=data[i];
			
			int idx=horse[cur][0];
			int now=horse[cur][1];
			
			if(now==-1) return 0;
			
			//맵 변경 여부
			if(idx==0) {
				int val=horse[cur][1];
				if(map[0][val]==10) {
					idx=1;
					now=0;
				}else if(map[0][val]==20) {
					idx=2;
					now=0;
				}else if(map[0][val]==30) {
					idx=3;
					now=0;
				}
			}
			if(now+move<map[idx].length) {
				//다른 말이 있는지 검사하기.
				for(int j=0;j<4;j++) {
					int[] other=horse[j];
					//같은 말이거나 도착했으면 제외
					if(cur==j||other[1]==-1) continue;
					
					//같은 맵에서 같은 위치에 있을 때
					if(other[0]==idx&&now+move==other[1]) return 0;
					//맵 중앙 25에서 만날 때
					else if(map[idx][now+move]==25&&map[other[0]][other[1]]==25) return 0;
					//맵 끝 마지막 40에서 만날 때
					else if(map[idx][now+move]==40&&map[other[0]][other[1]]==40) return 0;
					//분기점에 위치할 때
					else if(idx==0&&other[0]!=0&&other[1]==0) {
						if(map[0][now+move]==map[other[0]][0]) return 0;
					}
				}
				//없으면 move
				horse[cur][0]=idx;
				horse[cur][1]+=move;
				ret+=map[idx][now+move];
			}else horse[cur][1]=-1;//장외 처리
		}
		return ret;
	}
}