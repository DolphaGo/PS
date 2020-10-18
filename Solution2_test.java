class Solution2 {
    static int[] answer;
    public int[] solution(int[][] arr) {
        answer = new int[2];
        int n=arr.length;

        divide(arr,0,0,n,n);

        return answer;

    }
    static void divide(int[][] arr, int sy, int sx, int ey, int ex){
        int res=check(arr,sy,sx,ey,ex);
        if(res!=-1) answer[res]++;
        else{
            int my=(sy+ey)>>1;
            int mx=(sx+ex)>>1;
            divide(arr,sy,sx,my,mx);
            divide(arr,sy,mx,my,ex);
            divide(arr,my,sx,ey,mx);
            divide(arr,my,mx,ey,ex);
        }
    }

    static int check(int[][] arr,int sy,int sx,int ey,int ex){
        int pivot=arr[sy][sx];
        for(int yy=sy;yy<ey;yy++){
            for(int xx=sx;xx<ex;xx++){
                if(arr[yy][xx]!=pivot) return -1;
            }
        }
        return pivot;
    }
}