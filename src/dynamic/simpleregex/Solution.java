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
    
    //任何NFA都必须有一个初始状态
    delta.add(new HashMap<Character, HashSet<Integer>>());
    int currentState = 0;
    
    for(int i = 0;i < p.length();i++) {
      char readin = p.charAt(i);
      
      boolean star = false;
      boolean allMatch = false;      
      if(readin == '.') {
        allMatch = true;
      }
      if(i + 1 <p.length() && p.charAt(i + 1) == '*') { 
        star = true;
        i++;
      }
      
      if(star) {
        if(allMatch) {
          HashSet<Integer> thisStates = new HashSet<Integer>();
          thisStates.add(currentState);
          for(Character chars : sigma) {
            delta.get(currentState).put(chars, thisStates);
          }
          HashSet<Integer> nextStates = new HashSet<Integer>();
          nextStates.add(currentState + 1);
          delta.get(currentState).put('-', nextStates);
          delta.add(new HashMap<Character, HashSet<Integer>>());
          currentState++;
        } else {
          HashSet<Integer> thisStates = new HashSet<Integer>();
          thisStates.add(currentState);
          delta.get(currentState).put(readin, thisStates);
          HashSet<Integer> nextStates = new HashSet<Integer>();
          nextStates.add(currentState + 1);
          delta.get(currentState).put('-', nextStates);
          delta.add(new HashMap<Character, HashSet<Integer>>());
          currentState++;
        }
      } else {
        if(allMatch) {
          //.
          HashSet<Integer> nextStates = new HashSet<Integer>();
          nextStates.add(currentState + 1);
          for(Character chars : sigma) {
            delta.get(currentState).put(chars, nextStates);
          }
          delta.add(new HashMap<Character, HashSet<Integer>>());
          currentState++;
        } else {
          //a
          HashSet<Integer> nextStates = new HashSet<Integer>();
          nextStates.add(currentState + 1);
          delta.get(currentState).put(readin, nextStates);
          delta.add(new HashMap<Character, HashSet<Integer>>());
          currentState++;
        }
      }
    }
    if(p.isEmpty()) {
      endF.add(0);
      return;
    }
    endF.add(currentState);
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
      if(delta.get(currentState).containsKey('-')) {
        return scanText(s, index, currentState + 1);
      }
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
    //空转移
    if(delta.get(currentState).containsKey('-')) {
      flag = flag || scanText(s, index, currentState + 1);
    }
    return flag;
  }
}
