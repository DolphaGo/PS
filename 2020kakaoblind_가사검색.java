
import java.util.*;
class Solution {
    static class Node {
        Map<Character,Node> children;
        int count;

        Node(){
            this.children= new HashMap<>();
            this.count=0;
        }
    }

    static class Trie{
        private Node front;
        private Node back;

        Trie(){
            this.front=new Node();
            this.back=new Node();
        }

        void insert(String word){
            insertFront(word);
            insertBack(word);
        }

        private void insertFront(String word){
            Node node=this.front;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                node.count++;

                if(node.children.get(c)==null){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
        }
        private void insertBack(String word){
            Node node=this.back;

            for(int i=word.length()-1;i>=0;i--){
                char c=word.charAt(i);

                node.count++;
                if(node.children.get(c)==null){
                    node.children.put(c,new Node());
                }
                node=node.children.get(c);
            }
        }
        
        public int getCount(String query){
            if(query.charAt(0)=='?') return getCountFromBack(query);
            else return getCountFromFront(query);
        }

        private int getCountFromFront(String query) {
            Node node=front;
            for(int i=0;i<query.length();i++){
                char c=query.charAt(i);

                if(c=='?') break;
                if(node.children.get(c)==null) return 0;
                node=node.children.get(c);
            }
            return node.count;
        }

        private int getCountFromBack(String query) {
            Node node=back;
            for(int i=query.length()-1;i>=0;i--){
                char c=query.charAt(i);

                if(c=='?') break;
                if(node.children.get(c)==null) return 0;
                node=node.children.get(c);
            }
            return node.count;
        }
    }

    public static void main(String[] args) {
        String[] words={"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries={"fro??", "????o", "fr???", "fro???", "pro?"};
        System.out.println(Arrays.toString(solution(words,queries)));
    }

    private static int[] solution(String[] words, String[] queries) {
        int n=queries.length;
        int[] answer=new int[n];

        Trie[] tries=new Trie[100001];
        for(String word:words){
            int len=word.length();
            if(tries[len]==null) tries[len]=new Trie();
            tries[len].insert(word);
        }


        for(int i=0;i<queries.length;i++){
            String query=queries[i];
            int len=query.length();

            if(tries[len]==null) answer[i]=0;
            else answer[i]=tries[len].getCount(query);
        }
        return answer;
    }
}