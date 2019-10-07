/**
 * @author yujiangtao
 * @date 2018/5/4 9:22
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String total = addTwoStringLikeDigit(getDigit(l1), getDigit(l2));
        return constructNodeList(total);
    }

    public String addTwoStringLikeDigit(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        String result = "";
        // 进位标识符
        boolean carryFlag = false;
        for(int i = 0; i < Math.min(len1, len2); i++) {
            // 取出一位数据
            int currDigit1 = str1.charAt(len1 - 1 - i) - '0';
            int currDigit2 = str2.charAt(len2 - 1 - i) - '0';
            int total = currDigit1 + currDigit2;
            // 判断后面是否进位，如果进位加上进位的1
            if(carryFlag) {
                total++;
            }
            if(total > 9) {
                carryFlag = true;
                result = String.valueOf(total).substring(1) + result;
            } else {
                carryFlag = false;
                result = total + result;
            }
        }
        if(carryFlag && len1 == len2) {
            result = "1" + result;
        } else if(!carryFlag) {
            if(len1 > len2) {
                result = str1.substring(0, len1 - len2) + result;
            } else {
                result = str2.substring(0, len2 - len1) + result;
            }
        } else if(carryFlag) {
            if(len1 > len2) {
                result = addTwoStringLikeDigit(str1.substring(0, len1 - len2), "1") + result;
            } else {
                result = addTwoStringLikeDigit(str2.substring(0, len2 - len1), "1") + result;
            }
        }
        return result;
    }

    private String getDigit(ListNode node) {
        String temp = "";
        while(node != null) {
            int value = node.val;
            temp = value + temp;
            node = node.next;
        }
        if(!"".equals(temp)) {
            return temp;
        } else {
            return "0";
        }
    }

    private ListNode constructNodeList(String digit) {
        char[] arr = digit.toCharArray();
        ListNode head = null;
        ListNode node = null;
        for(int i = arr.length-1; i >= 0; i--) {
            if(i == arr.length-1) {
                head = new ListNode(Integer.valueOf(arr[i] + ""));
            } else if(i == arr.length-2) {
                node = new ListNode(Integer.valueOf(arr[i] + ""));
                head.next = node;
            } else {
                ListNode temp = new ListNode(Integer.valueOf(arr[i] + ""));
                node.next = temp;
                node = temp;

            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
