package dynamic.longestpalindrome;

import java.util.LinkedList;
import java.util.List;

public class Solution {

  class Tuple {
    public final int longestPalindromeLength;
    public final int longestPalindromeEnd;
    public final int longestEvenSubPalindrome;
    public final int longestOddSubPalindrome;

    public Tuple(int longestPalindromeLength, int longestPalindromeEnd,
        int longestEvenSubPalindrome, int longestOddSubPalindrome) {
      this.longestPalindromeLength = longestPalindromeLength;
      this.longestPalindromeEnd = longestPalindromeEnd;
      this.longestEvenSubPalindrome = longestEvenSubPalindrome;
      this.longestOddSubPalindrome = longestOddSubPalindrome;
    }

    public String toString() {
      return "[" + this.longestPalindromeLength + "," + this.longestPalindromeEnd + ","
          + this.longestEvenSubPalindrome + "," + this.longestOddSubPalindrome + "]";
    }
  }

  /**
   * input a String of size <= 1000, return the longest palindrome substring
   * 
   * @param s the input string, requires length <= 1000
   * @return the longest palindrome in s
   */
  public String longestPalindrome(String s) {
    // ±ß½çÌõ¼þÅÐ¶Ï
    if (s.length() <= 1) {
      return s;
    } else if (s.length() == 2) {
      if (s.charAt(0) == s.charAt(1)) {
        return s;
      } else {
        return s.substring(0, 1);
      }
    }

    List<Tuple> tuples = new LinkedList<Solution.Tuple>();
    // init
    tuples.add(new Tuple(1, 0, 0, 1));
    if (s.charAt(1) == s.charAt(0)) {
      tuples.add(new Tuple(2, 1, 2, 1));
    } else {
      tuples.add(new Tuple(1, 0, 0, 1));
    }
    for (int i = 2; i < s.length(); i++) {
      Tuple lastTuple = tuples.get(i - 1);
      int evenLength = 0;
      int oddLength = 1;
      if (s.charAt(i) == s.charAt(i - lastTuple.longestEvenSubPalindrome - 1)) {
        evenLength = lastTuple.longestEvenSubPalindrome + 2;
      } else {
        evenLength = 0;
      }
      if (s.charAt(i) == s.charAt(i - lastTuple.longestOddSubPalindrome - 1)) {
        oddLength = lastTuple.longestOddSubPalindrome + 2;
      } else {
        oddLength = 1;
      }

      int max = 1;
      if (evenLength > oddLength) {
        max = evenLength;
      } else {
        max = oddLength;
      }

      if (max > lastTuple.longestPalindromeLength) {
        tuples.add(new Tuple(max, i, evenLength, oddLength));
      } else {
        tuples.add(new Tuple(lastTuple.longestPalindromeLength, lastTuple.longestPalindromeEnd,
            evenLength, oddLength));
      }
    }
    Tuple resultTuple = tuples.get(tuples.size() - 1);
    return s.substring(resultTuple.longestPalindromeEnd - resultTuple.longestPalindromeLength + 1,
        resultTuple.longestPalindromeEnd + 1);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String q1 = "a";
    String q2 = "ab";
    String q3 = "aa";
    String q4 = "afdsaf";
    String q5 = "acdbaabcs";
    String q6 = "babad";
    String a1 = solution.longestPalindrome(q1);
    String a2 = solution.longestPalindrome(q2);
    String a3 = solution.longestPalindrome(q3);
    String a4 = solution.longestPalindrome(q4);
    String a5 = solution.longestPalindrome(q5);
    String a6 = solution.longestPalindrome(q6);
  }
}
