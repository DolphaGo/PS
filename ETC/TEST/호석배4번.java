import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 호석배4번 {
    static int n,k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            int e=0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0){
                    sum+=arr[j];
                    if(sum>=k){
                        e+=sum-k;
                        sum=0;
                    }
                }else sum=0;
            }
            answer=Math.max(answer,e);
        }
        System.out.println(answer);
    }
}