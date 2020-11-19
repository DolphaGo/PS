import java.util.*;
import java.io.*;

public class boj5635 {
	static class Data{
		String name;
		int dd;
		int mm;
		int yyyy;
		Data(String name,int dd,int mm,int yyyy){
			this.name=name;
			this.dd=dd;
			this.mm=mm;
			this.yyyy=yyyy;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st;
		ArrayList<Data> list=new ArrayList<>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			String name=st.nextToken();
			int dd=Integer.parseInt(st.nextToken());
			int mm=Integer.parseInt(st.nextToken());
			int yyyy=Integer.parseInt(st.nextToken());
			list.add(new Data(name,dd,mm,yyyy));
		}
		Collections.sort(list,new Comparator<Data>() {
			public int compare(Data o1,Data o2) {
				//생일이 같은 사람은 없다.
				if(o1.yyyy==o2.yyyy) {
					if(o1.mm==o2.mm) return Integer.compare(o1.dd, o2.dd);
					else return Integer.compare(o1.mm,o2.mm);
				}else return Integer.compare(o1.yyyy, o2.yyyy);
			}
		});
		System.out.println(list.get(list.size()-1).name);
		System.out.println(list.get(0).name);
	}
}
