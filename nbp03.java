import java.util.*;
class nbp03 {
	 public static int solution(int[] p) {
	        int answer = 0;
	        TreeMap<Integer, Integer> map=new TreeMap<Integer, Integer>();
	        for(int i=0;i<p.length;i++) {
	        	int val=p[i];
	        	if(map.get(val)==null) map.put(val,0);
	        	int num=map.get(val)+1;
	        	map.put(val,num);
	        }
	        Queue<Integer> rmList=new LinkedList<Integer>();
	        while(map.size()>0) {
	        	//첫번째 키 값을 가져온다.
	        	int first=map.firstKey();
	        	//하나를 줄여주고
	        	int num=map.get(first)-1;
	        	//그래도 1개 이상이라면 다시 값으로 넣어줌
	        	//0개면 지운다.
	        	if(num>0) map.put(first,num);
	        	else rmList.add(first);
	        	
	        	for(int keys:map.keySet()) {
	        		//첫 번째는 제외하고
	        		if(keys==first) continue;
	        		
	        		//양을 하나 줄여주고 0개면 지우고 1개이상이면 업데이트
	        		num=map.get(keys)-1;
	        		if(num>0) map.put(keys,num);
		        	else rmList.add(keys);
	        		
	        		++answer;
	        	}
	        	
	        	while(!rmList.isEmpty()) {
	        		map.remove(rmList.poll());
	        	}
	        }
	        return answer;
	    }
    public static void main(String[] args) {
    	int[] p= {103,101,103,103,101,102,100,100,101,104};
    	System.out.println(solution(p));
	}
}