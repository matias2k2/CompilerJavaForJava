/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcoes;

import entity.JackTokenizer;
import entity.KeyWord;
import entity.Token;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author tinar
 */
public class funcoesDoprojecto {

    public List<String> lerArquivo(String nomeArquivo) throws IOException {
        //o daddo que serao lido do fichero vai ser adicionado aqui 
        List<String> conteudo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.add(linha);
            }
        }
        return conteudo;
    }

    public void escreverArquivo(String caminhoArquivo, String tokens,int linha) {
        File file = new File(caminhoArquivo);
        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(tokens + " erro :" +linha);
            bw.newLine();  // Adiciona uma nova linha após escrever
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//esta funcao e usado para saber se e uma string ou nao 
    public boolean verficaPalavra(String palavras) {
        if (palavras.matches("^[a-zA-Z]+$")) {
            return true;
        }
        return false;
    }

    // funcao e funcao esta a ser usada para identificar token 
    public List<JackTokenizer> identificarToken(String palavras, int linha) {
        List<JackTokenizer> saida = new ArrayList<>();
        KeyWord palavraResevada = new KeyWord();
        String lexema = "==";
        String dividir = "", encotratoken = "", aux = "";
        analizarSintaxe analise = new analizarSintaxe();
        boolean estadoVariavel;

        //Verfica se comentario
        for (int i = 0; i < palavras.length() - 1; i++) {

            if (palavras.charAt(i) != ' ') {
                dividir = dividir + palavras.charAt(i);
            } else {
                String caracter = "";
                encotratoken = dividir;
                dividir = "";
                //Analira 

                if (encotratoken.indexOf("=+") != -1) {
                    lexema = "=+";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_ATR_INCREMENTO.name(), linha, 23));
                    lexema = "";
                }
                if (encotratoken.indexOf("+") != -1) {
                    lexema = "+";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_DE_ADICAO.name(), linha, 27));
                    lexema = "";
                }
                if (encotratoken.indexOf("==") != -1) {
                    lexema = "==";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_AIGUAL.name(), linha, 13));
                    lexema = "";
                }
                if (encotratoken.indexOf("=/") != -1) {
                    lexema = "=/";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_AD.name(), linha, 56));
                    lexema = "";
                }
                if (encotratoken.indexOf("++") != -1) {
                    lexema = "++";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_ATR_INCREMENTO.name(), linha, 27));
                    lexema = "";
                }
                if (encotratoken.indexOf("=-") != -1) {
                    lexema = "=-";
                    saida.add(new JackTokenizer(aux, Token.OPERADOR_ATRSUBTRACAO.name(), linha, 56));
                    lexema = "";
                }
                if (encotratoken.indexOf(";") != -1) {
                    String letra = ";";
                    saida.add(new JackTokenizer(letra, Token.PONTO_VG.name(), linha, 49));
                }
                if (encotratoken.indexOf(".") != -1) {
                    lexema = ".";
                    saida.add(new JackTokenizer(lexema, Token.PONTO.name(), linha, 21));
                    lexema = "";
                }
                if (encotratoken.indexOf("=>") != -1) {
                    lexema = "=>";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_MAIOR.name(), linha, 23));
                    lexema = "";
                }
                if (encotratoken.indexOf("=<") != -1) {
                    lexema = "=<";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_AMENOR.name(), linha, 21));
                    lexema = "";
                }
                if (encotratoken.indexOf("#") != -1) {
                    lexema = "#";
                    saida.add(new JackTokenizer(lexema, Token.CARDINAL.name(), linha, 20));
                    lexema = "";
                }
                if (encotratoken.indexOf("*") != -1) {
                    lexema = "*";
                    saida.add(new JackTokenizer(lexema, Token.ASTERISCO.name(), linha, 23));
                    lexema = "";
                }
                if (encotratoken.indexOf("()") != -1) {
                    lexema = "()";
                    saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES.name(), linha, 24));
                    lexema = "";
                }
                if (encotratoken.indexOf("(") != -1) {
                    lexema = "(";
                    saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 23));
                    lexema = "";
                }
                if (encotratoken.indexOf(")") != -1) {
                    lexema = ")";
                    saida.add(new JackTokenizer(lexema, Token.FECHA_CHAVES.name(), linha, 45));
                    lexema = "";
                }
                if (encotratoken.indexOf("{") != -1) {
                    lexema = "{";
                    saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 27));
                    lexema = "";
                }
                if (encotratoken.indexOf("}") != -1) {
                    lexema = "}";
                    saida.add(new JackTokenizer(lexema, Token.FECHA_PARENTESES.name(), linha, 27));
                    lexema = "";
                }
                if (encotratoken.indexOf("{}") != -1) {
                    lexema = "{";
                    saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES_RETOS.name(), linha, 37));
                    lexema = "";
                }
                if (encotratoken.indexOf("\r") != -1) {
                    lexema = "\r";
                    saida.add(new JackTokenizer(lexema, Token.NOVA_LINHA.name(), linha, 61));
                    lexema = "";
                }
                if (encotratoken.indexOf("\t") != -1) {
                    lexema = "\t";
                    saida.add(new JackTokenizer(lexema, Token.TABULACAO.name(), linha, 60));
                    lexema = "";
                }
                if (encotratoken.indexOf("\f") != -1) {
                    lexema = "\f";
                    saida.add(new JackTokenizer(lexema, Token.ALIMENTACAO.name(), linha, 62));
                    lexema = "";
                }
                if (encotratoken.indexOf("\n") != -1) {
                    lexema = "\n";
                    saida.add(new JackTokenizer(lexema, Token.NOVA_LINHA.name(), linha, 61));
                    lexema = "";
                }
                if (encotratoken.indexOf("<=") != -1) {
                    lexema = "<=";
                    saida.add(new JackTokenizer(lexema, Token.OPERADO_MAIOR_IGUAL.name(), linha, 61));
                    lexema = "";
                }
                if (encotratoken.indexOf("!=") != -1) {
                    lexema = "!=";
                    saida.add(new JackTokenizer(lexema, Token.OPERADOR_AD.name(), linha, 61));
                    lexema = "";
                }

                if (encotratoken.indexOf("[]") != -1) {
                    lexema = "[]";
                    saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES.name(), linha, 24));
                    lexema = "";
                }
                if (encotratoken.indexOf("[") != -1) {
                    lexema = "[";
                    saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 24));
                    lexema = "";
                }
                if (encotratoken.indexOf("]") != -1) {

                    lexema = "]";
                    saida.add(new JackTokenizer(lexema, Token.FECHA_CHAVES.name(), linha, 45));
                    lexema = "";

                }

                if (verficaPalavra(encotratoken)) {
                    if (palavraResevada.getPalavrasReservadas().contains(encotratoken)) {
                        saida.add(new JackTokenizer(encotratoken, Token.PALAVRA_RESERVADA.name(), linha, 134));

                        encotratoken = "";
                    } else if (encotratoken.matches("^[a-zA-Z]+$")) {
                        //a palavra possui a apenas 1..2 letras
                        if (encotratoken.length() <= 2 || encotratoken.length() == 1) {
                            saida.add(new JackTokenizer(encotratoken, Token.Letra.name(), linha, 1));
                            encotratoken = "";
                        } else {
                            
                            saida.add(new JackTokenizer(encotratoken, Token.STRING.name(), linha, 1));

                            encotratoken = "";
                            //Indetificador desconhecido

                        }

                        //Indetificador desconhecido
                    }

                } else if (false == verficaPalavra(encotratoken)) {
                    for (int j = 0; j < encotratoken.length(); j++) {
                        char currentChar = encotratoken.charAt(j);
                        if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')) {
                            caracter += currentChar;
                            // Verificar se há um próximo caractere
                            if (j + 1 < encotratoken.length()) {
                                char nextChar = encotratoken.charAt(j + 1);
                                if ((nextChar >= 'a' && nextChar <= 'z') || (nextChar >= 'A' && nextChar <= 'Z')) {
                                    // Se o próximo caractere também for uma letra, continue concatenando
                                    caracter += nextChar;
                                } else {
                                    // Se o próximo caractere não for uma letra, adicione o grupo atual à saída
                                    saida.add(new JackTokenizer(caracter, Token.STRING.name(), linha, 1));
                                    // Limpe o grupo para a próxima iteração
                                    caracter = "";
                                }
                            } else {
                                // Se chegamos ao final da string, adicione o grupo atual à saída
                                saida.add(new JackTokenizer(caracter, Token.STRING.name(), linha, 1));
                                caracter = "";
                                
                            }

                        } else {
                            // Se o caractere não for uma letra, adicione-o à saída como um número
                            saida.add(new JackTokenizer(String.valueOf(currentChar), Token.INTEIRO.name(), linha, 2));
                            caracter = "";
                        }
                    }
                }
                if (encotratoken.matches("[0-9]+")) {
                    saida.add(new JackTokenizer(encotratoken, Token.INTEIRO.name(), linha, 2));
                }
            }

        }
        // Processar a última palavra, se houver
        if (!dividir.isEmpty()) {
            String caracter = "";
            encotratoken = dividir;
            dividir = "";

            if (encotratoken.indexOf("=+") != -1) {
                lexema = "=+";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_ATR_INCREMENTO.name(), linha, 23));
                lexema = "";
            }
            if (encotratoken.indexOf("+") != -1) {
                lexema = "+";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_DE_ADICAO.name(), linha, 27));
                lexema = "";
            }
            if (encotratoken.indexOf("==") != -1) {
                lexema = "==";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_AIGUAL.name(), linha, 13));
                lexema = "";
            }
            if (encotratoken.indexOf("=/") != -1) {
                lexema = "=/";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_AD.name(), linha, 56));
                lexema = "";
            }
            if (encotratoken.indexOf("++") != -1) {
                lexema = "++";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_ATR_INCREMENTO.name(), linha, 27));
                lexema = "";
            }
            if (encotratoken.indexOf("=-") != -1) {
                lexema = "=-";
                saida.add(new JackTokenizer(aux, Token.OPERADOR_ATRSUBTRACAO.name(), linha, 56));
                lexema = "";
            }
            if (encotratoken.indexOf(";") != -1) {
                String letra = ";";
                saida.add(new JackTokenizer(letra, Token.PONTO_VG.name(), linha, 49));
            }
            if (encotratoken.indexOf(".") != -1) {
                lexema = ".";
                saida.add(new JackTokenizer(lexema, Token.PONTO.name(), linha, 21));
                lexema = "";
            }
            if (encotratoken.indexOf("=>") != -1) {
                lexema = "=>";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_MAIOR.name(), linha, 23));
                lexema = "";
            }
            if (encotratoken.indexOf("=<") != -1) {
                lexema = "=<";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_AMENOR.name(), linha, 21));
                lexema = "";
            }
            if (encotratoken.indexOf("#") != -1) {
                lexema = "#";
                saida.add(new JackTokenizer(lexema, Token.CARDINAL.name(), linha, 20));
                lexema = "";
            }
            if (encotratoken.indexOf("*") != -1) {
                lexema = "*";
                saida.add(new JackTokenizer(lexema, Token.ASTERISCO.name(), linha, 23));
                lexema = "";
            }
            if (encotratoken.indexOf("()") != -1) {
                lexema = "()";
                saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES.name(), linha, 24));
                lexema = "";
            }
            if (encotratoken.indexOf("(") != -1) {
                lexema = "(";
                saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 23));
                lexema = "";
            }
            if (encotratoken.indexOf(")") != -1) {
                lexema = ")";
                saida.add(new JackTokenizer(lexema, Token.FECHA_CHAVES.name(), linha, 45));
                lexema = "";
            }
            if (encotratoken.indexOf("{") != -1) {
                lexema = "{";
                saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 27));
                lexema = "";
            }
            if (encotratoken.indexOf("}") != -1) {
                lexema = "}";
                saida.add(new JackTokenizer(lexema, Token.FECHA_PARENTESES.name(), linha, 27));
                lexema = "";
            }
            if (encotratoken.indexOf("{}") != -1) {
                lexema = "{";
                saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES_RETOS.name(), linha, 37));
                lexema = "";
            }
            if (encotratoken.indexOf("\r") != -1) {
                lexema = "\r";
                saida.add(new JackTokenizer(lexema, Token.NOVA_LINHA.name(), linha, 61));
                lexema = "";
            }
            if (encotratoken.indexOf("\t") != -1) {
                lexema = "\t";
                saida.add(new JackTokenizer(lexema, Token.TABULACAO.name(), linha, 60));
                lexema = "";
            }
            if (encotratoken.indexOf("\f") != -1) {
                lexema = "\f";
                saida.add(new JackTokenizer(lexema, Token.ALIMENTACAO.name(), linha, 62));
                lexema = "";
            }
            if (encotratoken.indexOf("\n") != -1) {
                lexema = "\n";
                saida.add(new JackTokenizer(lexema, Token.NOVA_LINHA.name(), linha, 61));
                lexema = "";
            }
            if (encotratoken.indexOf("<=") != -1) {
                lexema = "<=";
                saida.add(new JackTokenizer(lexema, Token.OPERADO_MAIOR_IGUAL.name(), linha, 61));
                lexema = "";
            }
            if (encotratoken.indexOf("!=") != -1) {
                lexema = "!=";
                saida.add(new JackTokenizer(lexema, Token.OPERADOR_AD.name(), linha, 61));
                lexema = "";
            }

            if (encotratoken.indexOf("[]") != -1) {
                lexema = "[]";
                saida.add(new JackTokenizer(lexema, Token.CONJUNTO_DE_PARENTES.name(), linha, 24));
                lexema = "";
            }
            if (encotratoken.indexOf("[") != -1) {
                lexema = "[";
                saida.add(new JackTokenizer(lexema, Token.ABRE_PARENTESES.name(), linha, 24));
                lexema = "";
            }
            if (encotratoken.indexOf("]") != -1) {

                lexema = "]";
                saida.add(new JackTokenizer(lexema, Token.FECHA_CHAVES.name(), linha, 45));
                lexema = "";

            }

            if (verficaPalavra(encotratoken)) {
                if (palavraResevada.getPalavrasReservadas().contains(encotratoken)) {
                    saida.add(new JackTokenizer(encotratoken, Token.PALAVRA_RESERVADA.name(), linha, 134));
                   

                } else if (encotratoken.matches("^[a-zA-Z]+$")) {
                    //a palavra possui a apenas 1..2 letras
                    if (encotratoken.length() <= 2 || encotratoken.length() == 1) {
                        saida.add(new JackTokenizer(encotratoken, Token.Letra.name(), linha, 1));
                    } else {
                        saida.add(new JackTokenizer(encotratoken, Token.STRING.name(), linha, 1));
                    }
                }
            } else if (false == verficaPalavra(encotratoken)) {
                for (int j = 0; j < encotratoken.length(); j++) {
                    char currentChar = encotratoken.charAt(j);
                    if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z')) {
                        caracter += currentChar;
                        // Verificar se há um próximo caractere
                        if (j + 1 < encotratoken.length()) {
                            char nextChar = encotratoken.charAt(j + 1);
                            if ((nextChar >= 'a' && nextChar <= 'z') || (nextChar >= 'A' && nextChar <= 'Z')) {
                                // Se o próximo caractere também for uma letra, continue concatenando
                                caracter += nextChar;
                            } else {
                                // Se o próximo caractere não for uma letra, adicione o grupo atual à saída
                                saida.add(new JackTokenizer(caracter, Token.STRING.name(), linha, 1));
                                // Limpe o grupo para a próxima iteração
                                caracter = "";
                            }
                        } else {
                            // Se chegamos ao final da string, adicione o grupo atual à saída
                            saida.add(new JackTokenizer(caracter, Token.STRING.name(), linha, 1));
                            caracter = "";
                        }
                    } else {
                        // Se o caractere não for uma letra, adicione-o à saída como um número
                        saida.add(new JackTokenizer(String.valueOf(currentChar), Token.INTEIRO.name(), linha, 2));
                        caracter = "";
                    }
                }

            }
        }

        return saida;
    }

    public void processarPalavra(String palavra, List<JackTokenizer> saida, int linha, KeyWord palavraResevada) {
        String encotratoken = palavra;
        if (verficaPalavra(encotratoken)) {
            if (palavraResevada.getPalavrasReservadas().contains(encotratoken)) {
                saida.add(new JackTokenizer(encotratoken, Token.PALAVRA_RESERVADA.name(), linha, 134));
            } else if (encotratoken.matches("^[a-zA-Z]+$")) {
                //a palavra possui a apenas 1..2 letras
                if (encotratoken.length() <= 2 || encotratoken.length() == 1) {
                    saida.add(new JackTokenizer(encotratoken, Token.Letra.name(), linha, 1));
                } else {
                    saida.add(new JackTokenizer(encotratoken, Token.STRING.name(), linha, 1));
                }
            }
        }
    }

    public String encontraSimbolos(String palavras) {
        if (palavras.indexOf("=+") != -1) {
            return "=+";
        }
        if (palavras.indexOf("+") != -1) {
            return "+";
        }
        if (palavras.indexOf("==") != -1) {
            return "==";
        }
        if (palavras.indexOf("=/") != -1) {
            return "=/";
        }
        if (palavras.indexOf("++") != -1) {
            return "++";
        }
        if (palavras.indexOf("--") != -1) {
            return "--";
        }
        if (palavras.indexOf(";") != -1) {
            return ";";
        }
        if (palavras.indexOf(".") != -1) {
            return ".";
        }
        if (palavras.indexOf("=>") != -1) {
            return "=>";
        }
        if (palavras.indexOf(">=") != -1) {
            return ">=";
        }
        if (palavras.indexOf("#") != -1) {
            return "#";
        }
        if (palavras.indexOf("*") != -1) {
            return "*";
        }
        if (palavras.indexOf("()") != -1) {
            return "()";
        }
        if (palavras.indexOf("(") != -1) {
            return "(";
        }
        if (palavras.indexOf(")") != -1) {
            return ")";
        }
        if (palavras.indexOf("{") != -1) {
            return "{";
        }
        if (palavras.indexOf("}") != -1) {
            return "}";
        }
        if (palavras.indexOf("{}") != -1) {
            return "{}";
        }
        if (palavras.indexOf("\r") != -1) {
            return "\r";
        }
        if (palavras.indexOf("\t") != -1) {
            return "\t";
        }
        if (palavras.indexOf("\f") != -1) {
            return "\f";
        }
        if (palavras.indexOf("\n") != -1) {
            return "\n";
        }
        if (palavras.indexOf("<=") != -1) {
            return "<=";
        }
        if (palavras.indexOf("!=") != -1) {
            return "!=";
        }
        if (palavras.indexOf("&&") != -1) {
            return "&&";
        }
        if (palavras.indexOf("[]") != -1) {
            return "[]";
        }
        if (palavras.indexOf("[") != -1) {
            return "[";
        }
        if (palavras.indexOf("]") != -1) {
            return "]";
        }
        return "";
    }

}
