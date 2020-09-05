import java.io.*;
import java.util.*;

public class Main {
    static TreeMap<Character,Integer> map=new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        String[] DNA=new String[n];

        for(int i=0;i<n;i++){
            DNA[i]=br.readLine();
        }

        StringBuilder sb=new StringBuilder();
        int answer=0;
        for(int j=0;j<m;j++){
            int max=0;
            char pick='\0';
            init();

            for(int i=0;i<n;i++){
                char c=DNA[i].charAt(j);
                map.put(c,map.get(c)+1);
            }

            for(char c:map.keySet()){
                int value=map.get(c);
                if(max<value){
                    max=value;
                    pick=c;
                }
            }
            answer+=n-max;
            sb.append(pick);
        }
        System.out.println(sb.toString());
        System.out.println(answer);
    }
    static void init(){
        map.put('A',0); map.put('C',0);  map.put('G',0);  map.put('T',0);
    }
}