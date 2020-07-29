import java.io.*;

public class boj1550 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String hexString=br.readLine();
		System.out.println(Integer.parseInt(hexString,16));
	}
}