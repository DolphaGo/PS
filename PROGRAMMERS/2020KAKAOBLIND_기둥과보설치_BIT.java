import java.util.*;
import java.io.*;

public class Main {
    static final int PILLAR = 0, BRIDGE = 1, REMOVE = 0;
    static int n;
    static int[][] map;

    static int[][] solution(int n1, int[][] build_frame) {
        n = n1;
        int count = 0;

        map = new int[n+1][n+1];

        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            if (frame[2] == PILLAR) {//기둥
                if (frame[3] == REMOVE) { //제거
                    if (canRemove(y, x, PILLAR)) { // 제거 가능 여부 확인
                        Commit(y,x,PILLAR,false);
                        --count;
                    }
                } else { // 설치
                    if (canAdd(y, x, PILLAR)) { // 추가 가능 여부 확인
                        Commit(y,x,PILLAR,true);
                        ++count;
                    }
                }
            } else {//보
                if (frame[3] == REMOVE) { //제거
                    if (canRemove(y, x, BRIDGE)) { // 제거 가능 여부 확인
                        Commit(y,x,BRIDGE,false);
                        --count;
                    }
                } else { // 설치
                    if (canAdd(y, x, BRIDGE)) { // 설치 가능 여부 확인
                        Commit(y,x, BRIDGE,true);
                        ++count;
                    }
                }
            }
        }

        int idx = 0;
        int[][] answer = new int[count][3];
        for (int x = 0; x <= n ; x++) {
            for (int y = 0; y <= n ; y++) {
                if (checkBit(y,x,PILLAR)) answer[idx++] = new int[]{x , y ,PILLAR};
                if (checkBit(y,x,BRIDGE)) answer[idx++] = new int[]{x , y ,BRIDGE};
            }
        }
        return answer;
    }

    static boolean checkBit(int y, int x, int type){
       return (map[y][x]&(1<<type)) >0;
    }

    static boolean canAdd(int y, int x, int type){
        if(type==PILLAR) return y==0 || checkBit(y-1,x,PILLAR) || checkBit(y,x,BRIDGE) || (x-1>=0 && checkBit(y,x-1,BRIDGE));
        else return (y-1>=0&& checkBit(y-1,x,PILLAR)) //왼쪽 아래에 기둥이 있거나
                || (y-1>=0&&x+1<=n&& checkBit(y-1,x+1,PILLAR)) //오른쪽 아래에 기둥이 있거나
                || (x-1>=0&& x+1<=n && checkBit(y,x-1,BRIDGE) && checkBit(y,x+1,BRIDGE)); //양 옆에 보가 있을 때
    }

    static boolean canRemove(int y, int x, int type) {
        //임시로 기둥이나 보를 제거해보자.
        Commit(y, x, type, false);

        //위의 제거로 인해서 다른 기둥, 보에 영향이 없는지 확인해보자.
        for (int i = 0; i <= n ; i++) {
            for (int j = 0; j <= n ; j++) {
                // 다른 기둥과 보에 이상이 생겼다면 삭제할 수 없으므로 원상태로 되돌린다.
                if (checkBit(i,j,PILLAR) && !canAdd(i, j, PILLAR)) {
                    Commit(y, x, type, true);
                    return false;
                }
                if (checkBit(i,j,BRIDGE)&& !canAdd(i, j, BRIDGE)) {
                    Commit(y, x, type, true);
                    return false;
                }
            }
        }
        return true;
    }

    static void Commit(int y, int x, int type, boolean state){
        if(state) map[y][x]|=(1<<type); //비트 켜기
        else map[y][x]&=~(1<<type); //비트 끄기
    }

    public static void main(String[] args) throws IOException {
        int[][] build_frame ={{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
        int n = 5;

        int[][] result = solution(n, build_frame);
        for (int y = 0; y < result.length; y++) {
            System.out.println(Arrays.toString(result[y]));
        }
    }
}