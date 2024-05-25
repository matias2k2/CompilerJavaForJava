/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcoes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tinar
 */
public class analizarSintaxe {

    public boolean verficarPackege(String declaracaoPackage) {
        List<String> tokens = tokenizar(declaracaoPackage);
        int posicao = 0;
        // Estrutura de decisão para verificar a declaração de pacote
        if (posicao < tokens.size() && tokens.get(posicao).equals("package")) {
            posicao++;

            // Verifica o nome do pacote
            if (posicao < tokens.size() && tokens.get(posicao).matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                posicao++;

                while (posicao < tokens.size() && tokens.get(posicao).equals(".")) {
                    posicao++;
                    if (posicao < tokens.size() && tokens.get(posicao).matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                        posicao++;
                    } else {
                        return false; // Nome do pacote inválido
                    }
                }

                // Verifica o ponto e vírgula no final
                if (posicao < tokens.size() && tokens.get(posicao).equals(";")) {
                    posicao++;
                    return posicao == tokens.size(); // Certifica que não há tokens adicionais
                } else {
                    return false; // Falta ponto e vírgula
                }
            } else {
                return false; // Nome do pacote inválido
            }
        } else {
            return false; // Não começa com 'package'
        }
    }

    //Funcao para validar o
    public boolean verficarImportacao(String importacao) {
        List<String> tokens = tokenizar(importacao);
        int posicao = 0;

        // Estrutura de decisão para verificar a importação
        // Estrutura de decisão para verificar a importação
        if (posicao < tokens.size() && tokens.get(posicao).equals("import")) {
            posicao++;

            // Verifica o nome do pacote
            if (posicao < tokens.size() && tokens.get(posicao).matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                posicao++;

                while (posicao < tokens.size() && tokens.get(posicao).equals(".")) {
                    posicao++;
                    if (posicao < tokens.size() && tokens.get(posicao).matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
                        posicao++;
                    } else {
                        return false; // Nome do pacote inválido
                    }
                }

                // Verifica se é uma classe ou wildcard
                if (posicao < tokens.size() && tokens.get(posicao).equals(".")) {
                    posicao++;
                    if (posicao < tokens.size() && (tokens.get(posicao).matches("[a-zA-Z_][a-zA-Z0-9_]*") || tokens.get(posicao).equals("*"))) {
                        posicao++;
                    } else {
                        return false; // Classe ou wildcard inválido
                    }
                }

                // Verifica o ponto e vírgula no final
                if (posicao < tokens.size() && tokens.get(posicao).equals(";")) {
                    posicao++;
                    return posicao == tokens.size(); // Certifica que não há tokens adicionais
                } else {
                    return false; // Falta ponto e vírgula
                }
            } else {
                return false; // Nome do pacote inválido
            }
        } else {
            return false; // Não começa com 'import'
        }

    }

    // Função auxiliar para tokenizar a entrada
    public List<String> tokenizar(String entrada) {

        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        int posicao = 0;

        while (posicao < entrada.length()) {
            char caractereAtual = entrada.charAt(posicao);

            if (Character.isWhitespace(caractereAtual)) {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
            } else if (caractereAtual == ';' || caractereAtual == '.' || caractereAtual == '*') {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
                tokens.add(String.valueOf(caractereAtual));
            } else {
                token.append(caractereAtual);
            }
            posicao++;
        }

        if (token.length() > 0) {
            tokens.add(token.toString());
        }

        return tokens;
    }

    // Função auxiliar para adicionar tokens à lista
    public void adiconarToken(List<String> tokens, String token) {
        if (!tokens.isEmpty()) {
            tokens.add(token);
        }
    }

    public boolean sequenciaDesconhecida(String letras) {
        return letras.matches(".*[a-z]+.*");
    }

    public boolean verificarComentario(String linha) {
        linha = linha.trim();
        return linha.startsWith("//");

    }

    public boolean verificaDeclaracao(String declaracao) {
        // Remove espaços em branco no início e no final da declaração
        declaracao = declaracao.trim();

        // Regex para verificar a declaração de variável
        String regex = "^(int|double|float|char|String|boolean|byte|short|long)\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*(=\\s*[^;]+)?;$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(declaracao);

        // Retorna true se a declaração corresponder ao padrão regex
        return matcher.matches();
    }

}
