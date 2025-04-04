import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame implements ActionListener {
    private JTextField visor;
    private double num1, num2, resultado;
    private char operador;

    public CalculadoraGUI() {
        // Configuração da janela
        setTitle("Calculadora Java");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela

        // Layout principal
        setLayout(new BorderLayout());

        // Visor
        visor = new JTextField();
        visor.setFont(new Font("Arial", Font.PLAIN, 24));
        visor.setEditable(false);
        visor.setHorizontalAlignment(SwingConstants.RIGHT);
        add(visor, BorderLayout.NORTH);

        // Painel de botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            painel.add(btn);
        }

        add(painel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("[0-9]")) {
            visor.setText(visor.getText() + comando);
        } else if (comando.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(visor.getText());
                operador = comando.charAt(0);
                visor.setText("");
            } catch (NumberFormatException ex) {
                visor.setText("Erro");
            }
        } else if (comando.equals("=")) {
            try {
                num2 = Double.parseDouble(visor.getText());
                switch (operador) {
                    case '+': resultado = num1 + num2; break;
                    case '-': resultado = num1 - num2; break;
                    case '*': resultado = num1 * num2; break;
                    case '/': 
                        if (num2 == 0) {
                            visor.setText("Divisão por 0");
                            return;
                        }
                        resultado = num1 / num2; break;
                }
                visor.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                visor.setText("Erro");
            }
        } else if (comando.equals("C")) {
            visor.setText("");
            num1 = 0;
            num2 = 0;
            resultado = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}
