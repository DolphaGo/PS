import java.io.*;
import java.util.*;
public class Main{
    static int last_ans, n;
    static int[] arr;
    static int[][] tree;
    static void mergeSortTree(int node,int start,int end){
        int m=(start+end)>>1;
        int s=start;
        int e=m+1;
        tree[node]=new int[end-start+1];
        int idx=0;
        while(s<=m&&e<=end){
            if(arr[s]<arr[e]) tree[node][idx++]=arr[s++];
            else tree[node][idx++]=arr[e++];
        }
        if(s>m) while(e<=end) tree[node][idx++]=arr[e++];
        if(e>end) while(s<=m) tree[node][idx++]=arr[s++];

        for(int i=start;i<=end;i++){
            arr[i]=tree[node][i-start];
        }
    }
    static void merge(int node,int start,int end){
        if(start!=end){
            int m=(start+end)>>1;
            merge(node*2,start,m);
            merge(node*2+1,m+1,end);
            mergeSortTree(node,start,end);
        }else tree[node]=new int[]{arr[start]};
    }

    static int upperBound(int node,int k){
        int s=0;
        int e=tree[node].length;
        while(s<e){
            int m=(s+e)>>1;
            if(tree[node][m]<=k) s=m+1;
            else e=m;
        }
        return e;
    }

    static int query(int node,int start,int end,int left,int right,int k){
        if(start>right || end < left) return 0;

        if(left<=start && end<=right){
            int idx=upperBound(node,k);
            return tree[node].length-idx;
        }
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right,k)+query(node*2+1, m+1,end,left,right,k);
    }

    public static void main(String[] args) throws Exception {
        FastIO io = new FastIO();
        n=io.nextInt();
        arr=new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i]=io.nextInt();
        }
        int h=(int)Math.ceil(Math.log(n)/Math.log(2))+1;
        tree=new int[1<<h][];
        merge(1,1,n);

        StringBuilder sb=new StringBuilder();
        int m=io.nextInt();
        last_ans=0;
        for(int i=0;i<m;i++){
            int a=io.nextInt() ^last_ans;
            int b=io.nextInt() ^last_ans;
            int c=io.nextInt() ^last_ans;
            sb.append(last_ans=query(1,1,n,a,b,c)).append("\n");
        }
        System.out.print(sb);
    }

    private static class FastIO {
        private static final int EOF = -1;

        private static final byte ASCII_n = 10;
        private static final byte ASCII_space = 32;
        private static final byte ASCII_minus = 45;
        private static final byte ASCII_0 = 48;
        private static final byte ASCII_9 = 57;

        private final DataInputStream din;
        private final DataOutputStream dout;

        private byte[] inbuffer;
        private int inbufferpointer, bytesread;
        private byte[] outbuffer;
        private int outbufferpointer;

        private byte[] bytebuffer;

        private FastIO() {
            this.din = new DataInputStream(System.in);
            this.dout = new DataOutputStream(System.out);

            this.inbuffer = new byte[65536];
            this.inbufferpointer = 0;
            this.bytesread = 0;
            this.outbuffer = new byte[65536];
            this.outbufferpointer = 0;

            this.bytebuffer = new byte[20];
        }

        private byte read() {
            if (inbufferpointer == bytesread)
                fillbuffer();
            return bytesread == EOF ? EOF : inbuffer[inbufferpointer++];
        }

        private void fillbuffer() {
            try {
                bytesread = din.read(inbuffer, inbufferpointer = 0, inbuffer.length);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void write(byte b) {
            if (outbufferpointer == outbuffer.length)
                flushbuffer();
            outbuffer[outbufferpointer++] = b;
        }

        private void flushbuffer() {
            if (outbufferpointer != 0) {
                try {
                    dout.write(outbuffer, 0, outbufferpointer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                outbufferpointer = 0;
            }
        }

        private int nextInt() {
            byte b;
            while (isSpace(b = read()))
                ;
            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = read();
            }
            int result = b - '0';
            while (isDigit(b = read()))
                result = result * 10 + b - '0';
            return negative ? -result : result;
        }

        private void println(int i) {
            if (i == 0) {
                write(ASCII_0);
            } else {
                if (i < 0) {
                    write(ASCII_minus);
                    i = -i;
                }
                int index = 0;
                while (i > 0) {
                    bytebuffer[index++] = (byte) ((i % 10) + ASCII_0);
                    i /= 10;
                }
                while (index-- > 0) {
                    write(bytebuffer[index]);
                }
            }
            write(ASCII_n);
        }

        private boolean isSpace(byte b) {
            return b <= ASCII_space && b >= 0;
        }

        private boolean isDigit(byte b) {
            return b >= ASCII_0 && b <= ASCII_9;
        }
    }
}