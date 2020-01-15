import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char[] name=br.readLine().toCharArray();
		int alpha[]=new int[26];
		for(int i=0;i<name.length;i++) ++alpha[name[i]-'A'];
		char[] answer=new char[name.length];
		ArrayList<Character> list=new ArrayList<Character>();
		boolean flag=true;
		if(name.length%2==0) {
			for(int i=0;i<26;i++) {
				if(alpha[i]==0) continue;
				while(alpha[i]>=2) {
					list.add((char)('A'+i));
					alpha[i]-=2;
				}
				if(alpha[i]==1) {
					flag=false;
					break;
				}
			}
			if(flag) {
				int idx=0;
				while(idx<list.size()) {
					answer[idx]=list.get(idx);
					answer[name.length-1-idx]=list.get(idx);
					idx++;
				}
			}
		}else {
			for(int i=0;i<26;i++) {
				if(alpha[i]==0) continue;
				while(alpha[i]>=2) {
					list.add((char)('A'+i));
					alpha[i]-=2;
				}
			}
			char temp='\0';
			for(int i=0;i<26;i++) {
				if(alpha[i]==0) continue;
				if(alpha[i]==1&&temp=='\0') {
					temp=(char)('A'+i);
				}else {
					flag=false;
					break;
				}
			}
			if(flag) {
				int idx=0;
				while(idx<list.size()) {
					answer[idx]=list.get(idx);
					answer[name.length-1-idx]=list.get(idx);
					idx++;
				}
				answer[idx]=temp;
			}
		}
		if(flag) for(int i=0;i<name.length;i++) System.out.print(answer[i]);
		else System.out.println("I'm Sorry Hansoo");
	}
}
