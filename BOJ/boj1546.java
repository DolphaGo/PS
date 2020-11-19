import java.io.*;
import java.util.*;
public class boj1546 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		int data[]=new int[n];
		for(int i=0;i<n;i++) {
			data[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(data);
		int max=data[n-1];
		double val=0;
		for(int i=0;i<n;i++) {
			val+=(1.0*data[i]/max)*100;
		}
		val/=n;
		System.out.printf("%f\n", val);
	}
}
