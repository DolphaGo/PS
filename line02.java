import java.util.*;
import java.io.*;

public class line02 {
	static String answer_sheet="4132315142";
	static String[] sheets= {"3241523133","4121314445","3243523133","4433325251","2412313253"};
	public static void main(String[] args) throws IOException {
		System.out.println(solution(answer_sheet,sheets));
	}
	static int n,ans;
	static int solution(String answer_sheet,String[] sheets) {
		n=answer_sheet.length();
		ans=Integer.MIN_VALUE;
		go(0,0);
		return ans;
	}
	static int cho[]=new int[2];
	static void go(int v,int select) {
		if(select==2) {
			int a=cho[0];
			int b=cho[1];
			int allcnt=0;
			int cnt=0;
			int max=Integer.MIN_VALUE;
			for(int i=0;i<n;i++) {
				int answer=answer_sheet.charAt(i)-'0';
				int A=sheets[a].charAt(i)-'0';
				int B=sheets[b].charAt(i)-'0';
				if(A==B&&A!=answer) {
					++allcnt;
					++cnt;
				}else cnt=0;
				max=Math.max(cnt, max);
			}
			int res=allcnt+max*max;
			ans=Math.max(ans,res);
			return;
		}
		if(v>=sheets.length) return;
		
		cho[select]=v;
		go(v+1,select+1);
		go(v+1,select);
	}
}