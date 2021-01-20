import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] inOrder, postOrder, index;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        final int n = Integer.parseInt(br.readLine());
        inOrder = input(n);
        postOrder = input(n);
        index = new int[n + 1];
        for (int i = 0; i < n; i++) {
            index[inOrder[i]] = i;
        }
        go(0, n - 1, 0, n - 1);
        System.out.println(sb);
    }

    static int[] input(int n) throws IOException {
        final int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    static void go(int in_st, int in_en, int post_st, int post_en) {
        if (in_st > in_en || post_st > post_en) { return; }
        final int root = postOrder[post_en];
        sb.append(root + " ");
        final int p = index[root];
        final int left = p - in_st;
        go(in_st, p - 1, post_st, post_st + left - 1);
        go(p + 1, in_en, post_st + left, post_en - 1);
    }
}