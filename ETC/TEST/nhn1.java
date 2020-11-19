import java.util.Scanner;
import java.util.*;

class Main {
    private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame){
        char[] seat=new char[numOfAllPlayers-1];
        int size=numOfAllPlayers-1;
        int[] count=new int[26];
        for(int i=0;i<size;i++){
            seat[i]=(char)('B'+i);
        }
        char who='A';// A가 술래
        int idx=0; //0번째 위치
        count[who-'A']++;

        Map<Character,Boolean> quickPlayer=new HashMap<>();
        for(int i=0;i<numOfQuickPlayers;i++){
            quickPlayer.put(namesOfQuickPlayers[i],true);
        }

        for(int i=0;i<numOfGames;i++){
            int moveCommand=numOfMovesPerGame[i];
            int move=Math.abs(moveCommand)%size;

            if(moveCommand>=0) idx=(idx+move)%size; //시계 이동
            else idx=(idx-move+size)%size; //반시계 이동

            char target=seat[idx];
            if(quickPlayer.containsKey(target)){//다시 자기가 술래가 됨
                count[who-'A']++;
            }else{
                count[target-'A']++;
                seat[idx]=who;
                who=target;
            }
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<size;i++){
            char man=seat[i];
            sb.append(man+" "+count[man-'A']).append("\n");
        }
        sb.append(who+" "+count[who-'A']);
        System.out.println(sb);
    }

    private static class InputData {
        int numOfAllPlayers;
        int numOfQuickPlayers;
        char[] namesOfQuickPlayers;
        int numOfGames;
        int[] numOfMovesPerGame;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
            System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0, inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

            inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.numOfMovesPerGame = new int[inputData.numOfGames];
            String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
            for(int i = 0; i < inputData.numOfGames ; i++){
                inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers, inputData.numOfGames, inputData.numOfMovesPerGame);
    }
}