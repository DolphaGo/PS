import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        String[] orders = new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = new int[]{2, 3, 5};

        boolean[] courseVisit=new boolean[11];
        for(int val:course) courseVisit[val]=true;

        int n=orders.length;
        boolean[][] alpha=new boolean[n][26];
        for(int i=0;i<n;i++){
            String order=orders[i];
            for(int j=0;j<order.length();j++){
                char c=order.charAt(j);
                int idx=c-'A';
                alpha[i][idx]=true;
            }
        }
        int[] max=new int[11];
        Arrays.fill(max,1);
        List<String> list[]=new ArrayList[11];
        for(int i=2;i<=10;i++) list[i]=new ArrayList<>();

        TreeSet<String> set=new TreeSet<>();
        PriorityQueue<Character> q=new PriorityQueue<>();
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<n;i++){
            String order=orders[i];
            int len=order.length();
            for(int j=3;j<(1<<len);j++){ //최소 2개 선택부터
                int cnt=0;
                q.clear();
                for(int k=0;k<len;k++){
                    if((j&(1<<k))>0){
                        ++cnt;
                        q.add(order.charAt(k));
                    }
                }
                //고른 것이 course에 포함될 때
                if(courseVisit[cnt]){
                    sb.setLength(0);
                    while(!q.isEmpty()){
                        sb.append(q.poll());
                    }
                    //다른 유저들과 비교, 한 쌍이라도 있으면 됨
                    int total=0;
                    for(int o=i+1;o<n;o++){
                        boolean[] otherAlpha=alpha[o];
                        int ocnt=0;
                        for(int k=0;k<len;k++){
                            if((j&(1<<k))>0){
                               char c=order.charAt(k);
                               int idx=c-'A';
                               if(otherAlpha[idx]) ++ocnt;
                            }
                        }
                        //있다면
                        if(ocnt==cnt) total++;
                    }
                    if(max[cnt]<total){
                        list[cnt].clear();
                        max[cnt]=total;
                        list[cnt].add(sb.toString());
                    }else if(max[cnt]==total){
                        list[cnt].add(sb.toString());
                    }
                }
            }
        }

        for(int i=2;i<=10;i++){
            for(String s:list[i]){
                set.add(s);
            }
        }

        String[] answer=new String[set.size()];
        int idx=0;
        for(String s:set) answer[idx++]=s;

        System.out.println(Arrays.toString(answer));
    }
}