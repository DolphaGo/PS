import java.io.*;
import java.util.*;

public class Main {
    static int[] comm,turn;
    static int answer;
    static int[][] map={
            {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40},
            {10,13,16,19,25,30,35,40},
            {20,22,24,25,30,35,40},
            {30,28,27,26,25,30,35,40}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        comm=new int[10];
        for(int i=0;i<10;i++) comm[i]=Integer.parseInt(st.nextToken());

        answer=0;
        turn=new int[10];
        solution(0);
        System.out.println(answer);
    }

    static void solution(int v){
        if(v==10){
            int res=simulation();
            answer=Math.max(answer,res);
            return;
        }

        for(int i=0;i<4;i++){
            turn[v]=i;
            solution(v+1);
        }
    }
    static int simulation(){
        int res=0;
        int[][] horse=new int[4][2];
        for(int i=0;i<10;i++){
            int nowHorse=turn[i];
            int move=comm[i];

            //이미 도착한 말을 움직일 수 없다.
            if(horse[nowHorse][1]==-1) return 0;

            //맵 전환
            int val=map[horse[nowHorse][0]][horse[nowHorse][1]];
            if(horse[nowHorse][0]==0 && (val==10 || val==20 || val==30)){
                horse[nowHorse][0]=val/10;
                horse[nowHorse][1]=0;
            }

            //이동
            horse[nowHorse][1]+=move;

            //도착한다면
            if(map[horse[nowHorse][0]].length<= horse[nowHorse][1])  horse[nowHorse][1]=-1;
            else{
                //다른 말들하고 겹치는 것은 없는지 검사하는 로직
                for(int horseNum=0;horseNum<4;horseNum++){
                    if(horseNum==nowHorse) continue;
                    int[] horseState=horse[horseNum];
                    int mapNum=horseState[0];
                    int loc=horseState[1];
                    if(loc==-1) continue; //이미 도착한 말

                    //맵 자체
                    if(horse[nowHorse][0]==mapNum&&horse[nowHorse][1]==loc) return 0;
                    //40
                    if(map[mapNum][loc]==40&&map[horse[nowHorse][0]][horse[nowHorse][1]]==40) return 0;

                    //공통구간 25, 30, 35 추가 검사(맵번호 1,2,3에 대해)
                    if(mapNum>0){
                        if(specialCheck(horse[nowHorse])&&specialCheck(horseState)){
                            if(map[mapNum][loc]==map[horse[nowHorse][0]][horse[nowHorse][1]]) return 0;
                        }
                    }
                }
                res+=map[horse[nowHorse][0]][horse[nowHorse][1]];
            }
        }
        return res;
    }
    static boolean specialCheck(int[] state){
        int mapNum=state[0];
        int loc=state[1];
        if(mapNum==1 || mapNum==3) return 4<=loc && loc<7;
        else return 3<=loc && loc<6;
    }
}