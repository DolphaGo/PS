import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        Map<Character,Node> children;
        Node() {children=new HashMap<>();}
    }
    static class Trie{
        Node root;
        Trie(){ this.root=new Node();}

        boolean insert(String s){
            Node node=root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(node.children.get(c)==null){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
            return node.children.size() <= 0;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tc=1;tc<=T;tc++){
            int n=Integer.parseInt(br.readLine());
            Trie trie=new Trie();

            boolean flag=true;
            PriorityQueue<String> pq=new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return Integer.compare(o2.length(),o1.length());
                }
            });
            for(int i=0;i<n;i++){
                pq.add(br.readLine());
            }
            while(!pq.isEmpty()){
                String s=pq.poll();
                if(!trie.insert(s)) {
                    flag=false;
                    pq.clear();
                }
            }
            if(flag) sb.append("YES");
            else sb.append("NO");
            sb.append("\n");
        }
        System.out.print(sb);
    }
}