package sample;

import java.util.LinkedHashMap;

public class NumbersMap {
    public static LinkedHashMap<String, String> Operands = new LinkedHashMap<String, String>();

    NumbersMap() {

        Operands.put("numbers","[^a-z][0-9]+([.]{0,}[0-9]|)");
        //   Operands.put("numbers","\\s{0,}[0-9]+\\s{0,}(+|-|*|/|%|&&|\\|\\||==|>=|<=|!=|<|>|>>|<<|>>>|\\||)|\\s{0,}(+|-|*|/|%|&&|\\|\\||==|>=|<=|!=|<|>|>>|<<|>>>|+=|-=|*=|/=|%=|>>=|>>>=|<<=|\\|=|^=|&=)\\s{0,}[0-9]+");
    }
}
