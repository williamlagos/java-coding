/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Visitante7
 */
public class Aula {
    class Licao {
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        int numero = 0;
        List<Integer> lista = new ArrayList<>();
        lista.add(numero);
        if(numero < 5) {
            System.out.println("Menor que 5");
        } else {
            System.out.println("Maior que 5");
        }
        while(numero < 5) {
            numero++;
        }
        Exemplo ex = new Exemplo(30, 60);
        System.out.println(lista + " " + numero + "\n" + ex);
        
        for(int contador = 5; contador > 0; contador--) {
            System.out.println(contador);
            lista.add(contador);
        }
        
        int count1 = 0;
        do {
            System.out.println(count1);
            count1++;
        } while(count1 >= 1 && count1 < 5);
        
        int count2 = 0;
        while(count2 >= 1 && count2 <5) {
            System.out.println(count1);
            count2++;
        }
        
        System.out.println(lista);
    }
    
    public int calculaNumero(int param) {
        return param + 10;
    }
    
}
