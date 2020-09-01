import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        char[] arr=br.readLine().toCharArray();
        ArrayList<int[]> pair=new ArrayList<>();

        TreeSet<String> answer=new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='('){
                stack.push(i);
            }else if(arr[i]==')'){
                int prev=stack.pop();
                int next=i;
                pair.add(new int[]{prev,next});
            }
        }

        int n=pair.size();
        StringBuilder builder=new StringBuilder();
        for(int i=1;i<(1<<n);i++){
            char[] test=arr.clone();
            for(int j=0;j<n;j++) {
                if ((i & (1 << j)) > 0) {
                    int[] p = pair.get(j);
                    test[p[0]] = '\0';
                    test[p[1]] = '\0';
                }
            }

            for(int j=0;j<test.length;j++){
                if(test[j]=='\0') continue;
                builder.append(test[j]);
            }
            answer.add(builder.toString());
            builder.setLength(0);
        }

        StringBuilder sb=new StringBuilder();
        for(String s:answer) sb.append(s).append("\n");
        System.out.print(sb);
    }
}