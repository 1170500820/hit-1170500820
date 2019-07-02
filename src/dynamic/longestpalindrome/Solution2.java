package dynamic.longestpalindrome;

import java.util.LinkedList;
import java.util.List;

public class Solution2 {

  /**
   * input a String of size <= 1000, return the longest palindrome substring
   * 
   * @param s the input string, requires length <= 1000
   * @return the longest palindrome in s
   */
  public String longestPalindrome(String s) {
    if(s.length() <= 1) {
      return s;
    }
    if(s.length() == 2) {
      if(s.charAt(0) == s.charAt(1)) {
        return s;
      } else {
        return s.substring(0, 1);
      }
    }
    if(s.length() == 3) {
      if(s.charAt(0) == s.charAt(2)) {
        return s;
      } else {
        if(s.charAt(0) == s.charAt(1)) {
          return s.substring(0, 2);
        } else if(s.charAt(1) == s.charAt(2)) {
          return s.substring(1, 3);
        } else {
          return s.substring(0, 1);
        }
      }
    }
    
    int longestStart = 0;
    int longestEnd = 0;
    
    final int length = s.length();
    boolean[][] list = new boolean[length][length];

    //单个字母自成一个回文序列
    for(int i = 0;i < length;i++) {
      list[i][i] = true;
    }
    
    //双字回文序列
    for(int i = 0;i < length - 1;i++) {
      list[i][i + 1] = s.charAt(i) == s.charAt(i + 1); 
      if(list[i][i + 1] == true) {
        longestStart = i;
        longestEnd = i + 1;
      }
    }
    
    //三字回文序列
    for(int i = 0;i < length - 2;i++) {
      list[i][i + 2] = s.charAt(i) == s.charAt(i + 2);
      if(list[i][i + 2] == true) {
        longestStart = i;
        longestEnd = i + 2;
      }
    }
    
    //其余长度迭代判断
    for(int len = 4;len <= length;len++) {
      for(int i = 0;i < length - len + 1;i++) {
        list[i][i + len - 1] = list[i + 1][i + len - 2] && s.charAt(i) == s.charAt(i + len - 1);
        if(list[i][i + len - 1] == true) {
          longestStart = i;
          longestEnd = i + len - 1;
        }
      }
    }
    return s.substring(longestStart, longestEnd + 1);
  }
}
