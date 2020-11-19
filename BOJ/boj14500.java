import java.io.*;
import java.util.*;

public class boj14500 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int h=Integer.parseInt(st.nextToken());
		int w=Integer.parseInt(st.nextToken());
		int map[][]=new int[h][w];
		for(int y=0;y<h;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<w;x++) {
				map[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		int arr[][][]= {
			{
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0}
			},
			{
				{1,0,0,0},
				{1,1,0,0},
				{1,0,0,0},
				{0,0,0,0}
			},
			{
				{0,1,0,0},
				{1,1,0,0},
				{0,1,0,0},
				{0,0,0,0}
			},
			{
				{0,1,0,0},
				{1,1,1,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{1,1,1,0},
				{0,1,0,0},
				{0,0,0,0},
				{0,0,0,0}
			},{
				{1,1,0,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0}
			},{
				{1,0,0,0},
				{1,1,0,0},
				{0,1,0,0},
				{0,0,0,0}
			},
			{
				{0,1,0,0},
				{1,1,0,0},
				{1,0,0,0},
				{0,0,0,0}
			},
			{
				{1,1,0,0},
				{0,1,1,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{0,1,1,0},
				{1,1,0,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{1,0,0,0},
				{1,0,0,0},
				{1,1,0,0},
				{0,0,0,0}
			},
			{
				{1,1,1,0},
				{1,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{1,1,0,0},
				{0,1,0,0},
				{0,1,0,0},
				{0,0,0,0}
			},
			{
				{0,0,1,0},
				{1,1,1,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{0,1,0,0},
				{0,1,0,0},
				{1,1,0,0},
				{0,0,0,0}
			},
			{
				{1,0,0,0},
				{1,1,1,0},
				{0,0,0,0},
				{0,0,0,0}
			},
			{
				{1,1,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{0,0,0,0}
			},
			{
				{1,1,1,0},
				{0,0,1,0},
				{0,0,0,0},
				{0,0,0,0}
			},
		};
		int answer=0;
		for(int y=0;y<h;y++) {
			for(int x=0;x<w;x++) {
				for(int i=0;i<arr.length;i++) {
					int res=0;
					int[][] window=arr[i];
					for(int yy=y;yy<y+4;yy++) {
						if(yy>=h) continue;
						for(int xx=x;xx<x+4;xx++) {
							if(xx>=w) continue;
							res+=window[yy-y][xx-x]*map[yy][xx];
						}
					}
					answer=Math.max(res,answer);
				}
			}
		}
		System.out.println(answer);
	}
}