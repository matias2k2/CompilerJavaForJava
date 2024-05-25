/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rotadoProgma;

import entity.AnalisadorSintatico;
import entity.JackTokenizer;
import funcoes.funcoesDoprojecto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tinar
 */
public class RotaDoprograma {

    public void corpoDoprograma() throws IOException {
        List<String> palavras = new ArrayList<>();
        funcoesDoprojecto funcoes = new funcoesDoprojecto();
        palavras = funcoes.lerArquivo("ficheiro/entrada.txt");

        AnalisadorSintatico analises = new AnalisadorSintatico();
        List<JackTokenizer> saida = new ArrayList<>();
        List<JackTokenizer> listToken = new ArrayList<>();
        //JackTokenizer token = null;
        analises.setTokens(palavras);
        //Corpo do nosso programa a chamada esta contercer aqui 
        try {

            //ler mostra saida de dado do fichero 
            for (int i = 0; i < palavras.size(); i++) {
                analises.analisar();
                //aqui a reparti a nossa string 
                String aux = palavras.get(i);
                saida = funcoes.identificarToken(aux, i);
                for (JackTokenizer token : saida) {
                    listToken.add(token);
                }
            }

            int cont = 0;
            // Esecver no fichero txt
            for (String mostraEsrro : analises.getErrors()) {
                funcoes.escreverArquivo("ficheiro/saida.txt", mostraEsrro, cont++);

            }
            imprimirToken(listToken);
            //fim do corpo do Porgrama 
        } catch (Exception erro) {
            System.out.println("O correu um erro na execucao do program --- do tipo :" + erro);
        }

    }

    public void imprimirToken(List<JackTokenizer> listToken) {
        System.out.println("Lexema \t\t\t\tTOKEN   \t\t\tLINHA  \t\t\t\t\tESTADO");
        System.out.println("\n\n");
        for (JackTokenizer token : listToken) {
            if (token != null) {
                // Formatação das colunas

                String lexema = token.getLexema();
                String tokenStr = token.getToken();
                String linha = Integer.toString(token.getLinha());
                int estado = token.getEstado();

                // Ajuste do espaçamento entre as colunas
                System.out.printf("%-30s %-30s %-40s %-10s\n", lexema, tokenStr, linha, estado);
            }
        }
    }

}
