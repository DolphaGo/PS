import java.util.Scanner;
import java.util.*;

class Main {
    static boolean isNumber(char c){
        return '0'<=c&&c<='9';
    }
    static boolean isChar(char c){
        return c == 'R' || c == 'G' || c == 'B';
    }
    private static void solution(int numOfOrder, String[] orderArr) {
        StringBuilder answer=new StringBuilder();
        Stack<String> stack=new Stack<>();
        for(int oi=0;oi<numOfOrder;oi++){
            String order=orderArr[oi];
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<order.length();i++){
                char c=order.charAt(i);
                if(c!=')') stack.push(c+"");
                else{ //닫는 괄호라면
                    StringBuilder temp=new StringBuilder();
                    while(!stack.peek().equals("(")) temp.append(stack.pop());
                    stack.pop(); // ( 버림
                    //temp에는 괄호 뭉텅이(token)가 존재. 괄호 앞엔 반드시 숫자나 문자가 있다.
                    String token=temp.reverse().toString();
                    char prev=stack.pop().charAt(0);
                    StringBuilder temp2=new StringBuilder();
                    if(isNumber(prev)){ //숫자라면
                        int count=prev-'0';
                        while(count-- >0)temp2.append(token);

                        for(int j=0;j<temp2.length();j++){
                            char ch=temp2.charAt(j);
                            stack.push(ch+"");
                        }
                    }else{ //문자라면
                        for(int j=0;j<token.length();j++){
                            char ch=token.charAt(j);
                            temp2.append(prev);
                            temp2.append(ch);
                        }
                        for(int j=0;j<temp2.length();j++){
                            char ch=temp2.charAt(j);
                            stack.push(ch+"");
                        }
                    }
                }
            }
            while(!stack.isEmpty()){
                sb.append(stack.pop());
            }
            String reverse=sb.reverse().toString();
            sb.setLength(0);
            for(int i=0;i<reverse.length();i++){
                char c=reverse.charAt(i);
                if(isChar(c)) sb.append(c);
                else {
                    int count=c-'0';
                    char nc=reverse.charAt(++i); // 인덱스 증가
                    while(count-->0) sb.append(nc);
                }
            }
            answer.append(sb).append("\n");
        }
        System.out.print(answer);
    }

    private static class InputData {
        int numOfOrder;
        String[] orderArr;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.numOfOrder = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.orderArr = new String[inputData.numOfOrder];
            for (int i = 0; i < inputData.numOfOrder; i++) {
                inputData.orderArr[i] = scanner.nextLine().replaceAll("\\s+", "");
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.numOfOrder, inputData.orderArr);
    }
}