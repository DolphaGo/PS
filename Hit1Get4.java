package Hit1Get4;

import java.net.*;
import java.io.*;
public class Hit1Get4 {

	// User and Game Server Information
	static final String NICKNAME = "DolphaGo";
	static final String HOST = "localhost";
	static final int PORT = 1447; // Do not modify
	
	// predefined variables(Do not modify these values)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 124;
	static final int NUMBER_OF_BALLS = 5;
	static final int[][] HOLES = {{0, 0}, {130, 0}, {260, 0}, {0, 130}, {130, 130}, {260, 130}};
	
	public static void main(String[] args) {
		
		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		int[][] balls = new int[NUMBER_OF_BALLS][2];

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = "9901/" + NICKNAME;
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play.");
			
			while (socket != null) {
				
				bytes = new byte[1024];
				
				int readByteCount = is.read(bytes);
				recv_data = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("Data Received: " + recv_data);
				
				String[] split_data = recv_data.split("/");
				if (split_data[0].equals("9909")) break;
				
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Integer.parseInt(split_data[idx++]);
						}
					}
				}
				catch (Exception e) {
					bytes = new byte[1024];
					balls = new int[NUMBER_OF_BALLS][2];
					bytes = "9902/9902".getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}
				double angle = 0;
				double power = 0;
				for(int i=1;i<NUMBER_OF_BALLS;i++) {
					if(balls[i][0]==0&&balls[i][1]==0) continue;
					angle=Math.atan2(balls[i][0]-balls[0][0],balls[i][1]-balls[0][1])*(180.0/Math.PI);
					break;
				}
				power=120;
				if(angle<0) angle+=360;
				String merged_data = angle + "/" + power;
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
			}
			os.close();
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
