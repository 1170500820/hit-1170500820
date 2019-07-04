package dynamic.simpleregex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
  
  private final Set<Character> sigma = new HashSet<Character>();
  
  private final List<Map<Character, HashSet<Integer>>> delta = new ArrayList<Map<Character,HashSet<Integer>>>();
  
  private final HashSet<Integer> endF = new HashSet<Integer>();
  
  /**
   * test if the String s matches the regular expression p.
   * @param s the match string
   * @param p the regular expression, every '*' must be after an alphabet or '.'
   * @return if s matches p
   */
  public boolean isMatch(String s, String p) {
    //initial scan for sigma
    for(int i = 0;i < s.length();i++) {
      sigma.add(s.charAt(i));
    }
    for(int i = 0;i < p.length();i++) {
      if(p.charAt(i) != '.' && p.charAt(i) != '*') {
        sigma.add(p.charAt(i));
      }
    }
    
    scanRegex(p);
    
    boolean success = scanText(s, 0, 0);
    sigma.clear();
    delta.clear();
    endF.clear();
    return success;
  }
  
  /**
   * generate the non-determined finite automata for regular expression p.
   * @param p the regular expression
   */
  private void scanRegex(String p) {
    int currentStatus = 0;
    int statusCount = 1;
    
    if(p.isEmpty()) {
      if(!sigma.isEmpty()) {
        delta.add(new HashMap<Character, HashSet<Integer>>());
        for(Character chas : sigma) {
          HashSet<Integer> sets = new HashSet<Integer>();
          sets.add(0);
          delta.get(0).put(chas, sets);
        }
        endF.add(2);
        return;
      } else {
        delta.add(new HashMap<Character, HashSet<Integer>>());
        endF.add(0);
      }
    }
    
    for(int i = 0;i < p.length();i++) {
      //set the type of expression
      boolean star = false;
      char character = p.charAt(i);
      boolean matchAll = character == '.';
      
      //check '*'
      if(i + 1 < p.length()) {
        if(p.charAt(i + 1) == '*') { 
          star = true;
          i++;
        }
      }    
      
      boolean end = false;
      if(i + 1 == p.length()) {
        end = true;
      }
      
      
      if(delta.size() < currentStatus + 1) {
        delta.add(new HashMap<Character, HashSet<Integer>>());
      }
      
      if(star) {
        if(matchAll) {
          //" .* "
          for(Character chars : sigma) {
            if(delta.get(currentStatus).containsKey(chars)) {
              delta.get(currentStatus).get(chars).add(currentStatus);
            } else {
              HashSet<Integer> integerset = new HashSet<Integer>();
              integerset.add(currentStatus);
              delta.get(currentStatus).put(chars, integerset);
            }
          }
          HashSet<Integer> integerset = new HashSet<Integer>();
          integerset.add(currentStatus + 1);
          delta.get(currentStatus).put('!', integerset);
          currentStatus++;
        } else {
          //" a* "
          if(delta.get(currentStatus).containsKey(character)) {
            delta.get(currentStatus).get(character).add(currentStatus);
          } else {
            HashSet<Integer> integerset = new HashSet<Integer>();
            integerset.add(currentStatus);
            delta.get(currentStatus).put(character, integerset);
          }
          HashSet<Integer> integerset = new HashSet<Integer>();
          integerset.add(currentStatus + 1);
          delta.get(currentStatus).put('!', integerset);
          currentStatus++;
        }
        if(end) {
          endF.add(currentStatus);
        }
      } else {
        if(matchAll) {
          // " . "
          for(Character chars : sigma) {
            if(delta.get(currentStatus).containsKey(chars)) {
              delta.get(currentStatus).get(chars).add(currentStatus + 1);
            } else {
              HashSet<Integer> integerset = new HashSet<Integer>();
              integerset.add(currentStatus + 1);
              delta.get(currentStatus).put(chars, integerset);
            }
          }
        } else {
          // " a "
          if(delta.get(currentStatus).containsKey(character)) {
            delta.get(currentStatus).get(character).add(currentStatus + 1);
          } else {
            HashSet<Integer> integerset = new HashSet<Integer>();
            integerset.add(currentStatus + 1);
            delta.get(currentStatus).put(character, integerset);
          }
        }
        currentStatus++;
        if(end) {
          endF.add(currentStatus);
        }
      }
    }
    if(sigma.isEmpty()) {
      endF.add(0);
    }
  }

  /**
   * scan one letter with '*' follows it (if has), and change status on the NFA.
   * @param s the string.
   * @param index the index to be checked in the string.
   * @param currentState of the NFA.
   * @return if this is part of right scanning path.
   */
  private boolean scanText(String s,int index, int currentState) {
    if(index >= s.length()) {
      if(endF.contains(currentState)) { 
        return true;
      } else {
        return false;
      }
    }
    char character = s.charAt(index);
    boolean flag = false;
    if(currentState > delta.size() - 1) {
      return false;
    }
    if(delta.get(currentState).containsKey(character)) {
      HashSet<Integer> status = delta.get(currentState).get(character);
      for(Integer ints : status) {
        flag = flag || scanText(s, index + 1, ints);
      }
    }
    if(delta.get(currentState).containsKey('!')) {
      HashSet<Integer> status = delta.get(currentState).get('!');
      for(Integer ints : status) {
        flag = flag || scanText(s, index + 1, ints);
      }
    }
    return false;
  }
}
