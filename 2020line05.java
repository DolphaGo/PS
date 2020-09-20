import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        int[] cards={12, 7, 11, 6, 2, 12};

        int answer=0;

        ArrayList<Integer> player=new ArrayList<>();
        ArrayList<Integer> dealer=new ArrayList<>();

        int canNotLook=0;
        int canLook=0;

        int card=0;
        int i=0;
        while(i<cards.length){
            //게임 시작 초기화
            player.clear();
            dealer.clear();

            //1
            if(i==cards.length) break;
            card=cards[i++];
            if(card>10) card=10;
            player.add(card);

            //2
            if(i==cards.length) break;
            canNotLook=cards[i++];
            if(canNotLook>=10) canNotLook=10;
            dealer.add(canNotLook);

            //3
            if(i==cards.length) break;
            card=cards[i++];
            if(card>10) card=10;
            player.add(card);

            //4
            if(i==cards.length) break;
            canLook=cards[i++];
            if(canLook>10) canLook=10;
            dealer.add(canLook);

            //처음 2개의 카드를 받고 가능성 검사
            HashMap<Integer,Boolean> playerScoreMap= getScoreMap(player);
            HashMap<Integer,Boolean> dealerScoreMap= getScoreMap(dealer);

            //만약 플레이어가 블랙잭이라면
            if(playerScoreMap.get(21)!=null){
                //딜러도 블랙잭이라면
                if(dealerScoreMap.get(21)!=null){
                    //비긴 경우
                }else{
                    answer+=3;
                }
            }

            //5 - 전략
            if(canLook==1||canLook==7){
                while(!checkScore(player,17)) {
                    if(i==cards.length) break;
                    card=cards[i++];
                    if(card>10) card=10;
                    player.add(card);
                }
            }else if(canLook==4||canLook==5||canLook==6){
                //카드를 받지 않습니다.
            }else if(canLook==2||canLook==3){
                while(!checkScore(player,12)) {
                    if(i==cards.length) break;
                    card=cards[i++];
                    if(card>10) card=10;
                    player.add(card);
                }
            }

            // 5-3
            if(checkScore(player,22)){
                answer-=2;
                continue; //다음 게임으로
            }

            //6
            while(!checkScore(dealer,17)) {
                if(i==cards.length) break;
                card=cards[i++];
                if(card>10) card=10;
                dealer.add(card);
            }

            //6-1
            if(checkScore(dealer,22)){
                answer+=2; //플레이어가 이김
                continue; //다음 게임으로
            }

            //7
            int playerGreedyScore=getGreedyScore(player);
            int dealerGreedyScore=getGreedyScore(dealer);
            int result=fight(playerGreedyScore,dealerGreedyScore);
            if(result>0) answer+=2;
            else if(result<0) answer-=2;

        }

    }
    static int fight(int player,int dealer){
        int p=Math.abs(21-player);
        int d=Math.abs(21-dealer);
        if(p<d) return 1; // 플레이어 승
        else if(p>d) return -1; //딜러 승
        else return 0; //무승부
    }

    //2개밖에 없음.
    static HashMap<Integer,Boolean> getScoreMap(ArrayList<Integer> list){
        int first=list.get(0);
        int second=list.get(1);

        HashMap<Integer,Boolean> scoreList=new HashMap<>();
        if(first==1) {
            scoreList.put(first+second,true);
            scoreList.put(10+second,true);
        }

        if(second==1){
            scoreList.put(first+second,true);
            scoreList.put(first+10,true);
        }

        return scoreList;
    }

    //true : max 이상. false:: max 미만
    static boolean checkScore(ArrayList<Integer> list, int max){
        HashMap<Integer,Boolean> scoreMap=getScoreMap(list);
        for(int score:scoreMap.keySet()){
            if(score>=max) return true;
        }
        return false;
    }

    //21점에 가장 가까운 점수를 반환한다.
    static int getGreedyScore(ArrayList<Integer> list){
        HashMap<Integer,Boolean> scoreMap=getScoreMap(list);
        int min=Integer.MAX_VALUE;
        int greedyScore=0;
        for(int score:scoreMap.keySet()){
            int diff=Math.abs(21-score);
            if(diff<min){
                min=diff;
                greedyScore=score;
            }
        }
        return greedyScore;
    }
}