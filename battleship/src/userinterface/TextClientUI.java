/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import userinterface.ClientUI;
import java.util.Scanner;

/**
 *
 * @author william
 */
public class TextClientUI implements ClientUI {
    @Override
    public void output(String message){
        System.out.println(message);
    }

    /**
     *
     * @return
     */
    @Override
    public String input(){
        Scanner reader = new Scanner(System.in);
        return reader.next();
    }
}
