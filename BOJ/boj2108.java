import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int total=0;
		int data[]=new int[n];
		int freqmax=0;
		TreeMap<Integer, Integer> tm=new TreeMap<Integer, Integer>();
		for(int i=0;i<n;i++) {
			int num=Integer.parseInt(br.readLine());
			data[i]=num;
			if(tm.get(num)==null) tm.put(num,1);
			else tm.replace(num, tm.get(num)+1);
			
			if(freqmax<tm.get(num)) freqmax=tm.get(num);
			
			total+=num;
		}
		Arrays.sort(data);
		//산술 평균 : 소숫점 첫째 자리에서 반올림
		System.out.printf("%.0f\n", 1.0*total/n);
		//중앙값
		System.out.println(data[n/2]);
		//최빈 값 : 여러개 있을 땐 두번째로 작은 값
		int idx=0;
		int freq=0;
		for(int val:tm.keySet()) {
			if(tm.get(val)==freqmax) {
				idx++;
				freq=val;
				if(idx==2) break;
			}
		}
		System.out.println(freq);
		//범위
		System.out.println(data[n-1]-data[0]);
	}
}
