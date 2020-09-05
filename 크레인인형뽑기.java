import java.util.*;

class Solution {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        ArrayList<Integer> list=new ArrayList<>();
        int n=board.length;
        for(int move:moves){
            int idx=move-1;

            for(int y=0;y<n;y++){
                if(board[y][idx]!=0){
                    list.add(board[y][idx]);
                    board[y][idx]=0;
                    break;
                }
            }

            if(list.size()>=2) {
                for (int i = 0; i < list.size() - 1; i++) {
                    int prev = list.get(i);
                    int next = list.get(i + 1);
                    if (prev==next){
                        answer+=2;
                        list.remove(i);
                        list.remove(i);
                        i-=2;//지워지는 부분 직전으로 가서 다시 검사한다.
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board={
                {0,0,0,0,0},
                {0,0,1,0,3},
                {0,2,5,0,1},
                {4,2,4,4,2},
                {3,5,1,3,1}
        };
        int[] moves={1,5,3,5,1,2,1,4};
        System.out.println(solution(board,moves));
    }

}