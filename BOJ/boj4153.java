import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int[] parse = getElements(st);
            if (isLastInput(parse)) { break; }
            sb.append(isRight(parse) ? "right" : "wrong").append("\n");
        }
        System.out.print(sb);
    }

    private static boolean isLastInput(final int[] parse) {
        return parse[0] == 0 && parse[1] == 0 && parse[2] == 0;
    }

    private static int[] getElements(final StringTokenizer st) {
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(result);
        return result;
    }

    private static boolean isRight(final int[] parse) {
        return Math.pow(parse[2], 2) == Math.pow(parse[0], 2) + Math.pow(parse[1], 2);
    }
}
