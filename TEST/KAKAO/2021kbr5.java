import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        String play_time="99:59:59";
        String adv_time="25:00:00";
        String[] logs={
                "69:59:59-89:59:59",
                "01:00:00-21:00:00",
                "79:59:59-99:59:59",
                "11:00:00-31:00:00"
        };

        StringTokenizer st;
        st=new StringTokenizer(play_time,":");
        int playhh=Integer.parseInt(st.nextToken());
        int playmm=Integer.parseInt(st.nextToken());
        int playss=Integer.parseInt(st.nextToken());

        int playTime=(playhh*3600)+(playmm*60)+playss;

        int n=playTime;
        int[] time=new int[n+1];

        for(int i=0;i<logs.length;i++){
            String log=logs[i];
            st=new StringTokenizer(log,"-");
            String start=st.nextToken();
            String end=st.nextToken();

            st=new StringTokenizer(start,":");
            int sthh=Integer.parseInt(st.nextToken());
            int stmm=Integer.parseInt(st.nextToken());
            int stss=Integer.parseInt(st.nextToken());

            int startTime=(sthh*3600)+(stmm*60)+stss;

            st=new StringTokenizer(end,":");
            int enhh=Integer.parseInt(st.nextToken());
            int enmm=Integer.parseInt(st.nextToken());
            int enss=Integer.parseInt(st.nextToken());

            int endTime=(enhh*3600)+(enmm*60)+enss;

            for(int t=startTime;t<endTime;t++){
                time[t]++;
            }
        }

        st=new StringTokenizer(adv_time,":");
        int adhh=Integer.parseInt(st.nextToken());
        int admm=Integer.parseInt(st.nextToken());
        int adss=Integer.parseInt(st.nextToken());

        int advTime=(adhh*3600)+(admm*60)+adss;

        long res=0;
        int answer=0;
        for(int i=0;i<advTime;i++) res+=time[i];
        long max=res;

        for(int i=0;i<=n-advTime;i++){
            long prev=time[i];
            long next=time[i+advTime];
            long diff=next-prev;
            res+=diff;

            if(max<res){
                max=res;
                answer=i+1;
            }
        }

        int anshh=answer/3600;
        answer%=3600;
        int ansmm=answer/60;
        int ansss=answer%60;

        String hh=String.valueOf(anshh);
        String mm=String.valueOf(ansmm);
        String ss=String.valueOf(ansss);
        if(hh.length()==1) hh="0"+hh;
        if(mm.length()==1) mm="0"+mm;
        if(ss.length()==1) ss="0"+ss;

        String ans=hh+":"+mm+":"+ss;
        System.out.println(ans);
    }
}