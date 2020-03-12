import java.util.*;
import java.io.*;

public class boj10825 {
	static class Data{
		String name;
		int kor;
		int eng;
		int mat;
		Data(String name,int kor,int eng,int mat){
			this.name=name;
			this.kor=kor;
			this.eng=eng;
			this.mat=mat;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Data> pq=new PriorityQueue<>(new Comparator<Data>() {
			public int compare(Data o1,Data o2) {
				if(o1.kor==o2.kor&&o1.eng==o2.eng&&o1.mat==o2.mat) {
					return o1.name.compareTo(o2.name);//이름 오름차순
				}else if(o1.kor==o2.kor&&o1.eng==o2.eng) {
					return Integer.compare(o2.mat, o1.mat);//수학 점수 내림차순
				}else if(o1.kor==o2.kor) {
					return Integer.compare(o1.eng, o2.eng);//영어 점수 오름차순
				}else return Integer.compare(o2.kor, o1.kor);//국어 점수가 내림차순
			}
		});
	
		int n=Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			String name=st.nextToken();
			int kor=Integer.parseInt(st.nextToken());
			int eng=Integer.parseInt(st.nextToken());
			int mat=Integer.parseInt(st.nextToken());
			pq.add(new Data(name,kor,eng,mat));
		}
		StringBuilder sb=new StringBuilder();
		while(!pq.isEmpty()) sb.append(pq.poll().name+"\n");
		
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
}
