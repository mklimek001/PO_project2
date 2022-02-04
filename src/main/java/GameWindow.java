import java.awt.event.*;
import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class GameWindow implements ActionListener {
    String kngdName = "";
    Kingdom gamerKingdom = new Kingdom(kngdName);

    JFrame frame = new JFrame();
    JTextField nameField = new JTextField();
    JTextField roundField = new JTextField();
    JTextField resourcesField = new JTextField();
    JTextArea dayActionDesc = new JTextArea();
    JButton firstOption = new JButton();
    JButton secondOption = new JButton();
    JTextArea decisionResult = new JTextArea();

    JButton startNewGame = new JButton();
    JButton saveGame = new JButton();
    JButton reReadGame = new JButton();

    JFrame menuFrame = new JFrame();
    JTextField titleFrame = new JTextField();
    JTextField newKingdomName = new JTextField();
    JButton newGame = new JButton();
    JButton readGame = new JButton();


    public GameWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.getContentPane().setBackground(new Color(235, 229, 213));
        frame.setLayout(null);
        frame.setResizable(false);

        nameField.setBounds(0,0,800,50);
        nameField.setBackground(new Color(235, 229, 213));
        nameField.setForeground(new Color(25,25,25));
        nameField.setFont(new Font("Lato",Font.BOLD,30));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setEditable(false);

        roundField.setBounds(0,50,800,50);
        roundField.setBackground(new Color(235, 229, 213));
        roundField.setForeground(new Color(25,25,25));
        roundField.setFont(new Font("Lato",Font.BOLD,30));
        roundField.setHorizontalAlignment(JTextField.CENTER);
        roundField.setEditable(false);

        resourcesField.setBounds(0,100,800,50);
        resourcesField.setBackground(new Color(235, 229, 213));
        resourcesField.setForeground(new Color(25,25,25));
        resourcesField.setFont(new Font("Lato",Font.BOLD,20));
        resourcesField.setHorizontalAlignment(JTextField.CENTER);
        resourcesField.setEditable(false);

        dayActionDesc.setBounds(10,160,780,90);
        dayActionDesc.setLineWrap(true);
        dayActionDesc.setWrapStyleWord(true);
        dayActionDesc.setBackground(new Color(235, 229, 213));
        dayActionDesc.setForeground(new Color(25,25,25));
        dayActionDesc.setFont(new Font("Lato",Font.ITALIC,23));
        dayActionDesc.setEditable(false);

        firstOption.setBounds(0,275,400,80);
        firstOption.setFont(new Font("Lato",Font.BOLD,25));
        firstOption.setBackground( new Color(224, 216, 193));
        firstOption.setFocusable(false);
        firstOption.addActionListener(this);

        secondOption.setBounds(400,275,400,80);
        secondOption.setFont(new Font("Lato",Font.BOLD,25));
        secondOption.setBackground( new Color(224, 216, 193));
        secondOption.setFocusable(false);
        secondOption.addActionListener(this);

        decisionResult.setBounds(10,450,780,150);
        decisionResult.setLineWrap(true);
        decisionResult.setWrapStyleWord(true);
        decisionResult.setBackground(new Color(235, 229, 213));
        decisionResult.setForeground(new Color(25,25,25));
        decisionResult.setFont(new Font("Lato",Font.ITALIC,23));
        decisionResult.setEditable(false);

        startNewGame.setBounds(0,275,800,80);
        startNewGame.setFont(new Font("Lato",Font.BOLD,25));
        startNewGame.setBackground( new Color(224, 216, 193));
        startNewGame.setFocusable(false);
        startNewGame.setVisible(false);
        startNewGame.addActionListener(this);
        startNewGame.setText("Zacznij grę od nowa");

        saveGame.setBounds(250,365,300,60);
        saveGame.setFont(new Font("Lato",Font.BOLD,19));
        saveGame.setBackground( new Color(224, 216, 193));
        saveGame.setFocusable(false);
        saveGame.addActionListener(this);
        saveGame.setText("Zapisz stan gry");

        reReadGame.setBounds(250,365,300,60);
        reReadGame.setFont(new Font("Lato",Font.BOLD,19));
        reReadGame.setBackground( new Color(224, 216, 193));
        reReadGame.setFocusable(false);
        reReadGame.addActionListener(this);
        reReadGame.setVisible(false);
        reReadGame.setText("Wczytaj grę");

        frame.add(nameField);
        frame.add(roundField);
        frame.add(resourcesField);
        frame.add(dayActionDesc);
        frame.add(firstOption);
        frame.add(secondOption);
        frame.add(decisionResult);
        frame.add(startNewGame);
        frame.add(saveGame);
        frame.add(reReadGame);


        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800,600);
        menuFrame.getContentPane().setBackground(new Color(235, 229, 213));
        menuFrame.setLayout(null);
        menuFrame.setResizable(false);

        titleFrame.setBounds(0,0,800,100);
        titleFrame.setBackground(new Color(235, 229, 213));
        titleFrame.setForeground(new Color(25,25,25));
        titleFrame.setFont(new Font("Lato",Font.BOLD,30));
        titleFrame.setHorizontalAlignment(JTextField.CENTER);
        titleFrame.setEditable(false);
        titleFrame.setText("Witaj w grze, podaj nazwę swojego królestwa:");

        newKingdomName.setBounds(50,160,700,80);
        newKingdomName.setBackground(new Color(224, 216, 193));
        newKingdomName.setForeground(new Color(25,25,25));
        newKingdomName.setFont(new Font("Lato",Font.ITALIC,30));
        newKingdomName.setBorder(BorderFactory.createBevelBorder(1));

        newGame.setBounds(100,310,600,80);
        newGame.setFont(new Font("Lato",Font.BOLD,25));
        newGame.setBackground( new Color(224, 216, 193));
        newGame.setFocusable(false);
        newGame.setVisible(true);
        newGame.addActionListener(this);
        newGame.setText("Nowa gra");

        readGame.setBounds(100,410,600,80);
        readGame.setFont(new Font("Lato",Font.BOLD,25));
        readGame.setBackground( new Color(224, 216, 193));
        readGame.setFocusable(false);
        readGame.setVisible(true);
        readGame.addActionListener(this);
        readGame.setText("Wczytaj grę");

        menuFrame.add(titleFrame);
        menuFrame.add(newKingdomName);
        menuFrame.add(newGame);
        menuFrame.add(readGame);
        menuFrame.setVisible(true);

        nextCard();
    }

    public void nextCard() {

        if(this.gamerKingdom.isInGoodCondition) {
            nameField.setText("Witaj królu " + this.gamerKingdom.kingdomName);
            roundField.setText("dzień " + gamerKingdom.roundNum);
            resourcesField.setText("    Armia : " + this.gamerKingdom.currentResources.armyValue.value + "   Złoto: " + this.gamerKingdom.currentResources.goldValue.value + "    Żywność: "+ this.gamerKingdom.currentResources.foodValue.value + "   Technologia: " + this.gamerKingdom.currentResources.technologyValue.value);
            dayActionDesc.setText(this.gamerKingdom.nextRoundCard.advice);
            firstOption.setText(this.gamerKingdom.nextRoundCard.firstOption.shortProposition);
            secondOption.setText(this.gamerKingdom.nextRoundCard.secondOption.shortProposition);

        }
        else {
            results();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==firstOption) {
            decisionResult.setText(this.gamerKingdom.nextRoundCard.firstOption.censequncesDesc);
            this.gamerKingdom.nextDay(1);
        }

        if(e.getSource()==secondOption) {
            decisionResult.setText(this.gamerKingdom.nextRoundCard.secondOption.censequncesDesc);
            this.gamerKingdom.nextDay(2);
        }

        if(e.getSource()==startNewGame) {
            startNewGame.setVisible(false);
            reReadGame.setVisible(false);
            firstOption.setVisible(true);
            secondOption.setVisible(true);
            saveGame.setVisible(true);
            this.gamerKingdom = new Kingdom(this.kngdName);
        }

        if(e.getSource()==newGame || e.getSource()==readGame) {
            this.kngdName = newKingdomName.getText();
            this.gamerKingdom.kingdomName = this.kngdName;
            frame.setVisible(true);
            menuFrame.setVisible(false);
        }

        if(e.getSource()==reReadGame) {
            this.gamerKingdom = new Kingdom(kngdName);

            try {
                this.gamerKingdom.downloadFromFile();
                kngdName = this.gamerKingdom.kingdomName;
            } catch (FileNotFoundException ex) {
                decisionResult.setText("Nie udało się poprawnie pobrać danych");
            }

            startNewGame.setVisible(false);
            reReadGame.setVisible(false);
            firstOption.setVisible(true);
            secondOption.setVisible(true);
            saveGame.setVisible(true);

        }

        if(e.getSource()==readGame) {
            try {
                this.gamerKingdom.downloadFromFile();
                kngdName = this.gamerKingdom.kingdomName;
            } catch (FileNotFoundException ex) {
                decisionResult.setText("Nie udało się poprawnie pobrać danych");
            }

        }

        if(e.getSource()==saveGame) {
            try {
                this.gamerKingdom.saveToFile();
            } catch (FileNotFoundException ex) {
                decisionResult.setText("Nie udało się poprawnie zapisać danych");
            }
        }

        nextCard();
    }

    public void results(){
        nameField.setText("Przegrana");
        roundField.setText("Królestwo " + gamerKingdom.kingdomName + " przetrwało " + gamerKingdom.roundNum + " rund!");;
        resourcesField.setText("    Armia : " + this.gamerKingdom.currentResources.armyValue.value + "   Złoto: " + this.gamerKingdom.currentResources.goldValue.value + "    Żywność: "+ this.gamerKingdom.currentResources.foodValue.value + "   Technologia: " + this.gamerKingdom.currentResources.technologyValue.value);
        dayActionDesc.setText("");
        firstOption.setVisible(false);
        secondOption.setVisible(false);
        saveGame.setVisible(false);
        decisionResult.setText("");
        startNewGame.setVisible(true);
        reReadGame.setVisible(true);

    }
}

