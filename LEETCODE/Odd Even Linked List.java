class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null) return null;
        ListNode oddNode = head;
        ListNode evenNode = head.next;
        ListNode evenHead = evenNode;
        while (oddNode.next != null && evenNode.next != null) {
            oddNode.next = oddNode.next.next;
            evenNode.next = evenNode.next.next;
            oddNode = oddNode.next;
            evenNode = evenNode.next;
        }

        oddNode.next = evenHead;
        return head;
    }
}