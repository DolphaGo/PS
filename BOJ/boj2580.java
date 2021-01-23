import java.io.*;
import java.util.*;

public class Main{
    static int map[][];
    static boolean flag;
    static ArrayList<int[]> zeros = new ArrayList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[9][9];
        for (int y = 0; y < 9; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 9; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 0)
                    zeros.add(new int[] { y, x });
            }
        }
        backtracking(0);
    }

    static void answerPrint() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    static int[] squarecheck(int yy, int xx) {
        int Area[] = searchArea(yy, xx);
        int[] data = new int[10];
        for (int y = Area[0]; y < Area[0] + 3; y++) {
            for (int x = Area[1]; x < Area[1] + 3; x++) {
                data[map[y][x]]++;
            }
        }
        return data;
    }

    static int[] colcheck(int r) {
        int[] list = new int[10];
        for (int y = 0; y < 9; y++)
            list[map[y][r]]++;
        return list;
    }

    static int[] rowcheck(int c) {
        int[] list = new int[10];
        for (int x = 0; x < 9; x++)
            list[map[c][x]]++;
        return list;
    }

    static int[] searchArea(int y, int x) {
        y = y / 3;
        x = x / 3;
        return new int[] { y * 3, x * 3 };
    }

    static void backtracking(int v) {
        if (v == zeros.size()) {
            answerPrint();
            System.exit(0);
        }
        int y = zeros.get(v)[0];
        int x = zeros.get(v)[1];
        int cols[] = colcheck(x);
        int rows[] = rowcheck(y);
        int squares[] = squarecheck(y, x);
        for (int num = 1; num <= 9; num++) {
            if (cols[num] > 0 || rows[num] > 0 || squares[num] > 0)
                continue;
            map[y][x] = num;
            backtracking(v + 1);
            map[y][x] = 0;
        }
    }
}
