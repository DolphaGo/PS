public class Solution {
    static final int PASS=1000001;
    public boolean hasCycle(ListNode head) {
        while(head!=null){
            if(head.val==PASS) return true;
            head.val=PASS;
            head=head.next;
        }
        return false;
    }
}