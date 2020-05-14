package Keep;
import java.util.*;
import java.io.*;

public class Line05 {
	static String[] directory = new String[] { "/", "/hello", "/hello/tmp", "/root", "/root/abcd", "/root/abcd/etc",
			"/root/abcd/hello" };
	static String[] command = new String[] { "mkdir /root/tmp", "cp /hello /root/tmp", "rm /hello" };

	static String[][] dataSource= {
			{"doc1", "t1", "t2", "t3"}, //3개
			{"doc2", "t0", "t2", "t3"}, //2개
			{"doc3", "t1", "t6", "t7"}, //1개
			{"Doc2", "t1", "t2", "t4"}, //2개
			{"doc5", "t6", "t100", "t8"}
	};
	static String[] tags= { "t1", "t2","t3"};
	
	public static void main(String[] args) throws IOException {
		String answer[]=solution(dataSource, tags);
		System.out.println(Arrays.toString(answer));
	}

	static ArrayList<String> list = new ArrayList<String>();

	static String[] solution(String[][] dataSource, String[] tags) {
		String[] answer=null;
		
		int n=dataSource.length;
		
		HashMap<String, Boolean> tagMap=new HashMap<String, Boolean>();
		for(String s:tags) tagMap.put(s,true);
		ArrayList<int[]> list=new ArrayList<int[]>();
		for(int i=0;i<n;i++) {
			int cnt=0;
			for(int j=1;j<dataSource[i].length;j++) {
				String tag=dataSource[i][j];
				if(tagMap.containsKey(tag)) ++cnt;
			}
			if(cnt>0) list.add(new int[] {i,cnt});
		}

		Collections.sort(list,new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				if(o2[1]==o1[1]) return dataSource[o1[0]][0].compareTo(dataSource[o2[0]][0]);
				return Integer.compare(o2[1],o1[1]);
			}
		});
		
		if(list.size()>=10) answer=new String[10];
		else answer=new String[list.size()];
		
		for(int i=0;i<answer.length;i++) {
			answer[i]=dataSource[list.get(i)[0]][0];
		}
		return answer;
	}
}