package sample.including;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextChanges {
    static String textPreparation (String text) {

        text = text.replaceAll("\n"," ");
        text = text.replaceAll(" break\\s*;","break;");
        text = TextChanges.search(text, " for\\s*");
        text = TextChanges.search(text, " if\\s*");
        text = text.replaceAll("else\\s*?"," if () ");
        text = TextChanges.search(text, " while ");
        text = TextChanges.searchSwitch(text);
        text = text.replaceAll(" default\\s*:"," if () ");
        text = TextChanges.searchCase(text);
        text = text.replaceAll(" case\\s*.=?\\s*:"," if () ");
        text = text.replaceAll("\\{"," { ");
        text = text.replaceAll("}"," } ");
        text = text.replaceAll("\\s{2,}"," ");
        text = TextChanges.searchAloneIf(text);
        text +="  "; // kostil

        return text ;
    }

    private static String search (String text, String statement) {
        Pattern pattern = Pattern.compile(statement);
        Matcher matcher = pattern.matcher(text) ;
        StringBuilder newText = new StringBuilder() ;
        int i = 0 ;

        while ( (matcher.find())) {

            if ( i < matcher.start()) {
            newText.append(text, i, matcher.start());

            i = matcher.end();
            int skobkaBegin = 0, skobkaEnd = 0 ;
            do {

                if (text.charAt(i) == '(' ) ++skobkaBegin ;
                if (text.charAt(i) == ')' ) ++skobkaEnd;
                ++i ;
            } while (skobkaBegin != skobkaEnd ^ (skobkaBegin == 0 && skobkaEnd  == 0) );


            newText.append(" if () ") ;
            }

        }




    return  String.valueOf(newText.append(text.substring(i)));
    }


    private static String searchSwitch (String text) {
        Pattern pattern = Pattern.compile("\\s*switch\\s*\\(.*?\\)\\s*\\{");
        Matcher matcher = pattern.matcher(text) ;
        StringBuilder newText = new StringBuilder() ;
        int i = 0 ;

        while ( (matcher.find())) {
            newText.append(text, i, matcher.start());

                i = matcher.end() - 1;
                int skobkaBegin = 0, skobkaEnd = 0 ;
                do {

                    if (text.charAt(i) == '{' ) ++skobkaBegin ;
                    if (text.charAt(i) == '}' ) ++skobkaEnd;
                    ++i ;
                } while (skobkaBegin != skobkaEnd ^ (skobkaBegin == 0 && skobkaEnd  == 0) );
                newText.append(text, matcher.end(), i-1);

            }


        return  String.valueOf(newText.append(text.substring(i)));
    }

    private static String searchCase (String text) {

        Pattern pattern = Pattern.compile("case\\s*.*?\\s*:");
        Matcher matcher = pattern.matcher(text) ;
        StringBuilder newText = new StringBuilder();
        int i = 0 ;


        if (matcher.find()) {
            newText.append(text, i, matcher.end());
            newText.append("{");
            i = matcher.end();
        }

        while ( (matcher.find())) {

            newText.append(text, i, matcher.end());
            newText.insert(newText.length()-(matcher.end()-matcher.start())-1,"}");
            newText.append("{");
            i = matcher.end();

        }

        return  String.valueOf(newText.append(text.substring(i)));
    }

    private static String searchAloneIf (String text) {

        Pattern pattern = Pattern.compile("if \\(\\)\\s+[^{]");
        Matcher matcher = pattern.matcher(text) ;
        StringBuilder newText = new StringBuilder(text);
        int j ;

        while ( (matcher.find())) {
            j = matcher.end() ;
            newText.insert(matcher.end()-1,"{ ");

            while ( (text.charAt(j) != ';')) ++j;
            newText.insert(j+3,' ');
            newText.insert(j+4,'}');

            text = String.valueOf(newText);
            matcher = pattern.matcher(text) ;
        }

        return  String.valueOf(newText);
    }
}
