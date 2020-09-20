import java.util.*;
public class Main3 {
    public static void main(String[] args){
        String[] info={"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query={"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};

        HashMap<String,Integer> conv=new HashMap<>();
        conv.put("-",0);
        conv.put("cpp",1); conv.put("java",2); conv.put("python",3);
        conv.put("backend",1); conv.put("frontend",2);
        conv.put("junior",1); conv.put("senior",2);
        conv.put("chicken",1); conv.put("pizza",2);

        List<Integer> list[][][][]=new ArrayList[4][3][3][3];
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        list[i][j][k][l]=new ArrayList<>();
                    }
                }
            }
        }

        StringTokenizer st;
        for(String s:info){
            st=new StringTokenizer(s);
            int i=conv.get(st.nextToken());
            int j=conv.get(st.nextToken());
            int k=conv.get(st.nextToken());
            int l=conv.get(st.nextToken());
            int score=Integer.parseInt(st.nextToken());
            for(int a=0;a<=i;a+=i){
                for(int b=0;b<=j;b+=j){
                    for(int c=0;c<=k;c+=k){
                        for(int d=0;d<=l;d+=l){
                            list[a][b][c][d].add(score);
                        }
                    }
                }
            }

        }

        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    for(int l=0;l<3;l++){
                        Collections.sort(list[i][j][k][l]);
                    }
                }
            }
        }

        int[] answer=new int[query.length];
        int idx=0;
        for(String q:query){
            st=new StringTokenizer(q);
            int a=conv.get(st.nextToken());
            st.nextToken(); //and
            int b=conv.get(st.nextToken());
            st.nextToken(); //and
            int c=conv.get(st.nextToken());
            st.nextToken(); //and
            int d=conv.get(st.nextToken());

            int x=Integer.parseInt(st.nextToken());
            int s=0;
            int e=list[a][b][c][d].size();
            //lowerbound
            while(s<e){
                int m=(s+e)>>1;
                if(list[a][b][c][d].get(m)<x) s=m+1;
                else e=m;
            }
            answer[idx++]=list[a][b][c][d].size()-e;
        }
        System.out.println(Arrays.toString(answer));
    }
}