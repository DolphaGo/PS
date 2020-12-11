public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;

        while(headA != headB) {
            headA = headA==null ? headA = tempB : headA.next;
            headB = headB==null ? headB = tempA : headB.next;
        }
        return headA;
    }
}