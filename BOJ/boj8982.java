import java.util.*;
import java.io.*;

public class boj8982 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		int arr[]=new int[40001];
		br.readLine();//시작점 버림
		for(int i=1;i<n-1;i+=2) {
			st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			int x2=Integer.parseInt(st.nextToken());
			Arrays.fill(arr, x1, x2+1, y1);
		}
		//수족관 전체 길이 가져옴
		st=new StringTokenizer(br.readLine());
		int len=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int mid=(a+c)/2;
			int h=arr[mid];
			while(--mid>=0) { //왼쪽 물 빼기
				h=Math.min(h, arr[mid]);
				arr[mid]-=h;
			}
			mid=(a+c)/2;
			h=arr[mid];
			while(++mid<=len) { //오른쪽 물 빼기
				h=Math.min(h,arr[mid]);
				arr[mid]-=h;
			}
			arr[(a+c)/2]=0; //자기 위치 물 빼기
		}
		int answer=0;
		//남아 있는 물양 구하기
		for(int i=0;i<len;i++) answer+=arr[i];
		System.out.println(answer);
	}
}