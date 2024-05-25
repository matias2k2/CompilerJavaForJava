/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import funcoes.analizarSintaxe;
import funcoes.funcoesDoprojecto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tinar
 */
public class AnalisadorSintatico {

    private List<String> tokens;
    private int posicao;
    private List<String> errors = new ArrayList<>();

    private KeyWord KeyWord;

    public KeyWord getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(KeyWord KeyWord) {
        this.KeyWord = KeyWord;
    }

    analizarSintaxe analisar = new analizarSintaxe();
    funcoesDoprojecto ler = new funcoesDoprojecto();

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public int getPosicao() {
        return posicao;
    }

    public AnalisadorSintatico() {
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    // Funcao para analizar o nosso programa
    public boolean analisar() {
        String[] tokens;
        int pos = 0;
        int inicio = 0, linha = 0;
        boolean controladoDeEstado = false;
        int cont = 0;
        int variavleDeStado = 0, variavleDeStado1 = 3;
        boolean variadeVerificacao = false;

        try {

            for (String tokenString : this.tokens) {

                //tokens = tokenString.split("\\s+");
                if (tokenString.startsWith("import") || tokenString.startsWith("package")) {
                    if (isTokenValido(tokenString, linha)) {
                        inicio = 1;
                    } else {
                        errors.add("Erro: Verficar a importacao  " + (linha));
                    }
                }
                if (isIdentifier(tokenString)) {
                    errors.add(" Erro : verifica a declaracao  ");
                }
                if (analisar.verificarComentario(tokenString)) {
                    continue;
                }
                if (inicio == 1) {
                    if (verificarDeclaracaoClasse(tokenString)) {
                        variavleDeStado = 2;
                        variadeVerificacao = true;
                    }
                    if (isDelimiter(tokenString)) {
                        variadeVerificacao = false;
                    }
                    if (variavleDeStado == 2) {

                        if (verificarDeclaracaoClasse(tokenString)) {
                            variavleDeStado = 2;
                            variadeVerificacao = true;
                        }
                        if (isIdentifier(tokenString)) {
                            errors.add(" Erro : verifica a declaracao  ");
                        }

                        if (true == isDelimiter(tokenString)) {
                            variavleDeStado = 3;
                            if (controladoDeEstado == true) {
                                controladoDeEstado = false;
                            } else {
                                controladoDeEstado = true;
                            }
                            continue;
                        }
                        tokenString = tokenString.trim();

                        if (tokenString.startsWith("int")
                                || tokenString.startsWith("double")
                                || tokenString.startsWith("float")
                                || tokenString.startsWith("char")
                                || tokenString.startsWith("boolean")
                                || tokenString.startsWith("String")
                                || tokenString.startsWith("public")
                                || tokenString.startsWith("private")
                                || tokenString.startsWith("protected")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarDeclaracaoVariavel(tokenString)) {

                                continue;
                            } else if (tokenString.contains("static")) {
                                if (verificarMainMethod(tokenString)) {
                                    variadeVerificacao = true;
                                    continue;
                                }
                            }
                        }
                        if (tokenString.startsWith("for")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarForStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (tokenString.startsWith("while")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarWhileStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (tokenString.startsWith("if")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarIfStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (analisar.verificarComentario(tokenString)) {
                            continue;
                        }
                        if (verificarChamadaMetodo(tokenString)) {
                            continue;
                        }
                        if (tokenString.contains("public static")
                                || tokenString.contains("private static")
                                || tokenString.contains("protected static")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (verificarMainMethod(tokenString)) {
                                controladoDeEstado = true;
                                variavleDeStado1 = 3;
                                continue;
                            }
                        }
                    }
                    
                    if(variavleDeStado1 ==3)
                    {
                        
                        if (verificarDeclaracaoClasse(tokenString)) {
                            variavleDeStado = 2;
                            variadeVerificacao = true;
                        }
                        if (isIdentifier(tokenString)) {
                            errors.add(" Erro : verifica a declaracao  ");
                        }

                        if (true == isDelimiter(tokenString)) {
                            variavleDeStado = 3;
                            if (controladoDeEstado == true) {
                                controladoDeEstado = false;
                            } else {
                                controladoDeEstado = true;
                            }
                            continue;
                        }
                        tokenString = tokenString.trim();

                        if (tokenString.startsWith("int")
                                || tokenString.startsWith("double")
                                || tokenString.startsWith("float")
                                || tokenString.startsWith("char")
                                || tokenString.startsWith("boolean")
                                || tokenString.startsWith("String")
                                || tokenString.startsWith("public")
                                || tokenString.startsWith("private")
                                || tokenString.startsWith("protected")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarDeclaracaoVariavel(tokenString)) {

                                continue;
                            } else if (tokenString.contains("static")) {
                                if (verificarMainMethod(tokenString)) {
                                    variadeVerificacao = true;
                                    continue;
                                }
                            }
                        }
                        if (tokenString.startsWith("for")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarForStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (tokenString.startsWith("while")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarWhileStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (tokenString.startsWith("if")) {
                            // Código a ser executado se uma das condições for verdadeira
                            if (true == verificarIfStatement(tokenString)) {
                                controladoDeEstado = true;
                                continue;
                            }
                        }
                        if (analisar.verificarComentario(tokenString)) {
                            continue;
                        }
                        if (verificarChamadaMetodo(tokenString)) {
                            continue;
                        }
                        
                    }

                } else {
                    errors.add("Vefiaca es estrutura do codigo java  " + (linha));
                }

                linha++;
            }
            if (inicio == 0) {
                errors.add("Erro: Verficar a importacao o caminho da packge ");
            }
            if (controladoDeEstado == false) {
                errors.add("Verfica os Feichmentos das chaves se estao correto ");
            }
            if (variadeVerificacao == true) {
                errors.add(" O seu codigo se encotra em um loop verfica os feichamento do Do while " + controladoDeEstado);
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return true;
    }

    // Método para verificar se o token é válido (exemplo simplificado)
    public boolean isTokenValido(String token, int linha) {

        if (true == token.startsWith("import")) {
            if (analisar.verficarImportacao(token)) {
                return true;
            } else {
                ler.escreverArquivo("ficheiro/saida.txt", token, linha);
                return false;
            }
        } else if (true == token.startsWith("package")) {
            if (true == token.startsWith("package")) {
                return true;
            } else {
                ler.escreverArquivo("ficheiro/saida.txt", token, linha);
                return false;
            }
        }

        return false;
    }
    // Método para encontrar o próximo ponto de sincronização

    public boolean classDeclaration(String token) {
        token = token.trim(); // Remove leading and trailing whitespace
        if (token.startsWith("public") || token.startsWith("private") || token.startsWith("protected")
                || token.startsWith("class") || token.startsWith("interface") || token.startsWith("enum")
                || token.startsWith("abstract") || token.startsWith("final")) {
            // Verifica se a string contém a palavra-chave 'class', 'interface' ou 'enum' após os modificadores de acesso
            return token.contains(" class ") || token.contains(" interface ") || token.contains(" enum ");
        }
        return false;
    }

    public boolean identifier(String token) {
        // Verifica se o token é um identificador válido (aqui simplificado para letras e números)
        return token.matches("[a-zA-Z][a-zA-Z0-9]*");
    }
    // Método para verificar modificador de acesso

    public boolean isAccessModifier(String token) {
        return token.equals("public") || token.equals("private") || token.equals("protected");
    }

    public boolean primitiveType(String palavras) {
        if (palavras.equals("byte")
                || palavras.equals("short")
                || palavras.equals("int")
                || palavras.equals("long")
                || palavras.equals("float")
                || palavras.equals("double")
                || palavras.equals("boolean")
                || palavras.equals("char")) {
            return true;
        }
        return false;
    }

    // Método para verificar delimitador válido
    private boolean isDelimiter(String token) {
        return token.equals("{") || token.equals("}") || token.equals(";");
    }

    // Verifica se o token é um modificador
    public boolean isModifier(String token) {
        return token.equals("static") || token.equals("abstract") || token.equals("final");
    }

    // Método para verificar a declaração
    public boolean verificarDeclaracao(String declaracao) {
        String[] tokens = declaracao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é um modificador de acesso (opcional)
        if (pos < tokens.length && isAccessModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um modificador (opcional)
        if (pos < tokens.length && isModifier(tokens[pos])) {
            pos++;
        }
        // Verifica se o próximo token é um identificador
        if (pos < tokens.length && isIdentifier(tokens[pos])) {
            pos++;
        } else {
            errors.add("Erro: Esperado identificador em " + (pos + 1));
            return false;
        }

        // Verifica se há mais tokens (delimitadores opcionais)
        while (pos < tokens.length) {
            pos++;
            if (isDelimiter(tokens[pos])) {
                pos++;
                return true;
            } else {
                System.out.println("" + tokens[pos]);
                errors.add("Erro: Token inesperado '" + tokens[pos] + "' em " + (pos + 1));
                pos++;

            }
        }

        return true;
    }

    // Método para verificar tipo primitivo
    public boolean isPrimitiveType(String token) {
        return token.equals("byte") || token.equals("short") || token.equals("int")
                || token.equals("long") || token.equals("float") || token.equals("double")
                || token.equals("boolean") || token.equals("char");
    }

    // Método para verificar identificador válido
    public boolean isIdentifier(String token) {
        return token.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    // Método para verificar se um valor é válido (simplificado)
    public boolean isValidValue(String token) {
        // Verifica se o token é um número ou uma string válida (apenas exemplos simples)
        return token.matches("\\d+") || token.matches("\"[^\"]*\"") || token.matches("true|false") || token.matches("'[a-zA-Z]'");
    }

    public boolean verificarDeclaracaoVariavel(String declaracao) {
        errors.clear();
        String[] tokens = declaracao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é um modificador de acesso (opcional)
        if (pos < tokens.length && isAccessModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um modificador (opcional)
        while (pos < tokens.length && isModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um tipo primitivo ou referência de objeto
        if (pos < tokens.length && (isPrimitiveType(tokens[pos]) || isIdentifier(tokens[pos]))) {
            pos++;
        }
        // Verifica se o próximo token é um identificador válido 
        if (pos < tokens.length && isIdentifier(tokens[pos]) && !isReservedKeyword(tokens[pos])) {
            pos++;
        } else {
            //errors.add("Erro: Esperado identificador válido em " + (pos + 1));
            return false;
        }

        // Verifica se há inicialização (opcional)
        if (pos < tokens.length && tokens[pos].equals("=")) {
            pos++;
            if (pos < tokens.length && isValidValue(tokens[pos])) {
                pos++;
            } else {
                //errors.add("Erro: Valor de inicialização inválido em " + (pos + 1));
                return false;
            }
        }

        // Verifica se a declaração termina com ";"
        if (pos < tokens.length && tokens[pos].equals(";")) {
            pos++;
        } else {
            errors.add("Erro: Declaração de variável deve terminar com ';' em " + (pos + 1));
            return false;
        }

        // Verifica se há tokens adicionais (não permitidos)
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o final da declaração em " + (pos + 1));
            return false;
        }

        return true;
    }

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
    // Método para verificar a declaração de matriz

    public boolean verificarDeclaracaoMatriz(String declaracao) {

        String[] tokens = declaracao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é um modificador de acesso (opcional)
        if (pos < tokens.length && isAccessModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um modificador (opcional)
        while (pos < tokens.length && isModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um tipo primitivo ou referência de objeto
        if (pos < tokens.length && (isPrimitiveType(tokens[pos]) || isIdentifier(tokens[pos]))) {
            pos++;
        } else {
            errors.add("Erro: Esperado tipo primitivo ou referência de objeto em  na sua matriz " + (pos + 1));
            return false;
        }

        // Verifica se o próximo token é um identificador válido
        if (pos < tokens.length && isIdentifier(tokens[pos]) && !isReservedKeyword(tokens[pos])) {
            pos++;
        } else {
            errors.add("Erro: Esperado identificador válido em " + (pos + 1));
            return false;
        }

        // Verifica se o próximo token é um colchete de abertura '[' para matriz
        if (pos < tokens.length && tokens[pos].equals("[")) {
            pos++;
            if (pos < tokens.length && tokens[pos].equals("]")) {
                pos++;
            } else {
                errors.add("Erro: Esperado ']' em " + (pos + 1));
                return false;
            }
        } else {
            errors.add("Erro: Esperado '[' para declarar matriz em " + (pos + 1));
            return false;
        }

        // Verifica se há mais colchetes de abertura '[' para matrizes multidimensionais
        while (pos < tokens.length && tokens[pos].equals("[")) {
            pos++;
            if (pos < tokens.length && tokens[pos].equals("]")) {
                pos++;
            } else {
                errors.add("Erro: Esperado ']' para matriz multidimensional em " + (pos + 1));
                return false;
            }
        }

        // Verifica se há inicialização (opcional)
        if (pos < tokens.length && tokens[pos].equals("=")) {
            pos++;
            if (pos < tokens.length && (tokens[pos].equals("{") || isValidValue(tokens[pos]))) {
                // Se for inicialização com valores explícitos, deve terminar com '}'
                if (tokens[pos].equals("{")) {
                    while (pos < tokens.length && !tokens[pos].equals("}")) {
                        pos++;
                    }
                    if (pos < tokens.length && tokens[pos].equals("}")) {
                        pos++;
                    } else {
                        errors.add("Erro: Esperado '}' para inicialização de matriz em " + (pos + 1));
                        return false;
                    }
                } else {
                    pos++;
                }
            } else {
                errors.add("Erro: Valor de inicialização inválido em " + (pos + 1));
                return false;
            }
        }

        // Verifica se a declaração termina com ";"
        if (pos < tokens.length && tokens[pos].equals(";")) {
            pos++;
        } else {
            errors.add("Erro: Declaração de variável deve terminar com ';' em " + (pos + 1));
            return false;
        }

        // Verifica se há tokens adicionais (não permitidos)
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o final da declaração em " + (pos + 1));
            return false;
        }

        return true;
    }

    //E aqui onde eu para
    // Método para verificar a sintaxe de uma instrução while
    public boolean verificarWhileStatement(String instrucao) {

        String[] tokens = instrucao.trim().split("\\s+");
        int pos = 0;

        // Verifica se a primeira palavra-chave é "while"
        if (pos < tokens.length && tokens[pos].equals("while")) {
            pos++;
        } else {
            errors.add("Erro: Instrução while deve começar com 'while'");
            return false;
        }

        // Verifica se há um parêntese de abertura após "while"
        if (pos < tokens.length && tokens[pos].equals("(")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente após 'while'");
            return false;
        }

        // Verifica a expressão dentro dos parênteses
        pos = verificarExpressao(pos, tokens);

        // Verifica se há um parêntese de fechamento após a expressão
        if (pos < tokens.length && tokens[pos].equals(")")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente após a expressão");
            return false;
        }

        // Verifica o corpo do while
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma chave de abertura
        if (pos < tokens.length && tokens[pos].equals("{")) {
            pos++;
            return true;
        } else {
            errors.add("Erro: Chave de abertura ausente para o corpo do while");

        }
        // Verifica se há tokens adicionais após a chave de fechamento do while
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o corpo do while");
            return false;
        }
        return true;
    }

// Método para verificar a sintaxe de uma expressão dentro dos parênteses do while
    private int verificarExpressao(int pos, String[] tokens) {
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada ou um identificador
        while (pos < tokens.length && !tokens[pos].equals(")")) {
            if (isReservedKeyword(tokens[pos]) || isIdentifier(tokens[pos])) {
                pos++;
            } else {
                errors.add("Erro: Expressão inválida no while");
                return pos;
            }
        }
        return pos;
    }

// Método para verificar a sintaxe de um método main
    public boolean verificarMainMethod(String metodo) {

        String[] tokens = metodo.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é "public"
        if (pos < tokens.length && tokens[pos].equals("public")) {
            pos++;
        } else {
            errors.add("Erro: Método main deve ser público (public)");
            return false;
        }

        // Verifica se o próximo token é "static"
        if (pos < tokens.length && tokens[pos].equals("static")) {
            pos++;
        } else {
            errors.add("Erro: Método main deve ser estático (static)");
            return false;
        }

        // Verifica se o próximo token é "void"
        if (pos < tokens.length && tokens[pos].equals("void")) {
            pos++;
        } else {
            errors.add("Erro: Método main não deve retornar valor (void)");
            return false;
        }

        // Verifica se o próximo token é "main"
        if (pos < tokens.length && tokens[pos].equals("main")) {
            pos++;
        } else {
            errors.add("Erro: Método main deve se chamar 'main'");
            return false;
        }

        // Verifica se há um parêntese de abertura após "main"
        if (pos < tokens.length && tokens[pos].equals("(")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente após 'main'");
            return false;
        }

        // Verifica se há um parêntese de fechamento após o array de argumentos
        if (pos < tokens.length && tokens[pos].equals("String")) {
            pos++;
        } else {
            errors.add("Erro: Tipo de argumento inválido para o método main");
            return false;
        }

        if (pos < tokens.length && tokens[pos].equals("[")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente após o tipo de argumento");
            return false;
        }

        if (pos < tokens.length && tokens[pos].equals("]")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente após o tipo de argumento");
            return false;
        }

        // Verifica se há um parêntese de fechamento após o array de argumentos
        if (pos < tokens.length && tokens[pos].equals("args")) {
            pos++;
        } else {
            errors.add("Erro: Nome de argumento inválido para o método main");
            return false;
        }

        if (pos < tokens.length && tokens[pos].equals(")")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente após o array de argumentos");
            return false;
        }

        // Verifica o corpo do método main
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma chave de abertura
        if (pos < tokens.length && tokens[pos].equals("{")) {
            pos++;
            return true;
        } else {
            errors.add("Erro: Chave de abertura ausente para o corpo do método main");

        }
        // Verifica se há tokens adicionais após a chave de fechamento do método main
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o corpo do método main");
            return false;
        }

        return true;
    }

// Método para verificar a sintaxe de uma instrução for
    public boolean verificarForStatement(String instrucao) {

        String[] tokens = instrucao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é "for"
        if (pos < tokens.length && tokens[pos].equals("for")) {
            pos++;
        } else {
            errors.add("Erro: Instrução for deve começar com 'for'");
            return false;
        }

        // Verifica se há um parêntese de abertura após "for"
        if (pos < tokens.length && tokens[pos].equals("(")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente após 'for'");
            return false;
        }

        // Verifica a inicialização do for
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada ou um identificador
        pos = verificarExpressaos(pos, tokens);

        // Verifica se há um ponto e vírgula após a inicialização
        if (pos < tokens.length && tokens[pos].equals(";")) {
            pos++;
        } else {
            errors.add("Erro: Ponto e vírgula ausente após a inicialização do for");
            return false;
        }

        // Verifica a condição do for
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada, um identificador ou uma constante
        pos = verificarExpressaos(pos, tokens);

        // Verifica se há um ponto e vírgula após a condição
        if (pos < tokens.length && tokens[pos].equals(";")) {
            pos++;
        } else {
            errors.add("Erro: Ponto e vírgula ausente após a condição do for");
            return false;
        }

        // Verifica o incremento do for
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada, um identificador ou uma constante
        pos = verificarExpressaos(pos, tokens);

        // Verifica se há um parêntese de fechamento após o incremento
        if (pos < tokens.length && tokens[pos].equals(")")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente após o incremento do for");
            return false;
        }

        // Verifica o corpo do for
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma chave de abertura
        if (pos < tokens.length && tokens[pos].equals("{")) {
            pos++;
            return true;
        } else {
            errors.add("Erro: Chave de abertura ausente para o corpo do for");

        }

        // Verifica se há tokens adicionais após a chave de fechamento do corpo do for
        while (pos < tokens.length && !tokens[pos].equals("}")) {
            pos++;
            return true;
        }

        // Verifica se há uma chave de fechamento após o corpo do for
        if (pos < tokens.length && tokens[pos].equals("}")) {
            pos++;
        } else {
            errors.add("Erro: Chave de fechamento ausente após o corpo do for");
            return false;
        }

        // Verifica se há tokens adicionais após a chave de fechamento do for
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o corpo do for");
            return false;
        }

        return true;
    }

// Método para verificar a sintaxe de uma expressão dentro dos parênteses do for
    public int verificarExpressaos(int pos, String[] tokens) {
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada, um identificador ou uma constante
        while (pos < tokens.length && !tokens[pos].equals(";")) {
            if (isReservedKeyword(tokens[pos]) || isIdentifier(tokens[pos])) {
                pos++;
            } else {
                errors.add("Erro: Expressão inválida no for");
                return pos;
            }
        }
        return pos;
    }
// Método para verificar a sintaxe de uma instrução if

    public boolean verificarIfStatement(String instrucao) {

        String[] tokens = instrucao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é "if"
        if (pos < tokens.length && tokens[pos].equals("if")) {
            pos++;
        } else {
            errors.add("Erro: Instrução if deve começar com 'if'");
            return false;
        }

        // Verifica se há um parêntese de abertura após "if"
        if (pos < tokens.length && tokens[pos].equals("(")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente após 'if'");
            return false;
        }

        // Verifica a expressão dentro dos parênteses
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma palavra-chave reservada, um identificador ou uma constante
        pos = verificarExpressaos(pos, tokens);

        // Verifica se há um parêntese de fechamento após a expressão
        if (pos < tokens.length && tokens[pos].equals(")")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente após a expressão");
            return false;
        }

        // Verifica o corpo do if
        // Como é um exemplo, podemos apenas verificar se o próximo token é uma chave de abertura
        if (pos < tokens.length && tokens[pos].equals("{")) {
            pos++;
            return true;
        } else {
            errors.add("Erro: Chave de abertura ausente para o corpo do if");

        }

        // Verifica se há tokens adicionais após a chave de fechamento do if
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após o corpo do if");
            return false;
        }

        return true;
    }
    // Método para verificar a sintaxe de uma chamada de método

    private boolean verificarChamadaMetodo(String instrucao) {
        String[] tokens = instrucao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é um identificador válido (ex: System.out)
        if (pos < tokens.length && isIdentifier(tokens[pos])) {
            pos++;
        } else {
            errors.add("Erro: Chamada de método deve começar com um identificador válido");
            return false;
        }

        // Verifica se há um ponto (opcional, para chamadas de método encadeadas)
        while (pos < tokens.length && tokens[pos].equals(".")) {
            pos++;
            if (pos < tokens.length && isIdentifier(tokens[pos])) {
                pos++;
            } else {
                errors.add("Erro: Esperado identificador após '.'");
                return false;
            }
        }

        // Verifica se há um parêntese de abertura
        if (pos < tokens.length && tokens[pos].equals("(")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de abertura ausente na chamada de método");
            return false;
        }

        // Verifica os argumentos do método (simplificado para este exemplo)
        pos = verificarArgumentosMetodo(pos, tokens);

        // Verifica se há um parêntese de fechamento
        if (pos < tokens.length && tokens[pos].equals(")")) {
            pos++;
        } else {
            errors.add("Erro: Parêntese de fechamento ausente na chamada de método");
            return false;
        }

        // Verifica se há um ponto e vírgula no final da chamada de método
        if (pos < tokens.length && tokens[pos].equals(";")) {
            pos++;
        } else {
            errors.add("Erro: Chamada de método deve terminar com ';'");
            return false;
        }

        // Verifica se há tokens adicionais após a chamada de método
        if (pos < tokens.length) {
            errors.add("Erro: Tokens adicionais após a chamada de método");
            return false;
        }

        return true;
    }

// Método para verificar os argumentos do método
    private int verificarArgumentosMetodo(int pos, String[] tokens) {
        // Simplificado: verificando se os argumentos são literais válidos ou identificadores
        while (pos < tokens.length && !tokens[pos].equals(")")) {
            if (isValidValue(tokens[pos]) || isIdentifier(tokens[pos])) {
                pos++;
            } else if (tokens[pos].equals(",")) {
                pos++; // Avança para o próximo argumento
            } else {
                errors.add("Erro: Argumento inválido na chamada de método");
                return pos;
            }
        }
        return pos;
    }

    public int verificarClass(String tokenString) {
        String[] aux = tokenString.trim().split("\\s+");
        int cont = 0;
        for (int i = 0; i < aux.length; i++) {
            if (isAccessModifier(aux[i])) {
                cont++;
            } else if (isModifier(aux[i])) {
                cont++;
            } else if (isIdentifier(aux[i])) {
                cont++;
            }
        }
        return cont;
    }

    public boolean verificarDeclaracaoClasse(String declaracao) {

        String[] tokens = declaracao.trim().split("\\s+");
        int pos = 0;

        // Verifica se o primeiro token é um modificador de acesso (opcional)
        if (pos < tokens.length && isAccessModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é um modificador (opcional)
        while (pos < tokens.length && isModifier(tokens[pos])) {
            pos++;
        }

        // Verifica se o próximo token é a palavra-chave 'class'
        if (pos < tokens.length && tokens[pos].equals("class")) {
            pos++;
        } else {
            errors.add("Erro: Esperado 'class' na declaração da classe em " + (pos + 1));
            return false;
        }

        // Verifica se o próximo token é um identificador válido (nome da classe)
        if (pos < tokens.length && isIdentifier(tokens[pos]) && !isReservedKeyword(tokens[pos])) {
            pos++;
        } else {
            errors.add("Erro: Esperado identificador válido (nome da classe) em " + (pos + 1));
            return false;
        }

        // Verifica herança (opcional)
        if (pos < tokens.length && tokens[pos].equals("extends")) {
            pos++;
            if (pos < tokens.length && isIdentifier(tokens[pos]) && !isReservedKeyword(tokens[pos])) {
                pos++;
            } else {
                errors.add("Erro: Esperado identificador válido (nome da classe pai) em " + (pos + 1));
                return false;
            }
        }

        // Verifica implementação de interfaces (opcional)
        if (pos < tokens.length && tokens[pos].equals("implements")) {
            pos++;
            while (pos < tokens.length && isIdentifier(tokens[pos]) && !isReservedKeyword(tokens[pos])) {
                pos++;
                if (pos < tokens.length && tokens[pos].equals(",")) {
                    pos++;
                } else {
                    break;
                }
            }
        }

        // Verifica se há uma chave de abertura para o corpo da classe
        if (pos < tokens.length && tokens[pos].equals("{")) {
            pos++;
            return true;
        } else {
            errors.add("Erro: Chave de abertura ausente para o corpo da classe em " + (pos + 1));
            return false;
        }

    }

}
