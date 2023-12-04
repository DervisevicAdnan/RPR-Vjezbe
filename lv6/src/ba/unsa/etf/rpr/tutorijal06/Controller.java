package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    public Label display;
    private Double vrijednost;
    private String operacija;
    private boolean resetV;
    private boolean resetO;

    public Controller(){
        resetV=true;
        resetO=true;
        vrijednost=0.0;
        operacija="+";
    }


    public void numBtnClicked(ActionEvent actionEvent){
        String s=((Button)actionEvent.getSource()).getText();
        if(display.getText().equals("0")||resetV) {
            display.setText(s);
            resetV=false;
        }
        else {
            display.setText(display.getText()+s);
        }
    }

    public void dotBtnClicked(ActionEvent actionEvent) {
        if(display.getText().indexOf(".")==-1)
            display.setText(display.getText()+".");
    }

    public void equalsBtnClicked(ActionEvent actionEvent){
        calculate();
        resetO=true;
    }
    private void calculate(){
        if(resetV) return;
        resetV=true;
        if(resetO) return;
        Double tmp=Double.parseDouble(display.getText());
        switch (operacija){
            case "-":
                vrijednost=vrijednost-tmp;
                break;
            case "x":
                vrijednost=vrijednost*tmp;
                break;
            case "/":
                if(tmp!=0) {
                    vrijednost = vrijednost / tmp;
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText("Dijeljenje sa nulom!");
                    alert.showAndWait();
                }
                break;
            default:
                vrijednost=vrijednost+tmp;
                break;
        }

        //za lijepo formatirani ispis
        //DecimalFormat format = new DecimalFormat("#.##");
        //display.setText(format.format(vrijednost));

        display.setText(vrijednost.toString());
    }

    public void operationBtnClicked(ActionEvent actionEvent){
        calculate();
        String s=((Button)actionEvent.getSource()).getText();
        if(s.equals("%")) {
            vrijednost=Double.parseDouble(display.getText())/100;
            operacija="x";
        }else{
            vrijednost=Double.parseDouble(display.getText());
            operacija=s;
        }
        resetO=false;
    }
}
