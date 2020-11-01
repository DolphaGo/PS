import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int h=Integer.parseInt(st.nextToken());
        int w=Integer.parseInt(st.nextToken());

        int[] arr=new int[w];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<w;i++) arr[i]=Integer.parseInt(st.nextToken());

        int answer=0;
        int v=arr[0];
        Queue<Integer> q=new LinkedList<>();
        for(int i=1;i<w;i++){
            if(v>arr[i]) q.add(i);
            else{
                while(!q.isEmpty()){
                    int p=q.poll();
                    answer+=v-arr[p];
                    arr[p]=v;
                }
                v=arr[i];            }
        }
        q.clear();
        v=arr[w-1];
        for(int i=w-2;i>=0;i--){
            if(v>arr[i]) q.add(i);
            else{
                while(!q.isEmpty()){
                    int p=q.poll();
                    answer+=v-arr[p];
                    arr[p]=v;
                }
                v=arr[i];
            }
        }
        System.out.println(answer);
    }
}