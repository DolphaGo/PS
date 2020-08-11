import java.io.*;
public class Main {
    static int N;
    static int[] arr,tree;
    static int init(int node,int start,int end){
        if(start==end){
            return tree[node]=start;
        }
        int m=(start+end)>>1;
        int l=init(node*2+1,start,m);
        int r=init(node*2+2,m+1,end);
        return tree[node]=arr[l]<arr[r]?l:r;
    }

    //현재 노드 구간에서 최소 높이 인덱스를 반환하는 함수
    static int query(int node,int start,int end,int left,int right){
        if(end<left || start > right) return -1;

        if(left<=start && end<=right) return tree[node];

        int m=(start+end)>>1;
        int l=query(node*2+1,start,m,left,right);
        int r=query(node*2+2,m+1,end,left,right);

        if(l==-1) return r;
        else if(r==-1) return l;
        else return arr[l]<arr[r]?l:r;
    }
    static int getArea(int left, int right){

        int minIdx=query(0,0,N-1,left,right);
        int m=(left+right)>>1;

        int area=(right-left+1)*arr[minIdx];
        int val=0;
        if(left<minIdx){
            val=getArea(left,minIdx-1);
            area=area<val?val:area;
        }
        if(minIdx<right){
            val= getArea(minIdx+1,right);
            area=area<val?val:area;
        }
        return area;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        arr=new int[N];
        tree=new int[4*N];

        for(int i=0;i<N;i++){
            int val=Integer.parseInt(br.readLine());
            arr[i]=val;
        }

        init(0,0,N-1);
        System.out.println(getArea(0,N-1));
    }
}

