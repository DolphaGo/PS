

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        int[][] arr=new int[n][2];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return Integer.compare(o1[1],o2[1]);
                return Integer.compare(o1[0],o2[0]);
            }
        });

        PriorityQueue<Integer> q=new PriorityQueue<>();
        int idx=0, answer=1;
        q.add(arr[idx++][1]);
        while(idx<n){
            int prev=q.peek();
            int[] next=arr[idx++];
            if(prev<=next[0]){ //강의실 업데이트
                q.poll();
                q.add(next[1]);
            }else{ //새로운 강의실 배치
                ++answer;
                q.add(next[1]);
            }
        }
        System.out.println(answer);
    }
}