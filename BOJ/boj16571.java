import java.util.*;
import java.io.*;

public class boj16571 {
	static int now;
	static int map[][]=new int[3][3];
	static int win,draw,lose;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cnt=0;
		for(int i=0;i<3;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]!=0) ++cnt;
			}
		}
		int res=0;
		if(cnt%2==0) res=go(1);
		else res=go(2);
		
		if(res==0) System.out.println("D");
		else if(res==1) System.out.println("W");
		else System.out.println("L");
	}
	static int go(int turn) {
		//만약 현재 맵 상태에서 상대턴이 이겼다면
		//더 수를 둘 필요가 없다.
		if(Test(3-turn)) return -1;
		
		// -1, 0, 1 중에서 결과를 표현하므로 2라고 두었음.
		int min=2;
		for(int y=0;y<3;y++) {
			for(int x=0;x<3;x++) {
				//Brute Force
				if(map[y][x]==0) {
					map[y][x]=turn;
					//최선의 수를 둔다는 것은 상대방이 놓았을 때 이기면 안됨
					//즉, 상대방이 이기면 음수(-1)를 반환하고
					//내가 이기면 양수(1)를 반환하는 상황
					//즉, 상대턴의 결과(go(3-turn))가
					//음수를 반환했으면 좋겠다는 것임(상대방 입장에선 내가 이겨서 음수를 반환)
					min=Math.min(min,go(3-turn));
					map[y][x]=0;
				}
			}
		}
		//아무런 값 변화가 없었다면 Draw
		if(min==2) return 0;
		//위에서 구한 min은 상대 턴의 입장에서 음수를 반환했으면 좋겠다는 것이다.
		//그래서 Math.min으로 구했다면,
		//이번에 값을 반환할 때에는 자신의 turn에서 최선의 결과를 내놓는 것이므로
		//부호를 반대로 곱해줘서 리턴한다.
		return -min;
	}
	static boolean Test(int turn) {
		for(int y=0;y<3;y++) {
			if(map[y][0]==turn&&map[y][0]==map[y][1]&&map[y][1]==map[y][2]) return true;
		}
		for(int x=0;x<3;x++) {
			if(map[0][x]==turn&&map[0][x]==map[1][x]&&map[1][x]==map[2][x]) return true;
		}
		if(map[0][0]==turn&&map[0][0]==map[1][1]&&map[1][1]==map[2][2]) return true;
		if(map[0][2]==turn&&map[0][2]==map[1][1]&&map[1][1]==map[2][0]) return true;
		return false;
	}
}