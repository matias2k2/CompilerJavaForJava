/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import funcoes.analizarSintaxe;
import funcoes.funcoesDoprojecto;
import java.io.IOException;
import java.util.List;
import rotadoProgma.RotaDoprograma;

/**
 *
 * @author tinar
 */
public class mainTeste {

    /**
     * @param args the command line arguments
     */
    
    public boolean sequenciaDesconhecida(String letras) {
        return letras.matches(".*[a-z]+.*");
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        analizarSintaxe teste = new analizarSintaxe();
        RotaDoprograma run1 = new RotaDoprograma();
        funcoesDoprojecto run = new funcoesDoprojecto();

        run1.corpoDoprograma();
        String matias = "matas11";
        System.out.println("Teste rapido " + matias.matches("[a-z,A-Z]"));
     

    }

}
