import java.util.*;

public class nbp04_효율성2개TLE {
	static int answer;
	static int brr[];

	public static int solution(int n, int capacity, int[] files) {
		brr = new int[17];
		for (int i = 0; i < files.length; i++) brr[files[i]]++;
		answer = Integer.MIN_VALUE;
		go(0, 0, 0, n, capacity);
		return answer;
	}

	static void go(int sy, int sx, int use, int n, int capacity) {
		answer = answer < use ? use : answer;
		
		for (int y = sy; y < n; y++) {
			for (int x = y == sy ? sx : 0; x < capacity; x++) {
				// 빈 곳에 대해
				// 사용하지 않은 파일들을 다 둘러보면서 배치
				for (int i = 1; i <= 16; i++) {
					if (x + i <= capacity) {
						if (brr[i] == 0)
							continue;
						// 붙일 수 있으면
						brr[i]--;
						// 다음 탐색
						go(y, x + i, use + 1, n, capacity);
						// 다시 떼고
						brr[i]++;
					} else break;
				}
				// 그냥 넘어가기(안붙이고 다음 캐패시터로)
				go(y + 1, 0, use, n, capacity);
				// 빈 곳에 대해 모든 시도를 해봤음->return
				return;
			}
		}
	}

	public static void main(String[] args) {
		int n = 2;
		int cap = 3;
		int[] arr = { 2, 2, 2, 2, 2 };
		System.out.println(solution(n, cap, arr));
	}
}