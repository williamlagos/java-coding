import java.util.Scanner;

/*
 * Classe com método principal com códigos elementares
 * @author William Oliveira de Lagos
 */
public class Basic {
    /*
     * Código com exemplos de entrada, saída e variáveis,
     * estruturado em uma função principal (main)
     */
    public static void main(String[] args) {
        /*
         * Declarações, definições e usos de variáveis,
         * bem como algumas manipulações
         */
        // Tipos primitivos
        int inteiro = 25;
        long inteiroGrande = 3600;
        float decimalSimples = 2.5f;
        double decimal = 25.90;
        char letra = 'A';
        boolean condicional = true;

        // Tipos elaborados
        String texto = "Olá mundo!";
        String[] textos = { "Olá", "Mundo" };    
        /* 
         * Exemplo de entrada e saída de texto
         * Escrita em linha e formatada com códigos especiais
         */
        System.out.printf("%d %d %f %f %c %b \n", inteiro, inteiroGrande, decimalSimples, decimal, letra, condicional);
        System.out.printf("%s %s %s \n", texto, textos[0], textos[1]);
        System.out.print("Digite um valor: ");

        // Exemplo de entrada de texto
        Scanner scan = new Scanner(System.in);
        int valor = scan.nextInt();
        scan.close();
        
        System.out.printf("O valor é: %d", valor);
    }
}