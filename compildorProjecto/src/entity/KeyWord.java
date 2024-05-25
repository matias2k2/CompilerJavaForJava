/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tinar
 */
public class KeyWord {
    
      private List<String> palavrasReservadas = new ArrayList<>(Arrays.asList(
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
                "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
                "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
                "volatile", "while","main","IOException"));

    public List<String> getPalavrasReservadas() {
        return palavrasReservadas;
    }

    public void setPalavrasReservadas(List<String> palavrasReservadas) {
        this.palavrasReservadas = palavrasReservadas;
    }
        // Método para verificar se é uma palavra-chave reservada (não permitida como identificador)
    public boolean isReservedKeyword(String token) {
        // Lista de palavras-chave reservadas do Java (incompleta para o exemplo)
        String[] keywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                             "class", "const", "continue", "default", "do", "double", "else", "enum",
                             "extends", "final", "finally", "float", "for", "goto", "if", "implements",
                             "import", "instanceof", "int", "interface", "long", "native", "new",
                             "package", "private", "protected", "public", "return", "short", "static",
                             "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
                             "transient", "try", "void", "volatile", "while"};

        for (String keyword : keywords) {
            if (token.equals(keyword)) {
                return true;
            }
        }
        return false;
    }
    
    
      
}
