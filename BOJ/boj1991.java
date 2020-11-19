import java.io.*;
import java.util.*;

class boj1991 {
	static class Tree{
		char alpha;
		Tree left,right;
		Tree(char alpha,Tree left,Tree right){
			this.alpha=alpha;
			this.left=left;
			this.right=right;
		}
	}
	static StringBuilder sb=new StringBuilder();
	static Tree tree[]=new Tree[26];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		
		for(int i=0;i<26;i++) {
			tree[i]=new Tree((char)('A'+i),null,null);
		}
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			char alpha=st.nextToken().charAt(0);
			char left=st.nextToken().charAt(0);
			char right=st.nextToken().charAt(0);
			if(left!='.') tree[alpha-'A'].left=tree[left-'A'];
			if(right!='.') tree[alpha-'A'].right=tree[right-'A'];
		}
		preorder(tree[0]);
		sb.append("\n");
		inorder(tree[0]);
		sb.append("\n");
		postorder(tree[0]);
		System.out.print(sb);
	}
	static void preorder(Tree node) {
		sb.append(node.alpha);
		if(node.left!=null) preorder(node.left);
		if(node.right!=null) preorder(node.right);
	}
	static void inorder(Tree node) {
		if(node.left!=null) inorder(node.left);
		sb.append(node.alpha);
		if(node.right!=null) inorder(node.right);
	}
	static void postorder(Tree node) {
		if(node.left!=null) postorder(node.left);
		if(node.right!=null) postorder(node.right);
		sb.append(node.alpha);
	}
}