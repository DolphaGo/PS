import java.util.Scanner;
import java.io.*;
import java.util.Stack;

class Main {
    private static void solution(int day, int width, int[][] blocks) {
        int[] count=new int[width];
        int answer=0;
        Stack<Integer> trace=new Stack<>();
        for(int d=0;d<day;d++){
            int[] today=blocks[d];

            //블럭 쌓기
            for(int i=0;i<width;i++){
                count[i]+=today[i];
            }
            
            //시멘트 작업
            int pivot=count[0];
            for(int i=1;i<width;i++){
                if(pivot>count[i]){
                    trace.push(i);
                }else{
                    while(!trace.isEmpty()){
                        int p=trace.pop();
                        //시멘트 주입
                        answer+=pivot-count[p];
                        count[p]=pivot;
                    }
                    pivot=count[i];
                }
            }
            trace.clear();
            pivot=count[width-1];
            for(int i=width-2;i>=0;i--){
                if(pivot>count[i]){
                    trace.push(i);
                }else{
                    while(!trace.isEmpty()){
                        int p=trace.pop();
                        //시멘트 주입
                        answer+=pivot-count[p];
                        count[p]=pivot;
                    }
                    pivot=count[i];
                }
            }
            trace.clear();
        }
        System.out.println(answer);
    }

    private static class InputData {
        int day;
        int width;
        int[][] blocks;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.day = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.width = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.blocks = new int[inputData.day][inputData.width];
            for (int i = 0; i < inputData.day; i++) {
                String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
                for (int j = 0; j < inputData.width; j++) {
                    inputData.blocks[i][j] = Integer.parseInt(buf[j]);
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.day, inputData.width, inputData.blocks);
    }
}