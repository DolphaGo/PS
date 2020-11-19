import java.util.*;

public class 카카오인턴십_수식최대화 {
	static long answer;
	static int order[]=new int[3];
	static char c[]= {'+','-','*'};
	static boolean visit[]=new boolean[3];
	static ArrayList<String> arr=new ArrayList<>();
	static long solution(String expression) {
		answer=0;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<expression.length();i++) {
			char c=expression.charAt(i);
			if('0'<=c&&c<='9') sb.append(c);
			else {
				arr.add(sb.toString());
				arr.add(c+"");
				sb.setLength(0);
			}
		}
		arr.add(sb.toString());
		go(0);
		return answer;
	}
	static void go(int v) {
		if(v==3) {
			long val=calc();
			if(val<0) val*=-1;
			answer=answer<val?val:answer;
			return;
		}
		for(int i=0;i<3;i++) {
			if(!visit[i]) {
				visit[i]=true;
				order[v]=i;
				go(v+1);
				visit[i]=false;
			}
		}
	}
	static long calc() {
		ArrayList<String> list=new ArrayList<>();
		for(int i=0;i<arr.size();i++) list.add(arr.get(i));
		//길이가 7인 것: 0(값) 1 2(값) 3 4(값) 5 6(값)
		//3이 2(값)와 4(값)를 가져감 => 0(값) 1 2(새로운 값) 
		//사이즈가 2가 줄음 1 값 5 값 7 값
		for(int i=0;i<3;i++) {
			char cur=c[order[i]];
			for(int j=1;j<list.size()-1;j++) {
				String cs=list.get(j);
				if(cs.equals(cur+"")) {
					long prev=Long.parseLong(list.get(j-1));
					long next=Long.parseLong(list.get(j+1));
					if(order[i]==0) prev+=next;
					else if(order[i]==1) prev-=next;
					else prev*=next;
					list.remove(j+1);
					list.set(j, prev+"");
					list.remove(j-1);
					j--;
				}
			}
		}
		long ret=Long.parseLong(list.get(0));
		return ret;
	}

	public static void main(String[] args) {
		String exp="100-200*300-500+20";
		System.out.println(solution(exp));
	}
}
