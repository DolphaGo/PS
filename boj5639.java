import java.io.*;
import java.util.*;

public class Main {
	static class Tree{
		int val;
		Tree left;
		Tree right;
		public Tree(int val,Tree left,Tree right){
			this.val=val;
			this.left=left;
			this.right=right;
		}

		public Tree makeTree(Tree tree, int data){
			if(tree==null) return new Tree(data,null,null);
			else if(data<tree.val){ //왼쪽 서브트리
				tree.left= makeTree(tree.left,data);
			}else{ //오른쪽 서브 트리
				tree.right= makeTree(tree.right,data);
			}
			return tree;
		}
	}

	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Tree tree=new Tree(Integer.parseInt(br.readLine()),null,null);
		String s="";
		while((s=br.readLine())!=null){
			tree = tree.makeTree(tree,Integer.parseInt(s));
		}

		postorder(tree);
		System.out.println(sb.toString());
	}
	static void postorder(Tree tree){
		if(tree.left!=null) postorder(tree.left);
		if(tree.right!=null) postorder(tree.right);
		sb.append(tree.val+"\n");
	}
}
