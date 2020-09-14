class Solution {
    static int n,m;
    public boolean solution(int[][] key, int[][] lock) {
        n=lock.length;
        m=key.length;
        int k=n+2*(m-1);
        int[][] map=new int[k][k];
        int zero=0;

        for(int y=m-1;y<m+n-1;y++){
            for(int x=m-1;x<m+n-1;x++){
                map[y][x]=lock[y-m+1][x-m+1];
                if(map[y][x]==0) ++zero;//빈 공간을 세줍니다.
            }
        }

        for(int iter=0;iter<4;iter++) {
            for (int y = 0; y < m+n-1; y++) {
                loop:for (int x = 0; x < m+n-1; x++) {
                    int cnt=0;
                    for(int yy=y;yy<y+m;yy++) {
                        for (int xx = x; xx < x + m; xx++) {
                            if(isRange(yy,xx)&&key[yy-y][xx-x]==1){
                                if(map[yy][xx]==1) continue loop; //겹치면 안 됨
                                else ++cnt;
                            }
                        }
                    }
                    if(cnt==zero) return true;
                }
            }
            key=rotate(key); //rotate 90
        }
        return false;
    }
    static int[][] rotate(int[][] key){
        int[][] newkey=new int[m][m];
        for(int y=0;y<m;y++){
            for(int x=0;x<m;x++){
                newkey[x][m-1-y]=key[y][x];
            }
        }
        return newkey;
    }
    static boolean isRange(int y,int x){
        return m-1<=y&&y<m+n-1&&m-1<=x&&x<m+n-1;
    }
}