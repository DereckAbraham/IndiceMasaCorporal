import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new IMCCalculator();
            }
        });
    }
}

class IMCCalculator extends JFrame implements ActionListener {
    private JTextField textFieldWeight, textFieldHeight;
    private JLabel labelWeight, labelHeight, labelResult;
    private JButton buttonCalculate;

    public IMCCalculator() {
        setTitle("Indice de Masa Corporal");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));
        labelWeight = new JLabel("Coloca tu peso (kg):");
        labelHeight = new JLabel("Coloca tu altura (mts):");
        textFieldWeight = new JTextField(10);
        textFieldHeight = new JTextField(10);
        inputPanel.add(labelWeight);
        inputPanel.add(textFieldWeight);
        inputPanel.add(labelHeight);
        inputPanel.add(textFieldHeight);

        JPanel buttonPanel = new JPanel();
        buttonCalculate = new JButton("Calcular IMC");
        buttonCalculate.addActionListener(this);
        buttonPanel.add(buttonCalculate);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelResult = new JLabel("Tu IMC aparecera Aca :)");
        resultPanel.add(labelResult);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCalculate) {
            try {
                double weight = Double.parseDouble(textFieldWeight.getText());
                double height = Double.parseDouble(textFieldHeight.getText());

                double bmi = calculateBMI(weight, height);

                String bmiCategory = interpretBMI(bmi);

                labelResult.setText(String.format("Tu IMC es %.2f (%s)", bmi, bmiCategory));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Porfavor coloca datos reales en peso y altura >:(", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    private String interpretBMI(double bmi) {
        if (bmi < 18.5) {
            return "Bajo Peso";
        } else if (bmi < 25) {
            return "Peso Normal";
        } else if (bmi < 30) {
            return "Sobrepeso";
        } else {
            return "Obeso";
        }
    }
}