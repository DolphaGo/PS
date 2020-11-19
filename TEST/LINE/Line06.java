package Keep;
import java.util.*;
import java.io.*;

public class Line06 {
	static String[] directory = new String[] { "/", "/hello", "/hello/tmp", "/root", "/root/abcd", "/root/abcd/etc",
			"/root/abcd/hello" };
	static String[] command = new String[] { "mkdir /root/tmp", "cp /hello /root/tmp", "rm /hello" };

	public static void main(String[] args) throws IOException {
		String answer[] = solution(directory, command);
		System.out.println(Arrays.toString(answer));
	}

	static ArrayList<ArrayList<String>> list = new ArrayList<>();

	static String[] solution(String[] directory, String[] command) {
		String[] answer = null;
		StringTokenizer st;
		
		ArrayList<String> list=new ArrayList<String>();
		for(String s:directory) list.add(s);
		
		for(String comm:command) {
			st=new StringTokenizer(comm);
			String cname=st.nextToken();
			if(cname.equals("mkdir")) {
				list.add(st.nextToken());
			}else if(cname.equals("cp")) {
				String a=st.nextToken();
				String b=st.nextToken();
				int size=list.size();
				for(int i=0;i<size;i++) {
					String dir=list.get(i);
					if(dir.startsWith(a)) {
						for(String search:list) {
							if(search.equals(b)) {
								list.add(b+dir);
								break;
							}
						}
					}
				}
			}else {
				String a=st.nextToken();
				for(int i=0;i<list.size();i++) {
					String dir=list.get(i);
					if(dir.startsWith(a)) {
						list.remove(i--);
					}
				}
			}
		}
		
		Collections.sort(list);
		answer=new String[list.size()];
		for(int i=0;i<list.size();i++) answer[i]=list.get(i);
		return answer;
	}
}