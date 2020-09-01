import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        ArrayList<int[]> list=new ArrayList<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            list.add(new int[]{a,b});
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });

        int[] lis=new int[n];
        int idx=0;
        lis[idx++]=list.get(0)[1];
        for(int i=1;i<n;i++){
            int val=list.get(i)[1];
            if(lis[idx-1]<val) lis[idx++]=val;
            else{
                int li=lower_bound(lis,idx,val);
                lis[li]=val;
            }
        }
        System.out.println(n-idx);
    }
    static int lower_bound(int[] arr,int e,int val){
        int s=0;
        while(s<e){
            int m=(s+e)>>1;
            if(arr[m]<val) s=m+1;
            else e=m;
        }
        return e;
    }
}