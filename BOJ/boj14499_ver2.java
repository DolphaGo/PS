import java.util.*;
import java.io.*;
public class Main {
	static int dy[]= {0,0,-1,1};
	static int dx[]= {1,-1,0,0};
	static HashMap<Integer, Integer> dice=new HashMap<Integer, Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(int i=1;i<=6;i++) dice.put(i, i);
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int dicey=Integer.parseInt(st.nextToken());
		int dicex=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		int map[][]=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}

		StringBuilder sb=new StringBuilder();
		st=new StringTokenizer(br.readLine());
		
		int diceValue[]=new int[7];
		for(int i=0;i<k;i++) {
			int dir=Integer.parseInt(st.nextToken())-1;
			int ny=dicey+dy[dir];
			int nx=dicex+dx[dir];
			if(ny<0||nx<0||ny==h||nx==w) continue;
			
			move(dir);
			dicey=ny;
			dicex=nx;
			if(map[dicey][dicex]==0) {
				//바닥면에서 맵으로 복사.
				map[dicey][dicex]=diceValue[dice.get(6)];
			}else {
				//맵에서 바닥면으로 복사
				diceValue[dice.get(6)]=map[dicey][dicex];
				map[dicey][dicex]=0;
			}
			
			//윗 면에 쓰여있는 수 출력.
			sb.append(diceValue[dice.get(1)]+"\n");
		}
		System.out.println(sb);
	}
	static void move(int dir) {
		int temp[]=new int[7];
		for(int i=1;i<=6;i++) temp[i]=dice.get(i);
		
		switch(dir) {
		case 0://동쪽
			dice.replace(1, temp[4]);
			dice.replace(3, temp[1]);
			dice.replace(4, temp[6]);
			dice.replace(6, temp[3]);
			break;
		case 1://서쪽
			dice.replace(1, temp[3]);
			dice.replace(3, temp[6]);
			dice.replace(4, temp[1]);
			dice.replace(6, temp[4]);
			break;
		case 2://북쪽
			dice.replace(1, temp[5]);
			dice.replace(2, temp[1]);
			dice.replace(5, temp[6]);
			dice.replace(6, temp[2]);
			break;
		case 3://남쪽
			dice.replace(1, temp[2]);
			dice.replace(2, temp[6]);
			dice.replace(5, temp[1]);
			dice.replace(6, temp[5]);
			break;
		}
	}
}
