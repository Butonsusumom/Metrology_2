package sample;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.including.CountINclud;

public class Controller {

    private ObservableList<FillingTable> usersData = FXCollections.observableArrayList();

    @FXML
    private Label Dictionary,LabelJ,LabelI,  Volume, Length,LabelN1, LabelN2, LabelInclude,
            LabelComplixetyAbs, LabelComplixetyOtn;

    @FXML
    private TableView<FillingTable> table;

    @FXML
    private TableColumn<FillingTable, String> kolvoOperators,idOperands,kolvoOperands,
            idOperators, Operator, Operand;

    @FXML
    private Button BGo;

    @FXML
    void initialize() {

        BGo.setOnAction(actionEvent -> {

        table.getItems().clear();

        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        idOperators.setCellValueFactory(new PropertyValueFactory<FillingTable, String>("idOperator"));
        Operator.setCellValueFactory(new PropertyValueFactory<FillingTable, String>("operator"));
        kolvoOperators.setCellValueFactory(new PropertyValueFactory<FillingTable,String>("kolvoOperator"));
        idOperands.setCellValueFactory(new PropertyValueFactory<FillingTable, String>("idOperand"));
        Operand.setCellValueFactory(new PropertyValueFactory<FillingTable, String>("operand"));
        kolvoOperands.setCellValueFactory(new PropertyValueFactory<FillingTable,String>("kolvoOperand"));
        //emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

        // заполняем таблицу данными
        table.setItems(usersData);

        });

    }

    private void initData() {

        LinkedHashMap<String, Integer> Operators_Array = new LinkedHashMap<String, Integer>() ;
        LinkedHashMap<String, Integer> Operands_Array = new LinkedHashMap<String, Integer>() ;
//        OOPMap oopMap = new OOPMap();
        Loops loops =  new Loops();
        OperatorsMap operations =  new OperatorsMap();
        NumbersMap numbersmap =new NumbersMap();

        Search Search = new Search("");

        try (BufferedReader Reader = new BufferedReader((new InputStreamReader(new FileInputStream
                ("C:\\Users\\Ksusha\\Downloads\\Metrology_2\\MetrologyFinal\\file.txt"),
                "UTF-8"))) ) {


            String line = Reader.readLine(), text = "" ;

            while (line != null) {
                line = Reader.readLine() ;
                text = text + line + "\n";
            }

            Search.code = text ;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Search.code=RemoveCom.removeComments(Search.code);
      //  String str=Search.code;
      //   String findString () {
         //   return str;
       // }

        Operands_Array = Search.search_stroki("(\\\"|').+?(\\\"|')",Operands_Array) ;
        Search.code = Search.code.replaceAll("\".+\""," ");

         for (Map.Entry<String,String> entry : OperatorsMap.Operators.entrySet()) {
             if ( entry.getKey() == ".")
                Operators_Array = Search.search_operators(entry, Operators_Array) ;

        }


        Operands_Array = Search.search_opeands(  //
                "(String|int|float|double|byte|short|char|boolean)\\s+?[A-Za-z].+?(;|\\)[^[\\\n|\\s]]*)",Operands_Array) ;

   //  Operands_Array = Search.search_opeandsOOP("[A-Z]\\w*?\\s+?[A-Za-z].*?(;|\\))",Operands_Array) ;
   //      Operators_Array = Search.search_methods("\\w+\\.\\w.+?\\)",Operators_Array) ;

   //     for (Map.Entry<String,String> entry : OOPMap.Operators.entrySet()) {

 //           Operators_Array = Search.search_operators(entry, Operators_Array) ;
 //           if (entry.getKey() == "this") Search.code = Search.code.replaceAll(entry.getKey()+"\\.","") ;
 //           else Search.code = Search.code.replaceAll(entry.getValue()," ") ;
  //     }

        for (Map.Entry<String,String> entry : Loops.Operators.entrySet()) {
            Operators_Array = Search.search_operators(entry, Operators_Array) ;
            //Search.code = Search.code.replaceAll(entry.getValue()," ") ;
        } Operators_Array = Search.deleteElse( Operators_Array);

        for (Map.Entry<String,String> entry : NumbersMap.Operands.entrySet()) {
            Operands_Array = Search.search_numbers(entry, Operands_Array) ;

        }

        for (Map.Entry<String,String> entry : OperatorsMap.Operators.entrySet()) {
            Operators_Array = Search.search_operators(entry, Operators_Array) ;
            Search.code = Search.code.replaceAll(entry.getValue()," ") ;
        }


        Object[] operators = Operators_Array.keySet().toArray() ;
        Object[] operands = Operands_Array.keySet().toArray() ;
        int i,j = 0, N1 = 0, N2 = 0; ;

        if ( Operators_Array.size() > Operands_Array.size()) {
            for ( i = 0; i < Operators_Array.size(); i++) {
                if (i < Operands_Array.size()) {
                    ++j;
                    N1 += Operators_Array.get(operators[i]);
                    N2 += Operands_Array.get(operands[i]);
                    usersData.add(new FillingTable
                            (Integer.toString(i),
                                    operators[i].toString(),
                                    Integer.toString(Operators_Array.get(operators[i].toString())),
                                    Integer.toString(i),
                                    operands[i].toString(),
                                    Integer.toString(Operands_Array.get(operands[i].toString()))));
                } else {
                    N1 += Operators_Array.get(operators[i]) ;
                    usersData.add(new FillingTable
                            (Integer.toString(i),
                                    operators[i].toString(),
                                    Integer.toString(Operators_Array.get(operators[i].toString())),
                                    "", "", ""));


                }
            }

            // --------------------- laba 2 including




            //-----------------------------
            LabelJ.setText("j = "+i);
            LabelI.setText("i = "+j);

            LabelN1.setText("N1 = "+N1);
            LabelN2.setText("N2 = "+N2);


            Dictionary.setText("Словарь программы = " + (i+j));
            Length.setText("Длина программы = " + (N1+N2));
            Volume.setText("Обьём программы = " + (
                    (int) (( Math.log(i+j)/ Math.log(2) ) * (N1+N2) ) )) ;

        }
         else {
            for ( i = 0; i < Operands_Array.size(); i++) {
                if (i < Operators_Array.size()) {
                    ++j ;
                    N1 += Operators_Array.get(operators[i]);
                    N2 += Operands_Array.get(operands[i]);
                    usersData.add(new FillingTable
                            (Integer.toString(i),
                                    operators[i].toString(),
                                    Integer.toString(Operators_Array.get(operators[i].toString())),
                                    Integer.toString(i),
                                    operands[i].toString(),
                                    Integer.toString(Operands_Array.get(operands[i].toString()))));
                }
                else {
                    N2 += Operands_Array.get(operands[i]);
                    usersData.add(new FillingTable
                            ("", "", "",
                                    Integer.toString(i),
                                    operands[i].toString(),
                                    Integer.toString(Operands_Array.get(operands[i].toString()))
                            ));
                }


            }
            LabelJ.setText("j = "+j);
            LabelI.setText("i = "+i);

            LabelN1.setText("N1 = "+ N1);
            LabelN2.setText("N2 = "+N2);

            Dictionary.setText("Словарь программы = " + (i+j));
            Length.setText("Длина программы = " + (N1+N2));
            Volume.setText("Обьём программы = " + (
                    (int) (( Math.log(i+j)/ Math.log(2) ) * (N1+N2) ) )) ;

        }

        int includ = CountINclud.countInclud();
        System.out.println(Operators_Array);
        System.out.println(Operands_Array);
        int abscomp = Search.countCondOperators( Operators_Array);
        float otncomp = (float)abscomp/N1;

        LabelInclude.setText("Макс. вложенность = "+includ);
        LabelComplixetyAbs.setText("Абс. сложность = "+ abscomp);
        LabelComplixetyOtn.setText("Отн. сложность = "+ String.format("%.2f",otncomp));


    }







}



