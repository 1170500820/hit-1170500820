package dynamic.longestpalindromeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dynamic.longestpalindrome.Solution2;
import dynamic.longestpalindrome.Solution3;

class TestSolution2 {
  
  /**
   * Solution2 testing strategy
   *    string length: 0, 1, 2, 3, 4, 100
   *    palindrome length : 1, odd, even
   *    palindrome contains: 1, more, whole, ÖØµþ£¬
   */
  @Test
  public void testLength() {
    Solution2 solution2 = new Solution2();
    assertEquals("", "a", solution2.longestPalindrome("a"));
    assertEquals("", "", solution2.longestPalindrome(""));
    assertTrue(solution2.longestPalindrome("ab").equals("a") || solution2.longestPalindrome("ab").equals("b"));
    assertEquals("", "aa", solution2.longestPalindrome("aa"));
    assertEquals("", "aaa", solution2.longestPalindrome("aaa"));
    assertEquals("", "aba", solution2.longestPalindrome("aaba"));
    assertEquals("", "bab", solution2.longestPalindrome("acbab"));
    assertEquals("", "ababacababa", solution2.longestPalindrome("kababacababal"));
    assertEquals("", "abcbaabcba", solution2.longestPalindrome("abcbaabcba"));
    assertEquals("", "abccbccba", solution2.longestPalindrome("abccbccba"));
    assertEquals("", "acbdadbca", solution2.longestPalindrome("abcaaacbdadbca"));
    assertEquals("", "a", solution2.longestPalindrome("abcda"));
  }
  
  @Test
  public void test3() {
    Solution3 solution2 = new Solution3();
    assertEquals("", "a", solution2.longestPalindrome("a"));
    assertEquals("", "", solution2.longestPalindrome(""));
    assertTrue(solution2.longestPalindrome("ab").equals("a") || solution2.longestPalindrome("ab").equals("b"));
    assertEquals("", "aa", solution2.longestPalindrome("aa"));
    assertEquals("", "aaa", solution2.longestPalindrome("aaa"));
    assertEquals("", "aba", solution2.longestPalindrome("aaba"));
    assertEquals("", "bab", solution2.longestPalindrome("acbab"));
    assertEquals("", "ababacababa", solution2.longestPalindrome("kababacababal"));
    assertEquals("", "abcbaabcba", solution2.longestPalindrome("abcbaabcba"));
    assertEquals("", "abccbccba", solution2.longestPalindrome("abccbccba"));
    assertEquals("", "acbdadbca", solution2.longestPalindrome("abcaaacbdadbca"));
    assertEquals("", "a", solution2.longestPalindrome("abcda"));
  }

}
