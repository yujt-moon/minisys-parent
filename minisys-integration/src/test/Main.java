/**
 * @author yujiangtao
 * @date 2018/5/4 9:24
 */
public class Main {
    public static void main(String[] args) {
        /*ListNode head1 = new ListNode(2);
        ListNode node1 = new ListNode(4);
        head1.next = node1;
        node1.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        ListNode node2 = new ListNode(6);
        head2.next = node2;
        node2.next = new ListNode(4);
        new Solution().addTwoNumbers(head1, head2);*/

        String str = new Solution().addTwoStringLikeDigit("342", "465");
        System.out.println(str);;
    }


}
