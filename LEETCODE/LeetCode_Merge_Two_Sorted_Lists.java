import java.util.List;

class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode();
        ListNode node=head.next;
        merge(l1,l2,node);
        return head.next;
    }
    static void splice(ListNode l,ListNode node){
        if(l==null) return;
        if(node==null) node=new ListNode();
        node.val=l.val;
        splice(l.next,node.next);
    }
    static void merge(ListNode l1,ListNode l2, ListNode node){
        if(l1==null || l2==null){
            if(l1==null) splice(l2,node);
            if(l2==null) splice(l1,node);
            return;
        }
        if(node==null) node=new ListNode();
        if(l1.val>l2.val){
            node.val=l2.val;
            merge(l1,l2.next,node.next);
        }else{
            node.val=l1.val;
            merge(l1.next,l2,node.next);
        }
    }
}