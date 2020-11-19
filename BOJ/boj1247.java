import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        long maxPivot=9223372036854775807L;
        long minPivot=-9223372036854775807L;

        for(int tc=1;tc<=3;tc++){
            int n=Integer.parseInt(br.readLine());
            //오버플로우를 처리할 변수
            int maxCnt=0;
            int minCnt=0;
            long cur=0;
            for(int i=0;i<n;i++){
                long val=Long.parseLong(br.readLine());
                /**
                 * #1. 지금까지의 합 + 새로 들어온 값이 양수 범위를 초과할 때
                */
                if(val>0&&cur>maxPivot-val){
                    ++maxCnt;
                    cur-=maxPivot-val;
                }
                /**
                 * #2. 지금까지의 합 + 새로 들어온 값이 음수 범위를 초과할 때
                 */
                else if(val<0&&cur<minPivot-val){
                    ++minCnt;
                    cur-=minPivot-val;
                }
                /**
                 * #3. 그 외
                 */
                else{
                    cur+=val;
                    if(cur==maxPivot) {
                        ++maxCnt;
                        cur=0;
                    }else if(cur==minPivot){
                        ++minCnt;
                        cur=0;
                    }
                }
            }
            if(maxCnt==minCnt){
                if(cur==0) sb.append("0");
                else if(cur<0) sb.append("-");
                else sb.append("+");
            }else{
                if(maxCnt>minCnt) sb.append("+");
                else sb.append("-");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
