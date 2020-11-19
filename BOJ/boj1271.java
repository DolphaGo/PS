import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class boj1271 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		BigInteger n=new BigInteger(st.nextToken());
		BigInteger m=new BigInteger(st.nextToken());
		System.out.println(n.divide(m));
		System.out.println(n.remainder(m));
	}
}