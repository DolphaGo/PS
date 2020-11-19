import java.io.*;
import java.util.*;

public class boj14499 {
	static int dice[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		dice=new int[6];
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int sx = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int map[][] = new int[h][w];
		for (int y = 0; y < h; y++) {
			st=new StringTokenizer(br.readLine());
			for (int x = 0; x < w; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		int dy[]= {0,0,-1,1};
		int dx[]= {1,-1,0,0};
		for(int i=0;i<k;i++){
			int dir=Integer.parseInt(st.nextToken())-1;
			int ny=sy+dy[dir];
			int nx=sx+dx[dir];
			if(ny<0||nx<0||ny>=h||nx>=w) continue;
			
			//이동하고
			sy=ny;
			sx=nx;
			move(dir);
			
			if(map[sy][sx]==0) {//바닥면이 0인경우
				map[sy][sx]=dice[5];
			}else {
				int val=map[sy][sx];
				dice[5]=val;
				map[sy][sx]=0;
			}
			sb.append(dice[0]+"\n");
		}
		System.out.println(sb.toString());
	}
	static void move(int dir) {
		//0:윗면, 1:뒷면, 2:오른면, 3:왼면, 4:앞면,5:바닥면
		int newdice[]=new int[6];
		switch(dir) {
		case 0: //오른쪽
			newdice[0]=dice[3];
			newdice[1]=dice[1];
			newdice[2]=dice[0];
			newdice[3]=dice[5];
			newdice[4]=dice[4];
			newdice[5]=dice[2];
			break;
		case 1://왼쪽
			newdice[0]=dice[2];
			newdice[1]=dice[1];
			newdice[2]=dice[5];
			newdice[3]=dice[0];
			newdice[4]=dice[4];
			newdice[5]=dice[3];
			break;
		case 2:
			newdice[0]=dice[4];
			newdice[1]=dice[0];
			newdice[2]=dice[2];
			newdice[3]=dice[3];
			newdice[4]=dice[5];
			newdice[5]=dice[1];
			break;
		case 3:
			newdice[0]=dice[1];
			newdice[1]=dice[5];
			newdice[2]=dice[2];
			newdice[3]=dice[3];
			newdice[4]=dice[0];
			newdice[5]=dice[4];
			break;
		}
		dice=newdice.clone();
	}
}