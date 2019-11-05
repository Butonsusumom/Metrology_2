package sample;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OperatorsMap  {

 public static   LinkedHashMap<String, String> Operators = new LinkedHashMap<String, String>();

   OperatorsMap () {

        Operators.put(">>>=", "\\>>>="); //added
        Operators.put(">>>", "\\>\\>\\>");
        //Equality and Relational Operators
        Operators.put("==", "==");
        Operators.put("!=", "!=");
        Operators.put(">=", "\\>=");
        Operators.put("<=", "\\<=");
        Operators.put(">", "\\>");
        Operators.put("<", "\\<");
        //Conditional Operators
        Operators.put("&&", "\\&\\&");
        Operators.put("||", "\\|\\|");
        //Operators.put("?:", "\\w\\s*\\?.+:");
        //Bitwise and Bit Shift Operators
        Operators.put(">>=", "\\>>="); //added
        Operators.put("<<=", "\\<<=");  //added
        Operators.put("~", "\\~");
        Operators.put(">>", "\\>\\>");  //added
        Operators.put("<<", "\\<\\<");
        Operators.put("|=", "\\|="); //added
        Operators.put("^=", "\\^="); //added
        Operators.put("&=", "\\&="); //added
        Operators.put("&", "\\&");
        Operators.put("^", "\\^");
        Operators.put("|", "\\|");
        // Simple Assignment Operator
        Operators.put("+=", "\\+=");
        Operators.put("-=", "-=");
        Operators.put("*=", "\\*=");
        Operators.put("/=", "\\/=");
        Operators.put("%=", "\\%=");
        Operators.put("=", "=");
        //Unary Operators
        Operators.put("postfix ++", "\\w\\s*\\+\\+");
        Operators.put("prefix ++", "\\+\\+\\w*\\w");
        Operators.put("postfix --", "\\w\\s*--");
        Operators.put("prefix --", "--\\w*\\w");
        Operators.put("!", "!");
        // Arithmetic Operators
        Operators.put("+", "[^\\+]\\+[^\\+]");
        Operators.put("-", "[^-]-[^-]");
        Operators.put("*", "\\*");
        Operators.put("/", "/");
        Operators.put("%", "\\%");
     //   Operators.put("()", "([+]|[-]|[0-9])\\s{0,}\\)");
     //   Operators.put("{}", "\\{");
     //   Operators.put(";",";");
     //   Operators.put(",",",");
     //   Operators.put(".","\\.{1}");


        // сохранение изменение знака прописать

    }

}
