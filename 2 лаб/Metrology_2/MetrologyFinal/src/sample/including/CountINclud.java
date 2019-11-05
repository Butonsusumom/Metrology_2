package sample.including;

import sample.RemoveCom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CountINclud {

    public static int countInclud ()
    {
        String text = "";
        try (BufferedReader Reader = new BufferedReader((new InputStreamReader(new FileInputStream
                ("C:\\Users\\Ksusha\\Downloads\\Metrology_2\\MetrologyFinal\\file.txt"),
                StandardCharsets.UTF_8)))) {



            String line = Reader.readLine();

            while (line != null) {
                text = text + line + "\n";
                line = Reader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        text= RemoveCom.removeComments(text);
        text=text.replaceAll("\".+\""," ");
        System.out.println(text);

        text = TextChanges.textPreparation(text);
        System.out.println(text);

        String regEx = ".*if \\(\\) ";
        int vlosh = -1, maxVlosh = 0;
        int skobki =  0, notSkobki = 0 ;
        boolean flag = false ;
        StringBuilder str = new StringBuilder();
        char blockBegin = '{', blockEnd = '}';


        for (int i = 0; i < text.length(); i++) {
            str.append(text.charAt(i));

            if (text.charAt(i) == blockBegin && vlosh != - 1 ) {
                if (text.substring(i-6,i+1).equals("if () {"))  ++skobki;
                else ++notSkobki ;
            }
            if (text.charAt(i) == blockEnd &&  vlosh != - 1 )  {
                if (notSkobki != 0 ) --notSkobki;
                else {
                    if (maxVlosh < vlosh ) maxVlosh = vlosh ;
                    --vlosh;
                    --skobki;
                }
            }


            if (skobki == 0 && flag) {
                if (maxVlosh < vlosh ) maxVlosh = vlosh ;
                vlosh = - 1 ;
                skobki = 0 ;
                str = new StringBuilder();
                flag = false;
            }

            if (String.valueOf(str).matches(regEx + "\\{")) {
                str = new StringBuilder();
                if (vlosh == - 1 ) {
                    ++skobki ;
                    flag = true ;
                }
                ++vlosh ;

            }

        }
        return maxVlosh;
    }

}
