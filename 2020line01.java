import java.util.*;

public class Main {
    public static void main(String[] args){
        int[][] boxes=new int[][]{
                {1, 2},
                {2, 3},
                {3, 1}
        };

        HashMap<Integer,Integer> map=new HashMap<>();

        int res=boxes.length;

        for(int i=0;i<boxes.length;i++){
            int[] box=boxes[i];
            int a=box[0];
            int b=box[1];
            if(a==b) {
                res--;
                continue;
            }
            if(map.get(a)==null) map.put(a,1);
            else map.put(a,map.get(a)+1);

            if(map.get(b)==null) map.put(b,1);
            else map.put(b,map.get(b)+1);
        }

        for(int val:map.keySet()){
            int num=map.get(val);
            res-=num/2;
        }

        System.out.println(res);
    }
}