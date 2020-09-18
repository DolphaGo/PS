import java.io.*;
import java.util.*;

public class Main {
    static Trie trie;
    static boolean[][] visit;
    static char[][] map;
    static StringBuilder sb=new StringBuilder();

    static class Node{
        Map<Character,Node> children;
        Node(){ children=new HashMap<>();}
    }

    static class Trie{
        Node root;
        Trie(){ root=new Node();}

        void insert(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(!node.children.containsKey(c)){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
        }

        boolean find(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(!node.children.containsKey(c)) return false;
                node=node.children.get(c);
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int w=Integer.parseInt(br.readLine());

        String[] s=new String[w];
        for(int i=0;i<w;i++) {
            s[i]=br.readLine();
        }
        Arrays.sort(s);
        br.readLine();
        int b=Integer.parseInt(br.readLine());
        char[][][] boggle=new char[b][4][4];
        for(int i=0;i<b;i++){
            for(int j=0;j<4;j++){
                boggle[i][j]=br.readLine().toCharArray();
            }
            if(i!=b-1) br.readLine();
        }

        visit=new boolean[4][4];

        Map<Integer,Integer> score=new HashMap<>();
        for(int i=1;i<=2;i++) score.put(i,0);
        for(int i=3;i<=4;i++) score.put(i,1);
        score.put(5,2); score.put(6,3); score.put(7,5); score.put(8,11);

        StringBuilder print=new StringBuilder();
        for(int i=0;i<b;i++) {
            map = boggle[i];
            trie=new Trie();

            String answer = "";
            int max = 0;
            int cnt=0;
            int boggleScore=0;
            for(int y=0;y<4;y++){
                for(int x=0;x<4;x++){
                    visit[y][x]=true;
                    sb.append(map[y][x]);
                    trie.insert(sb.toString());
                    makeTrie(y,x,sb,1);
                    visit[y][x]=false;
                    sb.setLength(sb.length()-1);
                }
            }

            for(String bs:s){
                if(trie.find(bs)){
                    ++cnt;
                    boggleScore+=score.get(bs.length());
                    if(max<bs.length()){
                        max=bs.length();
                        answer=bs;
                    }
                }
            }
            print.append(boggleScore + " " + answer + " " + cnt).append("\n");

            sb.setLength(0);
        }
        System.out.print(print);
    }
    static int dy[]={-1,-1,-1,0,1,1,1,0};
    static int dx[]={-1,0,1,1,1,0,-1,-1};

    static boolean isRange(int y,int x){
        return 0<=x&&x<4&&0<=y&&y<4;
    }
    static void makeTrie(int y, int x, StringBuilder sb,int len){
        for(int i=0;i<8;i++){
            int ny=y+dy[i];
            int nx=x+dx[i];
            if(isRange(ny,nx)&&!visit[ny][nx]&&len+1<=8){
                visit[ny][nx]=true;
                sb.append(map[ny][nx]);
                trie.insert(sb.toString());
                makeTrie(ny,nx,sb,len+1);
                visit[ny][nx]=false;
                sb.setLength(sb.length()-1);
            }
        }
    }
}