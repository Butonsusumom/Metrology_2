package sample;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Search {

    String code;

    Search() {
        code = "";
    }


    Search(String code) {
        this.code = code;

    }

    LinkedHashMap<String, Integer> search_stroki(String regex, LinkedHashMap<String, Integer> Map) {

        Pattern Search_pattern, Search_pattern1;
        Matcher Search_matcher, Search_matcher1;
        String element_value;
        int coincidences_number;

        try {

        do {
            Search_pattern = Pattern.compile(regex);
            Search_matcher = Search_pattern.matcher(code);


            while (Search_matcher.find()) {
                element_value = code.substring(Search_matcher.start(), Search_matcher.end());
                coincidences_number = 0;

                while (code.contains(element_value)) {
                    ++coincidences_number;
                    code = code.substring(0, code.indexOf(element_value)) +
                            code.substring(code.indexOf(element_value) + element_value.length(),
                                    code.length());

                }

                Search_pattern = Pattern.compile(regex);
                Search_matcher = Search_pattern.matcher(code);

                if (coincidences_number != 0) Map.put(element_value, coincidences_number);


            }

        } while (Search_matcher.find());

    } catch (Exception e) {
            return Map;
    }


        return Map;
    }

    LinkedHashMap<String, Integer> search_cifr(String regex, LinkedHashMap<String, Integer> Map) {

        Pattern Search_pattern, Search_pattern1;
        Matcher Search_matcher, Search_matcher1;
        String element_value;
        int coincidences_number;

        try {

        do {
            Search_pattern = Pattern.compile(regex);
            Search_matcher = Search_pattern.matcher(code);


            while (Search_matcher.find()) {
                element_value = code.substring(Search_matcher.start(), Search_matcher.end());
                element_value = element_value.substring(1, element_value.length() - 1);
                coincidences_number = 0;

                Search_pattern1 = Pattern.compile("[^[A-za-z]]" + element_value + "[^[A-za-z]]");
                Search_matcher1 = Search_pattern1.matcher(code);

                while (Search_matcher1.find())
                    ++coincidences_number;


//                Search_pattern = Pattern.compile(regex);
//                Search_matcher = Search_pattern.matcher(code);

                Map.put(element_value, coincidences_number);


            }

        } while (Search_matcher.find());
        } catch (Exception e) {
            return Map;
        }


        return Map;
    }

    LinkedHashMap<String, Integer> search_operators
            (Map.Entry<String, String> regex, LinkedHashMap<String, Integer> Map) {

        Pattern search_pattern = Pattern.compile(regex.getValue());
        Matcher search_matcher = search_pattern.matcher(code);
        int coincidences_number = 0;

        try {
            while (search_matcher.find())
                coincidences_number++;

            if (coincidences_number != 0)
                Map.put(regex.getKey(), coincidences_number);

        } catch (Exception e) {
            return Map;
        }


        return Map;
    }

    LinkedHashMap<String, Integer> search_methods(String regex, LinkedHashMap<String, Integer> Map) {
        Pattern Search_pattern, Search_pattern1 ;
        Matcher Search_matcher, Search_matcher1 ;
        String[] split ;
        String oldelement_value = "", element_value = "";

        try {
            do {

                int coincidences_number = 0;
                Search_pattern = Pattern.compile(regex);
                Search_matcher = Search_pattern.matcher(code);

                if (Search_matcher.find()) {
                    oldelement_value = code.substring(Search_matcher.start(), Search_matcher.end());

                    split = oldelement_value.split("\\.");
                    element_value = split[split.length - 1];
                    split = element_value.split("\\(");
                    element_value = split[0];
                    element_value = element_value.replaceAll("\\({0,1}.*?\\){0,1}", "");
                    element_value = element_value.trim();


                    Search_pattern1 = Pattern.compile("\\." + element_value + "[^[A-Za-z]]");
                    Search_matcher1 = Search_pattern1.matcher(code);


                    while (Search_matcher1.find())
                        ++coincidences_number;


                    if (coincidences_number != 0) {

                        Map.put(element_value, coincidences_number);

                        split = oldelement_value.split("\\(");
                        element_value = "\\w[^[\\s\\(]]+\\." + element_value; //+"\\s*?\\(\\w*?\\)";

                        Search_pattern = Pattern.compile(element_value);
                        Search_matcher = Search_pattern.matcher(code);

                        while (Search_matcher.find()) {
                            oldelement_value = code.substring(Search_matcher.start(), Search_matcher.end());
                            code = code.replaceAll(oldelement_value, " ");
                            Search_pattern = Pattern.compile(element_value);
                            Search_matcher = Search_pattern.matcher(code);


                        }

                    }
                }

                Search_pattern = Pattern.compile(regex);
                Search_matcher = Search_pattern.matcher(code);

            } while (Search_matcher.find());

        } catch (Exception e) { return Map ; }
        return Map ;


    }

    LinkedHashMap<String, Integer> search_opeands(String regex, LinkedHashMap<String, Integer> Map) {

        Pattern Search_pattern, Search_pattern1;
        Matcher Search_matcher, Search_matcher1;

        String[] split;
        String element_value = "";
        int coincidences_number = 0;
        String reg;

try {
        do {

            Search_pattern = Pattern.compile(regex);
            Search_matcher = Search_pattern.matcher(code);

            while (Search_matcher.find()) {

                element_value = code.substring(Search_matcher.start(), Search_matcher.end());
                element_value = element_value.replaceAll(";", " ");
                split = element_value.split(",");


                for (int i = 0; i < split.length; ++i) {

                    split[i] = split[i].replaceAll("=.+", "");
                    split[i] = split[i].replaceAll(".+?\\(", "");
                    split[i] = split[i].replaceAll("\\)+", "");
                    split[i] = split[i].replaceAll("(String|int|float|double|byte|short|char|boolean|long)",
                            "");
                    split[i] = split[i].replaceAll("\\[.*\\]"," ");
                    split[i] = split[i].trim();
                    if (!Map.containsKey(split[i])) {
                        reg = "[^[A-Za-z]]" + split[i].trim() + "[^[A-Za-z]]";
                        //split[i] = split[i]+"[]";
                        coincidences_number = 0;

                        Search_pattern1 = Pattern.compile(reg);
                        Search_matcher1 = Search_pattern1.matcher(code);

                        while (Search_matcher1.find()) {
                            ++coincidences_number;
                        }


                        if (--coincidences_number != 0 && split[i].length() != 0)
                            Map.put(split[i].trim(), coincidences_number);
                    }
                }

            }

        } while (Search_matcher.find());

    } catch (Exception e) {
    return Map;
    }


        return Map;
    }


   /* LinkedHashMap<String, Integer> search_opeandsOOP(String regex, LinkedHashMap<String, Integer> Map) {

        Pattern Search_pattern, Search_pattern1;
        Matcher Search_matcher, Search_matcher1;

        String[] split;
        String element_value = "";
        int coincidences_number = 0;
        String reg;


        try {
            do {

                Search_pattern = Pattern.compile(regex);
                Search_matcher = Search_pattern.matcher(code);

                while (Search_matcher.find()) {

                    element_value = code.substring(Search_matcher.start(), Search_matcher.end());
                    element_value = element_value.replaceAll(";", " ");
                    split = element_value.split(",");


                    for (int i = 0; i < split.length; ++i) {

                        split[i] = split[i].replaceAll("=.+", "");
                        split[i] = split[i].replaceAll(".+?\\(", "");
                        split[i] = split[i].replaceAll("\\)+", "");
                        split[i] = split[i].replaceFirst("([A-Z]\\w+?\\s)",
                                "");
                        split[i] = split[i].replaceFirst("\\[.*?\\]", "");
                        split[i] = split[i].trim();
                        if (!Map.containsKey(split[i])) {
                            reg = "[^[A-Za-z]]" + split[i].trim() + "[^[A-Za-z]]";
                            coincidences_number = 0;

                            Search_pattern1 = Pattern.compile(reg);
                            Search_matcher1 = Search_pattern1.matcher(code);

                            while (Search_matcher1.find()) {
                                ++coincidences_number;
                            }


                            if (--coincidences_number != 0 && split[i].length() != 0)
                                Map.put(split[i].trim(), coincidences_number);
                        }
                    }

                }

            } while (Search_matcher.find());
        } catch (Exception e) {
            return Map;
        }

        return Map;
    }
*/



    LinkedHashMap<String, Integer> deleteElse(LinkedHashMap<String, Integer> Map) {
        if (Map.containsKey("if") && Map.containsKey("if else")) {
            Map.put("if", Map.get("if") - Map.get("if else"));
        }
        return Map;

    }

    public static int countCondOperators(LinkedHashMap<String, Integer> Map) {
       int count=0;
        if (Map.containsKey("if ")) count+=Map.get("if ");
        if (Map.containsKey("if else")) count+=Map.get("if else");
        if (Map.containsKey("for")) count+=Map.get("for");
        if (Map.containsKey("foreach")) count+=Map.get("foreach");
        if (Map.containsKey("do while")) count+=Map.get("do while");
        if (Map.containsKey("while")) count+=Map.get("while");
        if (Map.containsKey("switch")) count+=Map.get("switch");
       return count;

    }

    LinkedHashMap<String,Integer> search_numbers
            (Map.Entry<String,String> regex, LinkedHashMap <String,Integer> Map) {

        Pattern search_pattern = Pattern.compile(regex.getValue());
        Matcher search_matcher = search_pattern.matcher(code);

        try {

            while (search_matcher.find()) {
                String temp = code.substring(search_matcher.start() + 1, search_matcher.end());
                System.out.println(temp);
                if (Map.containsKey(temp)) {
                    int counter = Map.get(temp);
                    Map.put(temp, ++counter);
                } else {
                    Map.put(temp, 1);
                }
            }


            //   while (search_matcher.find())
            //      coincidences_number++ ;

            //   if (coincidences_number != 0 )
            //       Map.put(regex.getKey(),coincidences_number) ;
            //   System.out.println(coincidences_number);
        }
         catch (Exception e) {      return Map; }

        return Map ;
    }



}

