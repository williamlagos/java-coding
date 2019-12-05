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
        if(contador == 5) {
            System.out.println("Contador é igual a 5");
        }
        contador = 0;
        contador = 5;
        System.out.println("Hello World!");
        System.exit(0);
        // TODO code application logic here
    }
    
    /**
      * Retorna um número calculado 
      *
      * @param  numero1 Primeiro operador a ser calculado 
      * @param  numero2 Segundo operador a ser calculado
      * @return      Número calculado
      */
    public int calculaNumero(int numero1, int numero2) {
        return numero1 + numero2;
    }
    
}
