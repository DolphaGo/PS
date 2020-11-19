import java.util.*;

public class Main2 {
    public static void main(String[] args){
        int[] ball=new int[]{
                1,2,3,4,5,6
        };

        int[] order=new int[]{
                6,2,5,1,4,3
        };

        int n=ball.length;
        int[] answer=new int[n];
        Deque<Integer> dq=new ArrayDeque<>();
        for(int i=0;i<n;i++) dq.addLast(ball[i]);
        int idx=0;
        HashMap<Integer,Boolean> check=new HashMap<>();
        for(int i=0;i<order.length;i++){
            int o=order[i];

            //다른 명령을 실행하기 전에 빠져나올 수 있는 ball이 있는지 확인한다.
            while(dq.size()>0&&check.get(dq.peekFirst())!=null){
                int b=dq.pollFirst();
                answer[idx++]=b;
                check.remove(b);
            }
            while(dq.size()>0&&check.get(dq.peekLast())!=null){
                int b=dq.pollLast();
                answer[idx++]=b;
                check.remove(b);
            }

            if(dq.size()>0&&o!=dq.peekFirst()&&o!=dq.peekLast()){//빠져나오지 못하는 상황
                check.put(o,true);
            }else{
                answer[idx++]=o;
                if(dq.peekFirst()==o) dq.pollFirst();
                else dq.pollLast();
            }
        }
        System.out.println(Arrays.toString(answer));
    }
}