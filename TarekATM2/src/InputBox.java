package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InputBox {
    long value=0;
    long x;

    public void initInputBox() {
        this.value = 0;
        this.x = 0;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public Transaction display(String type){
        Transaction trans = new Transaction();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enter The Amount");
        Label label = new Label();
        Button submitButton = new Button("Submit");
        Button b0 = new Button("0");
        Button b1 = new Button("1");
        Button b2 = new Button("2");
        Button b3 = new Button("3");
        Button b4 = new Button("4");
        Button b5 = new Button("5");
        Button b6 = new Button("6");
        Button b7 = new Button("7");
        Button b8 = new Button("8");
        Button b9 = new Button("9");
        Button eraseButton = new Button("Erase");
        GridPane inputGrid = new GridPane();
        Button closeButton = new Button ("Close Window");
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.addRow(0,b1,b2,b3);
        inputGrid.addRow(1,b4,b5,b6);
        inputGrid.addRow(2,b7,b8,b9);
        inputGrid.add(b0,1,3);
        inputGrid.add(closeButton,5,6);
        inputGrid.add(eraseButton,5,5);
        inputGrid.add(label,5,3);
        inputGrid.add(submitButton,5,4);
        eraseButton.setOnAction(e->{
            setValue(0);
            label.setText("");
        });


        closeButton.setOnAction(e-> window.close());

        b0.setOnAction(e->{
            if(value!=0){
                setValue(getValue()*10);
                label.setText(Long.toString(getValue()));
            }
        });
        b1.setOnAction(e->{
            setValue((getValue()*10)+1);
            label.setText(Long.toString(getValue()));
        });
        b2.setOnAction(e->{
            setValue((getValue()*10)+2);
            label.setText(Long.toString(getValue()));
        });
        b3.setOnAction(e->{
            setValue((getValue()*10)+3);
            label.setText(Long.toString(getValue()));
        });
        b4.setOnAction(e->{
            setValue((getValue()*10)+4);
            label.setText(Long.toString(getValue()));
        });
        b5.setOnAction(e->{
            setValue((getValue()*10)+5);
            label.setText(Long.toString(getValue()));
        });
        b6.setOnAction(e->{
            setValue((getValue()*10)+6);
            label.setText(Long.toString(getValue()));
        });
        b7.setOnAction(e->{
            setValue((getValue()*10)+7);
            label.setText(Long.toString(getValue()));
        });
        b8.setOnAction(e->{
            setValue((getValue()*10)+8);
            label.setText(Long.toString(getValue()));
        });
        b9.setOnAction(e->{
            setValue((getValue()*10)+9);
            label.setText(Long.toString(getValue()));
        });
        submitButton.setOnAction(e->
        {
            trans.setAmount(getValue());
            trans.setType(type);
            window.close();
        });
        Scene scene = new Scene(inputGrid,200,200);
        window.setScene(scene);
        window.showAndWait();
        return trans;
    }
}
