import java.io.*;
import java.util.*;

public class boj3190 {
	static class Command{
		int sec;
		char type;
		Command(int sec,char type){
			this.sec=sec;
			this.type=type;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		int v[][]=new int[n][n];
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken())-1;
			int c=Integer.parseInt(st.nextToken())-1;
			v[r][c]=1;	
		}
		
		int l=Integer.parseInt(br.readLine());
		int dy[]= {-1,0,1,0};
		int dx[]= {0,1,0,-1};
		int answer=0;
		int dir=1;
		int y=0;
		int x=0;
		v[y][x]=2;
		Deque<int[]> snake=new LinkedList<>();
		snake.add(new int[] {y,x});
		Command command[]=new Command[l+1];
		for(int i=0;i<l;i++) {
			st=new StringTokenizer(br.readLine());
			int sec=Integer.parseInt(st.nextToken());
			char com=st.nextToken().charAt(0);
			command[i]=new Command(sec,com);
		
		}
		command[l]=new Command(Integer.MAX_VALUE,'E');
		int idx=0;
		loop:while(true) {
			while(answer<command[idx].sec) { //방향전환 이전까지는
				answer++;
				int ny=y+dy[dir]; //머리가 향하는 부분
				int nx=x+dx[dir];
				if(ny<0||nx<0||ny>=n||nx>=n) break loop;
				if(v[ny][nx]==1) {
					snake.addFirst(new int[] {ny,nx});
					v[ny][nx]=2; //머리만 늘려준다.
				}else {
					
					if(v[ny][nx]==0) {
						v[ny][nx]=2;
						snake.addFirst(new int[] {ny,nx});
						int[] tail=snake.pollLast();
						v[tail[0]][tail[1]]=0;
					}else break loop;//뱀 자신과 부딪히게 되면
					
				}
				y=ny;
				x=nx;
			}
			if(command[idx].type=='L') dir=(dir+3)%4;
			else dir=(dir+1)%4;
			
			idx++;
		}
		
		System.out.println(answer);
	}
}