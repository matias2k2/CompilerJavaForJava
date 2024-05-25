/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author tinar
 */
public class JackTokenizer {
    
    private String lexema;
    private String token;
    
    private int linha;
    private int estado;

    public JackTokenizer() {
    }

    public String getLexema() {
        return this.lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

  
    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return   lexema + "\t\t\t\t|" + token+ "\t\t\t\t\t\t " + linha + "|\t" + estado ;
    }

    public JackTokenizer(String lexema, String token, int linha, int estado) {
        this.lexema = lexema;
        this.token = token;
        this.linha = linha;
        this.estado = estado;
    }
    

    
    
    
    
}
