import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        TreeMap<String,Node> map;
        Node(){ map=new TreeMap<>();}
    }
    static class Trie{
        Node root;
        Trie(){ root=new Node(); }

        void insert(String[] strings){
            Node node=root;
            for(String s:strings){
                if(!node.map.containsKey(s)) node.map.put(s,new Node());
                node=node.map.get(s);
            }
        }
        public Node getRoot(){
            return root;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Trie trie=new Trie();

        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int k=Integer.parseInt(st.nextToken());
            String[] strings=new String[k];
            for(int j=0;j<k;j++){
                strings[j]=st.nextToken();
            }
            trie.insert(strings);
        }

        Node root=trie.getRoot();
        StringBuilder sb=new StringBuilder();
        getDisplay(root,0,sb);
        System.out.print(sb);
    }
    static void getDisplay(Node cur,int depth,StringBuilder sb){
        for(String s:cur.map.keySet()){
            makeDepth(depth,sb);
            sb.append(s).append("\n");
            Node next=cur.map.get(s);
            getDisplay(next,depth+2,sb);
        }
    }
    static void makeDepth(int depth,StringBuilder sb){
        while(depth-->0) sb.append('-');
    }
}