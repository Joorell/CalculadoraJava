import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculadora {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String continuar;

        System.out.println("=== CALCULADORA JAVA ===");

        do {
            double num1 = lerNumero(input, "Digite o primeiro número: ");
            double num2 = lerNumero(input, "Digite o segundo número: ");

            char operador = lerOperacao(input);

            double resultado = 0;
            boolean operacaoValida = true;

            switch (operador) {
                case '+':
                    resultado = num1 + num2;
                    break;
                case '-':
                    resultado = num1 - num2;
                    break;
                case '*':
                    resultado = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        System.out.println("Erro: Divisão por zero!");
                        operacaoValida = false;
                    }
                    break;
                default:
                    System.out.println("Operação inválida!");
                    operacaoValida = false;
                    break;
            }

            if (operacaoValida) {
                System.out.println("Resultado: " + resultado);
            }

            System.out.print("Deseja fazer outro cálculo? (s/n): ");
            continuar = input.next();

        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("Programa finalizado. Até mais!");
        input.close();
    }

    // Função para ler números com validação
    public static double lerNumero(Scanner input, String mensagem) {
        double numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensagem);
            try {
                numero = input.nextDouble();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
                input.next(); // Limpa a entrada incorreta
            }
        }

        return numero;
    }

    // Função para ler operação válida
    public static char lerOperacao(Scanner input) {
        char operador;

        do {
            System.out.print("Escolha a operação (+ - * /): ");
            String entrada = input.next();

            if (entrada.length() == 1) {
                operador = entrada.charAt(0);
                if (operador == '+' || operador == '-' || operador == '*' || operador == '/') {
                    return operador;
                }
            }

            System.out.println("Operação inválida! Digite apenas +, -, * ou /.");
        } while (true);
    }
}
