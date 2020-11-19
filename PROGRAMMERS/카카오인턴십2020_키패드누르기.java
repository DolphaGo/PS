import java.util.*;
import java.io.*;

public class 카카오인턴십2020_키패드누르기 {
	static StringBuilder sb=new StringBuilder();
	static int[] l=new int[] {3,0};
	static int[] r=new int[] {3,2};
	static HashMap<Integer, int[]> map=new HashMap<>();
	public static String solution(int[] numbers, String hand) {
		map.put(0,new int[] {3,1});
		for(int i=0;i<9;i++) map.put(i+1,new int[] {i/3,i%3});

		for(int i=0;i<numbers.length;i++) {
			int num=numbers[i];
			if(num==1||num==4||num==7) setState('L',num);
			else if(num==3||num==6||num==9) setState('R',num);
			else {
				int ld=dist(map.get(num),l);
				int rd=dist(map.get(num),r);
				if(ld<rd) setState('L',num);
				else if(ld>rd) setState('R',num);
				else {
					if(hand.equals("left")) setState('L',num);
					else setState('R',num);
				}
			}
		}
		return sb.toString();
	}
	static int dist(int[] a,int[] b) {
		return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
	}
	static void setState(char c,int num) {
		if(c=='L') l=map.get(num);
		else r=map.get(num);
		sb.append(c);
	}
	
	public static void main(String[] args) {
		int[] numbers= {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		System.out.println(solution(numbers,"right"));
	}
}