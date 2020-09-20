import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        Map<Character,Node> children;
        int count;
        Node(){
            this.children=new HashMap<>();
            count=0;
        }
    }
    static class Trie{
        static int total=0;
        Node root;
        Trie(){ this.root=new Node();}

        void insert(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(node.children.get(c)==null){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
            node.count++;
            total++;
        }

        int find(String s){
            Node node=this.root;
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if(node.children.get(c)==null){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
            return node.count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        Trie trie=new Trie();
        TreeSet<String> set=new TreeSet<>();
        while((s=br.readLine())!=null){
            trie.insert(s);
            set.add(s);
        }
        StringBuilder sb=new StringBuilder();

        int total= Trie.total;
        for(String str:set){
            double ratio=100.0*trie.find(str)/total;
            sb.append(String.format("%s %.4f",str,ratio)).append("\n");
        }
        System.out.print(sb);
    }
}