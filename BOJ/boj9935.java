import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String pattern = br.readLine();

        int pLen = pattern.length();

        char[] arr = new char[str.length()];
        int idx = 0;
        for (int i = 0; i < str.length(); i++) {
            arr[idx++] = str.charAt(i);

            if (idx >= pLen) {
                boolean chk = true;
                for (int j = 0; j < pLen; j++) {
                    if (arr[idx - pLen + j] != pattern.charAt(j)) {
                        chk = false;
                        break;
                    }
                }
                if (chk) {
                    idx -= pLen;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            sb.append(arr[i]);
        }
        System.out.println(idx == 0 ? "FRULA" : sb.toString());
    }
}
