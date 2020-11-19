import java.util.*;

class Solution {
    public static int[] solution(String s) {
        String trimS=s.substring(1,s.length()-1);
        StringTokenizer st=new StringTokenizer(trimS,"{");

        HashMap<Integer,Integer> map=new HashMap<>();

        while(st.hasMoreTokens()){
            String data=st.nextToken();
            if(data.charAt(data.length()-1)==',') data=data.substring(0,data.length()-1);
            data=data.substring(0,data.length()-1);
            StringTokenizer parse=new StringTokenizer(data,",");
            while(parse.hasMoreTokens()){
                int val=Integer.parseInt(parse.nextToken());
                if(map.get(val)==null){
                    map.put(val,1);
                }else{
                    int cnt=map.get(val);
                    map.put(val,cnt+1);
                }
            }
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2){
                return Integer.compare(o2[1],o1[1]);
            }
        });

        for(int key:map.keySet()) pq.add(new int[]{key,map.get(key)});

        int max=pq.peek()[1];

        int[] arr=new int[max];
        int idx=0;
        while(!pq.isEmpty()){
            int[] p=pq.poll();
            arr[idx++]=p[0];
        }

        return arr;
    }
    public static void main(String[] args) {
        String s="{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(solution(s)));
    }

}