import java.io.*;
import java.util.*;

public class Main_자리전쟁 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		char map[][]=new char[h][w];
		ArrayList<int[]> seat=new ArrayList<int[]>();
		ArrayList<int[]> man=new ArrayList<int[]>();
		for(int y=0;y<h;y++) {
			map[y]=br.readLine().toCharArray();
			for(int x=0;x<w;x++) {
				if(map[y][x]=='L') seat.add(new int[] {y,x});
				else if(map[y][x]=='X') man.add(new int[] {y,x});
			}
		}
		int answer=0;
		
		int table[][]=new int[seat.size()][man.size()];
		for(int i=0;i<seat.size();i++) {
			int[] cur_seat=seat.get(i);
			for(int j=0;j<man.size();j++) {
				int[] cur_man=man.get(j);
				table[i][j]=getdis(cur_seat,cur_man);
			}
		}
		
		
		ArrayList<Integer> temp=new ArrayList<Integer>();
		boolean vseat[]=new boolean[seat.size()];
		boolean vman[]=new boolean[man.size()];
		
		for(int i=0;i<seat.size();i++) {
			//Step 1 : 한 좌석을 기준으로, 서있는(좌석에 앉지않은) 모든 사람 중 최단 거리검사.
			int min=Integer.MAX_VALUE;
			for(int j=0;j<man.size();j++) {
				if(vman[j]) continue;
				
				if(min>table[i][j]) {
					temp.clear();
					min=table[i][j];
					temp.add(j);
				}else if(min==table[i][j]) temp.add(j);
			}
			System.out.println("현재 좌석 : "+i+" ,사람들 : "+temp.toString());
			//temp : 좌석에 앉지 못한 상태 && 현재 좌석에 가장 가까운 사람들 모임.
			int cnt=0;
			int midx=0;//man idx
			for(int j:temp) {
				//역으로도 거리가 가장 가까운 좌석인지 확인해야 함.
				int checkmin=Integer.MAX_VALUE;
				int idx=0;//seat idx
				for(int k=0;k<seat.size();k++) {
					if(vseat[k]) continue; //이미 누군가 앉아있습니다.
					
					if(checkmin>table[k][j]) {
						checkmin=table[k][j];
						idx=k;
						midx=j;
					}
				}
				if(checkmin==min&&idx==i) ++cnt;
			}
			System.out.println("cnt : "+cnt);
			if(cnt>=2) ++answer;//자리전쟁
			else if(cnt==1) {//무혈사태
				vseat[i]=true; 
				vman[midx]=true;
			}
			temp.clear();
		}
		System.out.println(Arrays.toString(vman));
		System.out.println(answer);
	}
	static int getdis(int[] o1,int[] o2) {
		int val=0;
		for(int i=0;i<2;i++) val+=(o1[i]-o2[i])*(o1[i]-o2[i]);
		return val;
	}
}