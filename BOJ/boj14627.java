import java.io.*;
import java.util.*;

public class boj14627 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int S=Integer.parseInt(st.nextToken());
		int C=Integer.parseInt(st.nextToken());
		int[] arr=new int[S];
		int max=0;
		long sum=0;
		for(int i=0;i<S;i++) {
			arr[i]=Integer.parseInt(br.readLine());
			sum+=arr[i];
			max=Math.max(max,arr[i]);
		}
		
		int s=1;
		int e=max;
		long len=0;
		while(s<=e) {
			int mid=(s+e)>>1;
			long res=get(arr,mid);
			//mid와 res는 반비례관계
			//목표 : res는 만족시키면서 최대한 mid가 길게하는 것
			if(res>=C) {
				s=mid+1; //양이 같거나 많으면, 길이를 늘리기
				//우선 조건을 만족했으니 len을 업데이트
				len=Math.max(len,mid);
			}
			else e=mid-1; //양이 적으면 길이를 줄이기.
		}
		long answer=sum-(C*len);
		System.out.println(answer);
	}
	static long get(int[] arr,int val) {
		long res=0;
		for(int i=0;i<arr.length;i++) res+=arr[i]/val;
		return res;
	}
}
