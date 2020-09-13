import java.util.*;

public class Main6 {
    public static void main(String[] args) {
        String[] companies={
                "A abc 2", "B abc 1"
        };
        String[] applicants={
                "a AB 1", "b AB 1", "c AB 1"
        };

        StringTokenizer st;

        int companiesCount=companies.length;

        ArrayList<Integer>[] companyList=new ArrayList[26];
        for(int i=0;i<26;i++) companyList[i]=new ArrayList<>();
        boolean[] printCompany=new boolean[26];

        int[] pickCount=new int[26];
        for(String company:companies){
            st=new StringTokenizer(company);

            int where=st.nextToken().charAt(0)-'A';
            printCompany[where]=true;

            String applicantPrefers=st.nextToken();
            for(int i=0;i<applicantPrefers.length();i++){
                int who=applicantPrefers.charAt(i)-'a';
                companyList[where].add(who);
            }

            pickCount[where]=Integer.parseInt(st.nextToken());
        }

        Queue<Integer>[] applicantQ=new Queue[26];
        int[] appCount=new int[26];
        for(int i=0;i<26;i++) applicantQ[i]=new LinkedList<>();

        for(String applicant:applicants){
            st=new StringTokenizer(applicant);
            int who=st.nextToken().charAt(0)-'a';

            String companyPrefers=st.nextToken();
            for(int i=0;i<companyPrefers.length();i++){
                int company=companyPrefers.charAt(i)-'A';
                applicantQ[who].add(company);
            }

            appCount[who]=Integer.parseInt(st.nextToken());
        }

        boolean[] rejected=new boolean[26];
        Arrays.fill(rejected,true);

        //정렬된 순서로 지원자 return
        TreeMap<Integer,Boolean> cur[]=new TreeMap[26];
        for(int i=0;i<26;i++) cur[i]=new TreeMap<>();

        PriorityQueue<int[]> picker=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);
            }
        });

        for(int round=1;round<=4;round++){
            //지원 로직
            for(int who=0;who<26;who++){
                if(rejected[who]&&appCount[who]>0){
                    appCount[who]--;
                    int appCom=applicantQ[who].poll();
                    cur[appCom].put(who,true);
                    rejected[who]=false;
                }
            }

            //잠정 선택 로직
            for(int where=0;where<26;where++){
                //지원자가 있는 상황에서
                if(cur[where].size()>0){
                    for(int who:cur[where].keySet()){
                        //지원자 상위 pickCount만 뽑고 나머지는 reject한다.
                        int idx=companyList[where].indexOf(who);
                        picker.add(new int[]{who,idx});
                    }

                    int count=pickCount[where];
                    while(count>0&&picker.size()>0){
                        picker.poll(); //잠정 선택
                        count--;
                    }

                    //선택받지 못한 지원자들 -> reject
                    while(!picker.isEmpty()){
                        int[] p=picker.poll();
                        int who=p[0];
                        //reject
                        rejected[who]=true;
                        cur[where].remove(who);
                    }
                }
            }
        }

        String[] answer=new String[companiesCount];
        StringBuilder sb=new StringBuilder();
        int idx=0;

        for(int i=0;i<26;i++){
            //출력 대상 회사에 대해
            if(printCompany[i]){
                sb.setLength(0);
                char where=(char)('A'+i);
                sb.append(where+"_");
                for(int who:cur[i].keySet()){
                    char whoChar=(char)(who+'a');
                    sb.append(whoChar);
                }
                answer[idx++]=sb.toString();
            }
        }

        System.out.println(Arrays.toString(answer));
    }
}