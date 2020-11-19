import java.util.*;
import java.io.*;

public class boj10101 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum=0;
		int a[]=new int[3];
		for(int i=0;i<3;i++) {
			int val=Integer.parseInt(br.readLine());
			a[i]=val;
			sum+=val;
		}
		if(sum!=180) System.out.println("Error");
		else {
			if(a[0]==a[1]&&a[1]==a[2]) System.out.println("Equilateral");
			else if(a[0]==a[1]||a[1]==a[2]||a[0]==a[2]) System.out.println("Isosceles");
			else System.out.println("Scalene");
		}
	}
}
