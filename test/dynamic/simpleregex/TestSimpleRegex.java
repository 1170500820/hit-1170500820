package dynamic.simpleregex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSimpleRegex {

  /**
   * testing strategy:
   *    regex:
   *        !!!empty!!!
   *        Alphabet only
   *            longer
   *            shorter
   *        with 1-4 '*'
   *        with 1-4 '.'
   *        with 1-2 '.*'
   *        combined with '.', '*', '.*'
   *        '.' appears at the start/end/middle
   *        '*' appears at the start/end/middle
   *    String:
   *        match
   *        do not match
   */
  @Test
  public void testAlphabetOnly() {
    Solution solution = new Solution();
    assertTrue(solution.isMatch("abc", "abc"));
    assertFalse(solution.isMatch("ab", "abc"));
    assertFalse(solution.isMatch("abcd", "ab"));
    
    assertTrue(solution.isMatch("abcdefg", "abcdefg"));
    assertFalse(solution.isMatch("abcdefg", "abcdefgh"));
    assertFalse(solution.isMatch("abcdefg", "abcdef"));
  }

  /**
   * testing strategy
   *    a*
   *    a*b
   *    a*bcde
   *    ab*
   *    abcd*
   *    abc*de    
   */
  @Test
  public void testStar() {
    Solution solution = new Solution();
    assertTrue(solution.isMatch("", "a*"));
    assertTrue(solution.isMatch("a", "a*"));
    assertTrue(solution.isMatch("aa", "a*"));
    assertTrue(solution.isMatch("aaaaaaa", "a*"));
    assertFalse(solution.isMatch("b", "a*"));
    assertFalse(solution.isMatch("ab", "a*"));
    assertFalse(solution.isMatch("ba", "a*"));
    assertFalse(solution.isMatch("aaaaab", "a*"));
    
    assertTrue(solution.isMatch("b", "a*b"));
    assertTrue(solution.isMatch("ab", "a*b"));
    assertTrue(solution.isMatch("aaaab", "a*b"));
    assertFalse(solution.isMatch("ba", "a*b"));
    assertFalse(solution.isMatch("aba", "a*b"));
    
    assertTrue(solution.isMatch("bcde", "a*bcde"));
    assertTrue(solution.isMatch("abcde", "a*bcde"));
    assertTrue(solution.isMatch("aaaaabcde", "a*bcde"));
    assertFalse(solution.isMatch("aaaa", "a*bcde"));
    
    assertTrue(solution.isMatch("a", "ab*"));
    assertFalse(solution.isMatch("abbbba", "ab*"));
    
    assertTrue(solution.isMatch("abcdddddd", "abcd*"));
    
    assertTrue(solution.isMatch("abde", "abc*de"));
    assertTrue(solution.isMatch("abccccccde", "abc*de"));
    
    //好吧，我真的错了，还必须加上空转移，杀了我吧
    assertFalse(solution.isMatch("aaba", "ab*a*c*a"));
  }
  
  /**
   * testing strategy
   *    a.
   *    .a
   *    a.a
   *    a.a.a
   */
  @Test
  public void testDot() {
    Solution solution = new Solution();
    assertTrue(solution.isMatch("ae", "a."));
    assertTrue(solution.isMatch("ac", "a."));
    assertFalse(solution.isMatch("a", "a."));
    assertFalse(solution.isMatch("abc", "a.")); 
    
    assertTrue(solution.isMatch("ea", ".a"));
    assertTrue(solution.isMatch("ba", ".a"));
    assertFalse(solution.isMatch("a", ".a"));
    assertFalse(solution.isMatch("aaa", ".a"));
    
    assertTrue(solution.isMatch("aka", "a.a"));
    assertFalse(solution.isMatch("aa", "a.a"));
    
    
    assertTrue(solution.isMatch("akaga", "a.a.a"));
    assertFalse(solution.isMatch("aaa", "a.a.a"));
    assertFalse(solution.isMatch("aaaaaa", "a.a.a"));
  }
  
  @Test
  public void testCombine() {
    Solution solution = new Solution();
    assertTrue(solution.isMatch("sdafads", ".*"));
    assertTrue(solution.isMatch("", ".*"));
    
    assertTrue(solution.isMatch("abcdef", "abc.*"));
    assertTrue(solution.isMatch("abc", "abc.*"));
    assertFalse(solution.isMatch("abefd", "abc.*"));
    
    assertTrue(solution.isMatch("bcd", ".*bcd"));
    assertTrue(solution.isMatch("bcdbcd", ".*bcd"));
    assertTrue(solution.isMatch("bbbbcdbcd", ".*bcd"));
    assertFalse(solution.isMatch("bcdbcde", ".*bcd")); 
    
    assertTrue(solution.isMatch("abcd", "ab.*cd"));
    assertTrue(solution.isMatch("abcdcdcd", "ab.*cd"));
    assertTrue(solution.isMatch("abgcd", "ab.*cd"));
    assertFalse(solution.isMatch("abcde", "ab.*cd"));
    
    assertFalse(solution.isMatch("ab", ".*c"));
  }
  
  @Test
  public void testEmptyRegex() {
    Solution solution = new Solution();
    assertFalse(solution.isMatch("abc", ""));
    assertTrue(solution.isMatch("", ""));
  }
  
  @Test
  public void testLeetCode() {
    Solution solution = new Solution();
    assertFalse(solution.isMatch("aa", "a"));
    assertFalse(solution.isMatch("a", "aa"));
    
    assertTrue(solution.isMatch("aab", "c*a*b"));
    
    assertFalse(solution.isMatch("mississippi", "mis*is*p*."));
  }
  
  @Test
  public void testMoreCombine() {
    Solution solution = new Solution();
    
    assertTrue(solution.isMatch("", "a*b*c*d*a*b*c*d*"));
    assertTrue(solution.isMatch("abcbd", "a*b*c*d*a*b*c*d*"));
    assertTrue(solution.isMatch("aadad", "a*b*c*d*a*b*c*d*")); 
    assertFalse(solution.isMatch("ababa", "a*b*c*d*a*b*c*d*")); 
    
    assertTrue(solution.isMatch("abc", ".*abc.*"));
    assertTrue(solution.isMatch("abcacd", ".*abc.*"));
    assertTrue(solution.isMatch("afdsaabc", ".*abc.*"));
    assertTrue(solution.isMatch("asfdaabcads", ".*abc.*"));
    
    assertTrue(solution.isMatch("caabc", ".a*b."));
    assertTrue(solution.isMatch("cbb", ".a*b."));
    assertTrue(solution.isMatch("bbb", ".a*b."));
    
    assertTrue(solution.isMatch("sfdsa", "....."));
    assertTrue(solution.isMatch("aaaaa", "....."));
    assertTrue(solution.isMatch("abcde", "....."));
    
    assertTrue(solution.isMatch("a", "..*"));
    assertTrue(solution.isMatch("abc", "..*"));
    assertTrue(solution.isMatch("aaaaaa", "..*"));
  }

}
