class Node {
    Map<Character, Node> map;
    int count;
    Node() {
        map = new HashMap<>();
        count = 0;
    }
    void plusCount(){
        count+=1;
    }
}

class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.map.containsKey(c)) { node.map.put(c, new Node()); }
            node = node.map.get(c);
        }
        node.plusCount();
    }

    public boolean search(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.map.containsKey(c)) { return false; }
            node=node.map.get(c);
        }
        return node.count > 0;
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!node.map.containsKey(c)) { return false; }
            node=node.map.get(c);
        }
        return true;
    }
}