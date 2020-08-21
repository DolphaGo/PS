import java.io.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        char[][] map=new char[n][n];
        for(int y=0;y<n;y++){
            map[y]=br.readLine().toCharArray();
        }

        int state=Integer.parseInt(br.readLine());
        if(state==2) colSwap(map);
        else if(state==3) rowSwap(map);
        print(map);
    }
    static void print(char[][] map){
        StringBuilder sb=new StringBuilder();
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void colSwap(char[][] map){
        for(int y=0;y<n;y++){
            for(int x=0;x<n/2;x++){
                char temp=map[y][x];
                map[y][x]=map[y][n-1-x];
                map[y][n-1-x]=temp;
            }
        }
    }
    static void rowSwap(char[][] map){
        for(int y=0;y<n/2;y++){
            for(int x=0;x<n;x++){
                char temp=map[y][x];
                map[y][x]=map[n-1-y][x];
                map[n-1-y][x]=temp;
            }
        }
    }
}
