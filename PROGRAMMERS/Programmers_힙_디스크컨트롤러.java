import java.util.*;

class Solution {
    public static int solution(int[][] jobs) {
        //요청 시간이 먼저인 순으로 정렬
        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for(int i=0;i<jobs.length;i++){
            q.add(jobs[i]);
        }

        //처리 시간이 작은 순으로 정렬
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1[1],o2[1]);
            }
        });

        int answer=0,t=0;
        int proc=0;
        while(proc< jobs.length) {
            while(!q.isEmpty()&&q.peek()[0]<=t){
                pq.add(q.poll());
            }

            if(!pq.isEmpty()) {
                int[] p=pq.poll();
                answer+=(t-p[0])+p[1];
                proc++;
                t+=p[1];
            }else t++; // 아직 처리 불가
        }
        answer/=jobs.length;
        return answer;
    }
    public static void main(String[] args) {
        int[][] jobs={
                {0,3},
                {1,9},
                {2,6}
        };
        System.out.println(solution(jobs));
    }
}

