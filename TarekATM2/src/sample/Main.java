package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main extends Application implements EventHandler<ActionEvent>{
    Button withdrawButton;
    Button depositButton;
    Button balanceButton;
    Button prevButton;
    Button nextButton;
    Label label;
    ErrorWindow errorWindow = new ErrorWindow();
    public void reset()
    {
        nextButton.setDisable(true);
        while(!nextTransactions.empty())
            prevTransactions.add((nextTransactions.pop()));
        if(prevTransactions.size()>4)
        {
            prevTransactions.pop();
        }
    }
    Stack<Transaction> nextTransactions = new Stack();
    Deque<Transaction> prevTransactions = new LinkedList<>();
    Transaction prevtemp = new Transaction();
    Transaction nexttemp = new Transaction();
    sample.InputBox inputBox = new sample.InputBox();
    Account account = new Account();

    Label passwordLabel;
    Button enterButton;
    PasswordField passwordField;
    String password = "1234";
    Transaction temp = new Transaction();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ATM");
        label = new Label("");
        label.setPadding(new Insets(0,0,0,5));
        depositButton = new Button("Deposit");
        depositButton.setOnAction(this);
        withdrawButton = new Button("Withdraw");
        balanceButton = new Button("Balance Inquiry");
        prevButton = new Button("Previous");
        nextButton = new Button("Next");
        nextButton.setDisable(true);
        prevButton.setDisable(true);
        withdrawButton.setOnAction(e-> {
            reset();
            boolean x;
            inputBox.initInputBox();
            temp = inputBox.display("Withdraw");
            x= account.withdraw(temp.getAmount());
            if(temp.getType()!=null&& x) {
                prevTransactions.add(temp);
                if(prevButton.isDisabled())
                    prevButton.setDisable(false);
            }
        });
        depositButton.setOnAction(e-> {
            reset();
            inputBox.initInputBox();
            temp = inputBox.display("Deposit");
            account.deposit(temp.getAmount());
            if(temp.getType()!=null) {
                  prevTransactions.add(temp);
                if(prevButton.isDisabled())
                    prevButton.setDisable(false);
            }
        });
        balanceButton.setOnAction(e->{
            label.setText("Balance: "+ Long.toString(account.getBalance()));
        });
        GridPane grid = new GridPane();
        grid.add(depositButton,0,0);
        grid.add(withdrawButton,0,1);
        grid.add(balanceButton,0,2);
        grid.add(prevButton,1,0);
        grid.add(nextButton,1,1);
        grid.add(label,1,2);
        grid.setAlignment(Pos.CENTER);

        grid.setVgap(10);
        grid.setHgap(10);
        Scene mainMenuScene = new Scene(grid , 400,200);

        GridPane grid2 = new GridPane();
        passwordLabel = new Label("Card Number:");
        passwordField = new PasswordField();
        enterButton = new Button("Enter");
        grid2.addRow(2,passwordLabel,passwordField);
        grid2.add(enterButton,1,4);
        grid2.setAlignment(Pos.TOP_CENTER);
        Scene ATM = new Scene(grid2,300,275);

        enterButton.setOnAction(e ->
        {
            if (password.equals(passwordField.getText())) {
                primaryStage.setScene(mainMenuScene);
                primaryStage.show();
            }
            else{
                errorWindow.displayErrorWindow("Invalid Input","Invalid card number, please try again.");
            }

        });
        nextButton.setOnAction(e->{
            nexttemp = nextTransactions.pop();
            if(nexttemp.printTransaction().equals(label.getText())) {
                prevTransactions.addLast(nexttemp);
                nexttemp = nextTransactions.pop();
            }
                label.setText(nexttemp.printTransaction());
                prevTransactions.addLast(nexttemp);

            if(prevButton.isDisabled())
                prevButton.setDisable(false);
            if(nextTransactions.empty())
                nextButton.setDisable(true);
        });
        prevButton.setOnAction(e->{
            prevtemp = prevTransactions.pollLast();
            if(prevtemp.printTransaction().equals(label.getText())) {
                nextTransactions.add(prevtemp);
                prevtemp = prevTransactions.pollLast();
            }
                label.setText(prevtemp.printTransaction());
                nextTransactions.add(prevtemp);
            if(nextButton.isDisabled()&&nextTransactions.size()!=1)
            {
                nextButton.setDisable(false);
            }
            if(prevTransactions.isEmpty())
                prevButton.setDisable(true);
        });
        primaryStage.setScene(ATM);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {


    }

}
