/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

/**
 * Classe console para entrada e saída de texto
 * @author Visitante7
 */
public class Console {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int contador = 0;
        while(contador < 5) {
            System.out.println(contador);
            contador++;
        }
        System.out.println("Hello World!");
        System.exit(0);
        // TODO code application logic here
    }
    
}
