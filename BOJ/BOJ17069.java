import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = { 0, 1, 1 };
    static int[] dx = { 1, 0, 1 };

    static int[][] dir = {
            { 0, 2 }, // 가로
            { 1, 2 }, // 세로
            { 0, 1, 2 } // 대각선
    };

    static int n;
    static int[][] map;
    static long[][][] dp;
    static boolean[][][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[n][n][3];
        check = new boolean[n][n][3];
        System.out.println(dfs(0, 1, 0));
    }

    private static long dfs(final int y, final int x, final int d) {
        if (y == n - 1 && x == n - 1) {
            return 1;
        }

        if (check[y][x][d] ) {
            return dp[y][x][d];
        }

        for (int di : dir[d]) {
            int ny = y + dy[di];
            int nx = x + dx[di];
            if (isRange(ny, nx) && possible(ny, nx, di)) {
                dp[y][x][d] += dfs(ny, nx, di);
            }
        }
        check[y][x][d] = true;
        return dp[y][x][d];
    }

    private static boolean possible(final int ny, final int nx, final int dir) {
        if (dir == 2) {
            return map[ny - 1][nx] == 0 && map[ny][nx - 1] == 0 && map[ny][nx] == 0;
        }

        return map[ny][nx] == 0;
    }

    private static boolean isRange(final int ny, final int nx) {
        return ny >= 0 && nx >= 0 && ny < n && nx < n;
    }

}
