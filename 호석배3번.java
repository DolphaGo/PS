import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class 호석배3번 {
    static int h,w,k;
    static char[][] map;
    static int[] dy={-1,-1,-1,0,1,1,1,0};
    static int[] dx={-1,0,1,1,1,0,-1,-1};
    static Trie trie;
    static class Node{
        Map<Character,Node> children;
        int count;
        Node() {
            children=new HashMap<>();
            count=0;
        }
    }
    static class Trie{
        Node root;
        Trie() { root=new Node();}

        void insert(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(!node.children.containsKey(c)){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
            node.count=node.count+1;
        }
        int find(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(!node.children.containsKey(c)) return 0;
                node=node.children.get(c);
            }
            return node.count;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        h=Integer.parseInt(st.nextToken());
        w=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        map=new char[h+1][w+1];
        for(int y=1;y<=h;y++){
            String s=br.readLine();
            for(int x=1;x<=w;x++){
                map[y][x]=s.charAt(x-1);
            }
        }
        trie=new Trie();
        StringBuilder sb=new StringBuilder();

        for(int y=1;y<=h;y++){
            for(int x=1;x<=w;x++){
                sb.append(map[y][x]);
                trie.insert(sb.toString());
                makeTrie(y,x,sb,1);
                sb.setLength(0);
            }
        }

        for(int i=0;i<k;i++){
            String pattern=br.readLine();
            sb.append(trie.find(pattern)).append("\n");
        }
        System.out.print(sb);
    }
    static void makeTrie(int y, int x, StringBuilder sb,int len){
        if(len==5) return;
        for(int i=0;i<8;i++){
            int ny=(y+dy[i])%h;
            int nx=(x+dx[i])%w;

            if(ny<=0) ny+=h;
            if(nx<=0) nx+=w;

            sb.append(map[ny][nx]);
            trie.insert(sb.toString());
            makeTrie(ny,nx,sb,len+1);
            sb.setLength(sb.length()-1);
        }
    }
}