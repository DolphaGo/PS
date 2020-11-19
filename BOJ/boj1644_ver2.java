import java.util.*;
import java.io.*;

public class boj1644_ver2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int primes[] = Eratos(n);
		int answer = 0;
		int lo = 0;
		int hi = 0;
		long now = 0;
		while (true) {
			if (now >= n)
				now -= primes[lo++];
			else if (hi == primes.length)
				break;
			else
				now += primes[hi++];
			if (now == n)
				++answer;
		}
		System.out.println(answer);
	}

	public static int[] Eratos(int n) {
		int trueNum = 0;
		boolean[] isPrime = new boolean[n + 1];
		for (int i = 2; i <= n; i++)
			isPrime[i] = true;
		for (int i = 2; (i * i) <= n; i++) {
			if(!isPrime[i]) continue;
			for (int j = i * i; j <= n; j += i) {
				isPrime[j] = false;
			}
		}
		
		for(int i=2;i<=n;i++) {
			if(isPrime[i]) ++trueNum;
		}
		
		int[] primes=new int[trueNum];
		int idx=0;
		for(int i=2;i<=n;i++) {
			if(isPrime[i]) primes[idx++]=i;
		}
		return primes;
	}
}
