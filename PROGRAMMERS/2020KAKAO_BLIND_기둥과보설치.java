import java.util.*;
import java.io.*;

public class Main {
    static final int PILLAR = 0, BRIDGE = 1, REMOVE = 0;
    static int n;
    static boolean[][] bridge, pillar;

    static int[][] solution(int n1, int[][] build_frame) {
        n = n1;
        int count = 0;

        bridge = new boolean[n + 3][n + 3];
        pillar = new boolean[n + 3][n + 3];

        for (int[] frame : build_frame) {
            int x = frame[0] + 1;
            int y = frame[1] + 1;
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
        for (int x = 1; x <= n + 1; x++) {
            for (int y = 1; y <= n + 1; y++) {
                if (pillar[y][x]) answer[idx++] = new int[]{x - 1, y - 1, PILLAR};
                if (bridge[y][x]) answer[idx++] = new int[]{x - 1, y - 1, BRIDGE};
            }
        }
        return answer;
    }

    static boolean canAdd(int y, int x, int type){
        if(type==PILLAR) return y==1 || pillar[y-1][x] || bridge[y][x] || bridge[y][x-1];
        else return pillar[y-1][x] || pillar[y-1][x+1] || (bridge[y][x-1] && bridge[y][x+1]) ;
    }

    static boolean canRemove(int y, int x, int type) {
        //임시로 기둥이나 보를 제거해보자.
        Commit(y, x, type, false);

        //위의 제거로 인해서 다른 기둥, 보에 영향이 없는지 확인해보자.
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                // 다른 기둥과 보에 이상이 생겼다면 삭제할 수 없으므로 원상태로 되돌린다.
                if (pillar[i][j] && !canAdd(i, j, PILLAR)) {
                    Commit(y, x, type, true);
                    return false;
                }
                if (bridge[i][j] && !canAdd(i, j, BRIDGE)) {
                    Commit(y, x, type, true);
                    return false;
                }
            }
        }
        return true;
    }

    static void Commit(int y, int x, int type, boolean state){
        if(type==PILLAR) pillar[y][x]=state;
        else bridge[y][x]=state;
    }

    public static void main(String[] args) throws IOException {
        int[][] build_frame ={{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        int n = 5;

        int[][] result = solution(n, build_frame);
        for (int y = 0; y < result.length; y++) {
            System.out.println(Arrays.toString(result[y]));
        }
    }
}