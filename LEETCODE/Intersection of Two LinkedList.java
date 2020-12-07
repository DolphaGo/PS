public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int acount=getCount(headA);
        int bcount=getCount(headB);
        int diff=Math.abs(acount-bcount);

        if(acount>bcount) headA=resetHead(headA,diff);
        else if(acount<bcount) headB=resetHead(headB,diff);

        while(headA!=null){
            if(headA==headB) return headA;
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }
    static int getCount(ListNode head){
        ListNode node=head;
        int count=0;
        while(node!=null){
            ++count;
            node=node.next;
        }
        return count;
    }

    static ListNode resetHead(ListNode head,int diff){
        for(int i=0;i<diff;i++){
            head=head.next;
        }
        return head;
    }
}
