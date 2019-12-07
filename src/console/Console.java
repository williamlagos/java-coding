/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Classe console para entrada e saída de texto
 * @author Visitante7
 */
public class Console {

    public static void leTexto() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite um número:");
        int i = Integer.parseInt(br.readLine());
        
        System.out.print("Digite um número:");
        String s = br.readLine();
        int i2 = Integer.parseInt(s);
        
        Scanner scan = new Scanner(System.in);
        String s2 = scan.next();
        int i3 = scan.nextInt();
        
        System.out.printf("%s %s %d %d %d",s,s2,i,i2,i3);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        try {
          leTexto();
        } catch (IOException exc) {
          System.out.println(exc.getMessage());
        }
        if(args.length == 0) {
          throw new Exception();   
        }
        
        int[] numeros = {1,2,3,4,5,6,7};
        
        for(int numero: numeros) {
            System.out.println(numero);
        }
        
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

    private static void leOpcao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
