package sample;

public class FillingTable {

    private String idOperator;
    private String operator;
    private String kolvoOperator ;
    private String idOperand ;
    private String operand;
    private String kolvoOperand;

    public FillingTable(String idOperator, String operator, String kolvoOperator ,
                        String idOperand,String operand, String kolvoOperand) {

        this.idOperator = idOperator;
        this.operator = operator;
        this.kolvoOperator = kolvoOperator ;
        this.operand = operand;
        this.idOperand = idOperand ;
        this.kolvoOperand = kolvoOperand;
    }

    public FillingTable() {
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getKolvoOperator () {
        return kolvoOperator ;
    }

    public void setKolvoOperator (String kolvoOperator) {
        this.kolvoOperator = kolvoOperator ;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public String getIdOperand() {
        return idOperand;
    }

    public void setIdOperand(String idOperand) {
        this.idOperand = idOperand;
    }

    public String getKolvoOperand() {
        return kolvoOperand;
    }

    public void setKolvoOperand(String kolvoOperand) {
        this.kolvoOperand = kolvoOperand;
    }
}

