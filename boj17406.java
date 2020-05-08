import java.util.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class boj17406 {
	static int n,m,k,answer;
	static int[][] A;
	static int[][] command;
	static int[] exec;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		A=new int[n][m];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<m;x++) {
				A[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		command=new int[k][3];
		visit=new boolean[k];
		exec=new int[k];
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			command[i][0]=Integer.parseInt(st.nextToken())-1;
			command[i][1]=Integer.parseInt(st.nextToken())-1;
			command[i][2]=Integer.parseInt(st.nextToken());
		}
		
		answer=Integer.MAX_VALUE;
		go(0);
		System.out.println(answer);
	}
	static void go(int v) {
		if(v==k) {
			int dummy[][]=new int[n][m];
			for(int y=0;y<n;y++) dummy[y]=A[y].clone();
			rotate(dummy);
			answer=Math.min(answer,getval(dummy));
			return;
		}
		for(int i=0;i<k;i++) {
			if(visit[i]) continue;
			visit[i]=true;
			exec[v]=i;
			go(v+1);
			visit[i]=false;
		}
	}
	static void rotate(int[][] arr) {
		for(int i=0;i<k;i++) {
			int turn=exec[i];
			int r=command[turn][0];
			int c=command[turn][1];
			int s=command[turn][2];
			
			for(int rad=s;rad>0;--rad) {
				int tmp=arr[r-rad][c-rad];
				
				for(int y=r-rad+1;y<=r+rad;y++) {
					arr[y-1][c-rad]=arr[y][c-rad];
				}
				
				for(int x=c-rad+1;x<=c+rad;x++) {
					arr[r+rad][x-1]=arr[r+rad][x];
				}
				
				for(int y=r+rad-1;y>=r-rad;y--) {
					arr[y+1][c+rad]=arr[y][c+rad];
				}
				
				for(int x=c+rad-1;x>=c-rad+1;x--) {
					arr[r-rad][x+1]=arr[r-rad][x];
				}
				arr[r-rad][c-rad+1]=tmp;
			}
		}
	}
	static int getval(int[][] arr) {
		int res=Integer.MAX_VALUE;
		for(int y=0;y<n;y++) {
			int sum=0;
			for(int x=0;x<m;x++) {
				sum+=arr[y][x];
			}
			res=Math.min(sum,res);
		}
		return res;
	}
}