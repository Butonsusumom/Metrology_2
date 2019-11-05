package sample;

import java.util.LinkedHashMap;

public class Loops {
  public static  LinkedHashMap<String, String> Operators = new LinkedHashMap<String, String>();

  public Loops() {
      Operators.put("foreach","\\s{0,1}foreach(\\s{0,}|\\s{0,}\\n\\s{0,}|\\()");
      Operators.put("for","\\s{0,1}for(\\s{0,}|\\s{0,}\\n\\s{0,}|\\()");
      Operators.put("do while","\\s{0,1}do(\\s{0,}|\\s{0,}\\n\\s{0,})\\{.+\\}(\\s{0,}|\\s{0,}\\n\\s{0,})while\\s{1,}");
      Operators.put("while","\\s{0,1}while(\\s{0,}|\\s{0,}\\n\\s{0,})\\(.+\\)(\\s{0,}|\\s{0,}\\n\\s{0,})(\\{.+\\}|.+)");
      Operators.put("if else","\\selse");
     // Operators.put("if else","\\s{0,1}if\\s{0,}\\(.+\\)(\\s{0,}|\\s{0,}\\n\\s{0,})(\\{.+\\}|.+)(\\s{0,}|\\s{0,}\\n\\s{0,})else(\\s{0,}|\\s{0,}\\n\\s{0,})(\\{.+\\}|.+)");
     //Operators.put("if","\\s{0,1}if(\\s{0,}|\\n)\\(.+\\)(\\s{0,}|\\s{0,}\\n\\s{0,})(\\{.+\\}|[^\\{].+)(\\s{0,}|\\s{0,}\\n\\s{0,})");
      Operators.put("if ","\\sif*\\s\\(");
      Operators.put("switch","\\s{0,1}switch\\s{0,1}");
      Operators.put("break","\\s{0,1}break\\s*;");
      Operators.put("continue","\\s{0,1}continue\\s*;");
      Operators.put("?:","\\s{0,1}[?].+[:]"); //added
    }

}
