import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] arr=new char[5][5];
        int ex=0;
        for(int i=0;i<5;i++){
            arr[i]=br.readLine().toCharArray();
            ex=Math.max(ex,arr[i].length);
        }
        StringBuilder sb=new StringBuilder();
        for(int x=0;x<ex;x++){
            for(int y=0;y<5;y++){
                if(arr[y].length<=x) continue;
                sb.append(arr[y][x]);
            }
        }
        System.out.println(sb);
    }
}