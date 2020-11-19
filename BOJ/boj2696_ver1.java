import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            int n =Integer.parseInt(br.readLine());
            int[] arr=new int[n];
            //입력
            int idx=0;
            for(int i = 0; i<=(n -1)/10; i++){
                st=new StringTokenizer(br.readLine());
                while(st.hasMoreTokens()){
                    arr[idx++]=Integer.parseInt(st.nextToken());;
                }
            }
            sb.append((n +1)/2).append("\n");
            idx=0;
            //query
            for(int i = 0; i< n; i++) {
                if (i % 2 == 0) {
                    sb.append(getMedian(arr,i)).append(" ");
                    if (++idx % 10 == 0) sb.append("\n");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static int getMedian(int[] arr,int i){
        Arrays.sort(arr,0,i+1);
        return arr[i/2];
    }
}