import java.util.*;
import java.math.BigInteger;
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack=new Stack<>();
        BigInteger A=getData(l1);
        BigInteger B=getData(l2);
        BigInteger S=A.add(B);
        StringBuilder sb=new StringBuilder(S.toString());
        return getAnswer(sb.reverse().toString());
    }
    private static BigInteger getData(ListNode l){
        ListNode p=l;
        Stack<Integer> stack=new Stack<>();
        while(p!=null){
            stack.push(p.val);
            p=p.next;
        }
        StringBuilder sb=new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return new BigInteger(sb.toString());
    }
    private static ListNode getAnswer(String s){
        ListNode head=new ListNode(s.charAt(0)-'0',null);
        ListNode p=head;
        for(int i=1;i<s.length();i++){
            int val=s.charAt(i)-'0';
            ListNode node=new ListNode(val);
            p.next=node;
            p=node;
        }
        return head;
    }
}