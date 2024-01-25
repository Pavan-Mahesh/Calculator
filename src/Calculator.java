import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
    JFrame frame;
    JLabel label;
    JTextField textField;
    JPanel panel;
    JButton[] numButtons = new JButton[10];
    JButton[] funButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton dotButton, equButton, delButton, clrButton;
    Font myFont = new Font("Cascadia Mono", Font.PLAIN, 25);
    float num1 = 0f, num2 = 0f, result = 0f;
    char operator;
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setResizable(false);
        frame.setLayout(null);

        label = new JLabel();
        label.setText("0.0");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBounds(50, 25, 300, 30);
        label.setFont(myFont);

        textField = new JTextField();
        textField.setBounds(50, 75, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        dotButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        funButtons[0] = addButton;
        funButtons[1] = subButton;
        funButtons[2] = mulButton;
        funButtons[3] = divButton;
        funButtons[4] = dotButton;
        funButtons[5] = equButton;
        funButtons[6] = delButton;
        funButtons[7] = clrButton;
        for(int i=0; i<8; i++) {
            funButtons[i].addActionListener(this);
            funButtons[i].setFont(myFont);
            funButtons[i].setFocusable(false);
        }

        for(int i=0; i<10; i++) {
            numButtons[i] = new JButton(Integer.toString(i));
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(myFont);
            numButtons[i].setFocusable(false);
        }

        panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBounds(50, 150, 300, 300);

        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(addButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(mulButton);
        panel.add(dotButton);
        panel.add(numButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        delButton.setBounds(50, 480, 145, 50);
        clrButton.setBounds(205, 480, 145, 50);

        frame.add(label);
        frame.add(textField);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Calculator();
    }
    boolean resultPrinted = false;
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!textField.getText().isEmpty() && Character.isAlphabetic(textField.getText().charAt(0))) {
            label.setText("0.0");
            textField.setText("");
        }
        for(int i=0; i<10; i++) {
            if(ae.getSource() == numButtons[i]) {
                if(resultPrinted) {
                    label.setText("0.0");
                    textField.setText("");
                }
                textField.setText(textField.getText().concat(Integer.toString(i)));
                break;
            }
        }
        if(ae.getSource() == delButton) {
            if(resultPrinted) {
                textField.setText("");
                label.setText("0.0");
            }
            String str = textField.getText();
            textField.setText("");
            for(int i=0; i<str.length()-1; i++)
                textField.setText(textField.getText() + str.charAt(i));
        }
        resultPrinted = false;
        if(ae.getSource() == clrButton) {
            textField.setText("");
            label.setText("0.0");
            return;
        }
        if(ae.getSource() == dotButton) {
            if(textField.getText().isEmpty())
                textField.setText("0");
            if(!textField.getText().contains("."))
                textField.setText(textField.getText().concat("."));
            return;
        }
        if(ae.getSource() == subButton) {
            if(textField.getText().isEmpty())
                textField.setText("-");
            else {
                num1 = Float.parseFloat(textField.getText());
                operator = '-';
                label.setText(num1 + " - ");
                textField.setText("");
            }
            return;
        }
        if(textField.getText().compareTo("") == 0)
            return;
        if(ae.getSource() == addButton) {
            num1 = Float.parseFloat(textField.getText());
            operator = '+';
            label.setText(num1 + " + ");
            textField.setText("");
            return;
        }
        if(ae.getSource() == mulButton) {
            num1 = Float.parseFloat(textField.getText());
            operator = '*';
            label.setText(num1 + " * ");
            textField.setText("");
            return;
        }
        if(ae.getSource() == divButton) {
            num1 = Float.parseFloat(textField.getText());
            operator = '/';
            label.setText(num1 + " / ");
            textField.setText("");
            return;
        }
        if(ae.getSource() == equButton) {
            num2 = Float.parseFloat(textField.getText());
            switch(operator) {
                case '+':
                    result = num1 + num2; break;
                case '-':
                    result = num1 - num2; break;
                case '*':
                    result = num1 * num2; break;
                case '/':
                    result = num1 / num2; break;
            }
            num1 = result;
            label.setText(Float.toString(result));
            textField.setText(Float.toString(result));
            resultPrinted = true;
            return;
        }
    }
}