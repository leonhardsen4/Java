package StartByCapgemini;

import java.util.Random;
import java.util.Scanner;

public class App {

    public static void msg(String texto) {
        System.out.print(texto);
    }

    public static void sair() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nAperte qualquer número para continuar: ");
        int x = input.nextInt();
    }

    /*
    LISTA DE EXERCÍCIOS I
     */
    public static void exercicio1() {
        /*
        1.Faça um algoritmo que receba dois números
        e exiba o resultado da sua soma;
         */

        int a = 0, b = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 1");
        System.out.print("Informe um número: ");
        a = input.nextInt();
        System.out.print("Informe outro número: ");
        b = input.nextInt();

        System.out.println("O resultado da soma é " + (a + b));

        sair();

    }

    public static void exercicio2() {
        /*
        2.Faça um algoritmo que receba dois números 
        e ao final mostre a soma, subtração, multiplicação 
        e a divisão dos dois números lidos;
         */

        float a = 0.0f, b = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 2");
        System.out.print("Informe um número: ");
        a = input.nextInt();
        System.out.print("Informe outro número: ");
        b = input.nextInt();

        System.out.println("O resultado da soma é " + (a + b));
        System.out.println("O resultado da subtração é " + (a - b));
        System.out.println("O resultado da multiplicação é " + (a * b));
        System.out.println("O resultado da divisão é " + (a / b));

        sair();

    }

    public static void exercicio3() {
        /*
        3.Escreva um algoritmo para determinar o 
        consumo médio de um automóvel sendo fornecida 
        a distância total percorrida pelo automóvel 
        e o total de combustível gasto;
         */

        float dist = 0.0f;
        float comb = 0.0f;
        float consumo = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 3");
        System.out.print("Informe a distância percorrida(km): ");
        dist = input.nextFloat();
        System.out.print("Informe o total de combustível gasto(L): ");
        comb = input.nextFloat();

        consumo = dist / comb;

        System.out.print("O consumo médio é de: ");
        System.out.printf("%.2f", consumo);
        System.out.print("km/L\n");
        
        sair();
    }

    public static void exercicio4() {
        /*
        4.Escreva um algoritmo que leia o nome de um vendedor,
        o seu salário fixo e o total de vendas efetuadas
        por ele no mês (em dinheiro). Sabendo que este vendedor
        ganha 15% de comissão sobre suas vendas efetuadas,
        informar o seu nome, o salário fixo e salário no final do mês;
         */

        String vendedor = "";
        float salario = 0.0f;
        float salarioFinal = 0.0f;
        float vendas = 0.0f;
        float comissao = 0.15f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 4");
        System.out.print("Informe o nome do vendedor: ");
        vendedor = input.nextLine();
        System.out.print("Informe o salário do vendedor: ");
        salario = input.nextFloat();
        System.out.print("Informe o total de vendas do vendedor: ");
        vendas = input.nextFloat();

        salarioFinal = salario + (vendas * comissao);

        System.out.println("\nVendedor: " + vendedor);
        System.out.print("Salário fixo: R$");
        System.out.printf("%.2f", salario);
        System.out.print("\nSalário + Comissão sobre vendas(15%): R$");
        System.out.printf("%.2f", salarioFinal);
        
        sair();
        
    }

    public static void exercicio5() {
        /*
        5.Escreva um algoritmo que leia o nome 
        de um aluno e as notas das três provas que 
        ele obteve no semestre. No final informar 
        o nome do aluno e a sua média (aritmética);
         */

        String aluno = "";
        float nota1 = 0.0f;
        float nota2 = 0.0f;
        float nota3 = 0.0f;
        float media = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 5");
        System.out.print("Informe o nome do aluno(a): ");
        aluno = input.nextLine();
        System.out.print("Informe a primeira nota: ");
        nota1 = input.nextFloat();
        System.out.print("Informe a segunda nota: ");
        nota2 = input.nextFloat();
        System.out.print("Informe a terceira nota: ");
        nota3 = input.nextFloat();

        media = (nota1 + nota2 + nota3) / 3;

        System.out.println("\nAluno(a): " + aluno);
        System.out.print("Média: ");
        System.out.printf("%.2f", media);
        
        sair();

    }

    public static void exercicio6() {
        /*
        6.Leia dois valores para as variáveis A e B,
        e efetuar as trocas dos valores de forma que
        a variável A passe a possuir o valor da variável B
        e a variável B passe a possuir o valor da variável A.
        Apresentar osvalores trocados;
         */

        int a = 0, b = 0, aux = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 6");
        System.out.print("Informe um número para a variável A: ");
        a = input.nextInt();
        System.out.print("Informe um número para a variável B: ");
        b = input.nextInt();

        System.out.println("Valores informados: A = " + a + "; B = " + b);
        aux = a;
        a = b;
        b = aux;
        System.out.println("Valores trocados: A = " + a + "; B = " + b);

        sair();

    }

    public static void exercicio7() {
        /*
        7.Leia uma temperatura em graus Celsius 
        e apresentá-la convertida em graus Fahrenheit. 
        A fórmula de conversão é: F = (9 * C + 160) / 5,
        sendo F a temperatura em Fahrenheit e C 
        a temperatura em Celsius;
         */

        float C = 0.0f;
        float F = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 7");
        System.out.print("Informe a temperatura em graus Celsius(॰C): ");
        C = input.nextFloat();

        F = ((9 * C) + 160) / 5;

        System.out.print("A temperatura informada corresponde a ");
        System.out.printf("%.2f", F);
        System.out.println("॰F");

        sair();

    }

    public static void exercicio8() {
        /*
        8.Elabore um algoritmo que efetue a apresentação 
        do valor da conversão em real (R$) de um valor lido em
        dólar (US$). O algoritmo deverá solicitar o valor da 
        cotação do dólar e também aquantidade de dólares
        disponíveis com o usuário.
         */

        float dolares = 0.0f;
        float reais = 0.0f;
        float cotacaoDolarReal = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 8");
        System.out.print("Informe a cotação do dólar(US$) no dia: ");
        cotacaoDolarReal = input.nextFloat();
        System.out.print("Informe quanto dinheiro deseja converter: ");
        dolares = input.nextFloat();

        reais = dolares * cotacaoDolarReal;

        System.out.print("\nUS$: ");
        System.out.printf("%.2f", dolares);
        System.out.print("\nR$: ");
        System.out.printf("%.2f\n", reais);

        sair();
    }

    public static void exercicio9() {
        /*
        9.Faça um algoritmo que receba um valor 
        que foi depositado e exiba o valor com 
        rendimento após um mês.Considere fixo o 
        juro da poupança em 0,07% a.m;
         */

        float deposito = 0.0f;
        float rendimento = 0.0f;
        float juros = 0.0007f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 9");
        System.out.print("Informe o valor do depósito: ");
        deposito = input.nextFloat();

        rendimento = deposito * juros;

        System.out.print("Rendimento: R$");
        System.out.printf("%.2f\n", rendimento);

        sair();

    }

    public static void exercicio10() {
        /*
        10. A Loja Mamão com Açúcar está vendendo 
        seus produtos em 5(cinco) prestações sem juros. 
        Faça um algoritmo que receba um valor de uma 
        compra e mostre o valor das prestações;
         */

        float compra = 0.0f;
        float parcela = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 10");
        System.out.print("Informe o valor da compra: ");
        compra = input.nextFloat();

        parcela = compra / 5;

        System.out.print("Parcela: 5 vezes de R$");
        System.out.printf("%.2f\n", parcela);

        sair();

    }

    public static void exercicio11() {
        /*
        11. Faça um algoritmo que receba o preço 
        de custo de um produto e mostre o valor de venda. 
        Sabe-se que o preço de custo receberá um acréscimo 
        de acordo com um percentual informado pelo usuário;
         */

        float precoCusto = 0.0f;
        float valorVenda = 0.0f;
        float percentual = 0.0f;;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 11");
        System.out.print("Informe o preço de custo do produto: ");
        precoCusto = input.nextFloat();
        System.out.print("Informe o percentual de acréscimo(%): ");
        percentual = input.nextFloat();

        valorVenda = precoCusto + (precoCusto * (percentual / 100));

        System.out.print("Valor de venda: ");
        System.out.printf("%.2f\n", valorVenda);

        sair();

    }

    public static void exercicio12() {
        /*
        12. O custo de um carro novo ao consumidor é 
        a soma do custo de fábrica mais o percentual 
        do distribuidor e dos impostos aplicados (primeiro
        os impostos são aplicados sobre o custo de fábrica, 
        e depois o percentual do distribuidor sobre o resultado). 
        Supondo que o percentual do distribuidor seja de 28% 
        e os impostos 45%, escreva um algoritmo que leia 
        o custo de fábrica de um carro e informe o custo ao 
        consumidor do mesmo;
         */

        float custoCarro = 0.0f;
        float impostos = 0.45f;
        float percentual = 0.28f;
        float custoFinal = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 12");
        System.out.print("Informe o custo de fábrica do carro: ");
        custoCarro = input.nextFloat();

        custoCarro = custoCarro + (custoCarro * impostos);
        custoFinal = custoCarro + (custoCarro * percentual);

        System.out.print("Preço final ao consumidor: R$");
        System.out.printf("%.2f\n", custoFinal);

        sair();

    }

    public static void exercicio13() {
        /*
        13. Faça um algoritmo que receba um número 
        e mostre uma mensagem caso este número seja maior que 10;
         */

        int num = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 13");
        System.out.print("Informe um número: ");
        num = input.nextInt();

        if (num > 10) {
            System.out.println("O número informado é maior que 10.");
        }

        sair();
    }

    public static void exercicio14() {
        /*
        14. Escreva um algoritmo que leia dois valores
        inteiros distintos e informe qual é o maior;
         */

        int a = 0;
        int b = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 14");
        System.out.print("Informe um número: ");
        a = input.nextInt();
        System.out.print("Informe outro número: ");
        b = input.nextInt();

        if (a > b) {
            System.out.println("O número " + a + " é maior que " + b);
        } else if (a < b) {
            System.out.println("O número " + b + " é maior que " + a);
        } else {
            System.out.println("Os números são iguais");
        }

        sair();

    }

    public static void exercicio15() {
        /*
        15. Faça um algoritmo que receba um número 
        e diga se este número está no intervalo entre 100 e 200;
         */

        int num = 0;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 15");
        System.out.print("Informe um número: ");
        num = input.nextInt();

        if (num >= 100 && num <= 200) {
            System.out.print("O número " + num + " está no intervalo entre 100 e 200");
        } else {
            System.out.print("O número não está no intervalo entre 100 e 200");
        }

        sair();

    }

    public static void exercicio16() {
        /*
        16. Escreva um algoritmo que leia o nome 
        e as três notas obtidas por um aluno durante 
        o semestre. Calcular a sua média (aritmética), 
        informar o nome e sua menção aprovado (media >= 7), 
        Reprovado (media <= 5) e Recuperação (media entre 5.1 a 6.9);
         */

        String aluno = "";
        float nota1 = 0.0f;
        float nota2 = 0.0f;
        float nota3 = 0.0f;
        float media = 0.0f;

        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 16");
        System.out.print("Informe o nome do aluno(a): ");
        aluno = input.nextLine();
        System.out.print("Informe a primeira nota: ");
        nota1 = input.nextFloat();
        System.out.print("Informe a segunda nota: ");
        nota2 = input.nextFloat();
        System.out.print("Informe a terceira nota: ");
        nota3 = input.nextFloat();

        media = (nota1 + nota2 + nota3) / 3;

        System.out.println("\nAluno(a): " + aluno);
        System.out.print("Média: ");
        System.out.printf("%.2f", media);

        if (media >= 7) {
            System.out.print("\nSituação: Aprovado(a)");
        } else if (media <= 5) {
            System.out.print("\nSituação: Reprovado(a)");
        } else {
            System.out.print("\nSituação: Recuperação");
        }

        sair();

    }

    public static void exercicio17() {
        /*
        17. Leia 80 números e ao final informar
        quantos número(s) est(á)ão no intervalo
        entre 10 (inclusive) e 150 (inclusive);
         */

        int num[] = new int[80];
        int i = 0, j = 0, aux = 0;

        Random gerador = new Random();
        Scanner input = new Scanner(System.in);

        System.out.println("Exercício 17");
        System.out.println("Números gerados: ");
        for (i = 0; i < 80; i++) {
            System.out.print(num[i] = gerador.nextInt(5000));
            System.out.print(" ");
        }

        //Ordenando o vetor
        for (i = 0; i < 80; i++) {
            for (j = 0; j < 80; j++) {
                if (num[i] < num[j]) {
                    aux = num[i];
                    num[i] = num[j];
                    num[j] = aux;
                }
            }
        }
        System.out.println("\nNúmeros ordenados: ");
        for (i = 0; i < 80; i++) {
            System.out.print(num[i]);
            System.out.print(" ");
        }

        System.out.println("\nNúmeros entre 10 e 150: ");
        for (i = 0; i < 80; i++) {
            if (num[i] >= 10 && num[i] <= 150) {
                System.out.print(num[i]);
                System.out.print(" ");
            }
        }

        sair();

    }

    public static void exercicio18() {
        /*
        18. Faça um algoritmo que receba a idade de 75 pessoas 
        e mostre mensagem informando “maior de idade” e “menor de idade”
        para cada pessoa. Considere a idade a partir de 18 anos como maior de idade;
         */

        int idade[] = new int[75];

        Random gerador = new Random();

        msg("Exercício 18");
        msg("\nGera vetor com idade de 75 pessoas:");
        for (int i = 0; i < 75; i++) {
            idade[i] = gerador.nextInt(120);
        }
        
        msg("\nApresenta todas as idades geradas e se é maior ou menor de idade:\n");
        for (int i = 0; i < 75; i++) {
            if (idade[i] < 18){
                System.out.println("Idade: " + idade[i] + " -> Menor de idade");
            }else{
                System.out.println("Idade: " + idade[i] + " -> Maior de idade");
            }
        }       

        sair();

    }
    
    public static void exercicio19(){
        /*
        19. Escreva um algoritmo que leia o nome e o sexo de 56 pessoas 
        e informe o nome e se ela é homem ou mulher. No final informe total 
        de homens e de mulheres;
        */
        
        msg("Exercício 19");
        
        sair();
        
    }
    
    public static void exercicio20(){
        /*
        20. A concessionária de veículos “CARANGO VELHO” está vendendo 
        os seus veículos com desconto. Faça um algoritmo que calcule e exiba 
        o valor do desconto e o valor a ser pago pelo cliente de vários carros. 
        O desconto deverá ser calculado de acordo com o ano do veículo. 
        Até 2000 - 12% e acima de 2000 - 7%. O sistema deverá perguntar se 
        deseja continuar calculando desconto até que a resposta seja: 
        “(N) Não”. Informar total de carros com ano até 2000 e total geral;
        */
        
        msg("Exercício 20");
        
        sair();
        
    }
    
    public static void exercicio21(){
        /*
        21. Escreva um algoritmo que leia os dados de “N” pessoas 
        (nome, sexo, idade e saúde) e informe se está apta ou não para 
        cumprir o serviço militar obrigatório. Informe os totais;
        */
        
        msg("Exercício 21");
        
        sair();
        
    }
    
    public static void exercicio22(){
        /*
        22. Faça um algoritmo que receba o preço de custo e o preço de venda
        de 40 produtos. Mostre como resultado se houve lucro, prejuízo ou empate
        para cada produto. Informe o valor de custo de cada produto, o valor
        de venda de cada produto, amédia de preço de custo e do preço de venda;
        */
        
        msg("Exercício 22");
        
        sair();
        
    }
    
    public static void exercicio23(){
        /*
        23. Faça um algoritmo que receba um número e mostre uma mensagem 
        caso este número sege maior que 80, menor que 25 ou igual a 40;
        */
        
        msg("Exercício 23");
        
        sair();
        
    }
    
    public static void exercicio24(){
        /*
        24. Faça um algoritmo que receba “N” números e mostre positivo, 
        negativo ou zero para cada número;
        */
        
        msg("Exercício 24");
        
        sair();
        
    }
    
    public static void exercicio25(){
        /*
        25. Faça um algoritmo que leia dois números e identifique se são iguais 
        ou diferentes. Caso eles sejam iguais imprima uma mensagem dizendo que 
        eles são iguais. Caso sejam diferentes, informe qual número é o
        maior, e uma mensagem que são diferentes;
        */
        
        msg("Exercício 25");
        
        sair();
        
    }
    
    public static void exercicio26(){
        /*
        26. Faça um algoritmo que leia um número de 1 a 5 e escreva por extenso.
        Caso o usuário digite um número que não esteja neste intervalo, 
        exibir a seguinte mensagem: número inválido;
        */
        
        msg("Exercício 26");
        
        sair();
        
    }
    
    public static void exercicio27(){
        /*
        27. A concessionária de veículos “CARANGO” está vendendo os seus 
        veículos com desconto. Faça um algoritmo que calcule e exiba o valor do 
        desconto e o valor a ser pago pelo cliente. O desconto deverá ser
        calculado sobre o valor do veículo de acordo com o combustível
        (álcool – 25%, gasolina – 21% ou diesel –14%). Com valor do veículo zero
        encerra entrada de dados. Informe total de desconto e total pago pelos
        clientes;
        */
        
        msg("Exercício 27");
        
        sair();
        
    }
    
    public static void exercicio28(){
        /*
        28. Escreva um algoritmo para uma empresa que decide dar um reajuste 
        a seus 584 funcionários de acordo com os seguintes critérios:
        a. 50% para aqueles que ganham menos do que três salários mínimos;
        b. 20% para aqueles que ganham entre três até dez salários mínimos;
        c. 15% para aqueles que ganham acima de dez até vinte salários mínimos;
        d. 10% para os demais funcionários.
        Leia o nome do funcionário, seu salário e o valor do salário mínimo. 
        Calcule o seu novo salário reajustado. Escrever o nome do funcionário, 
        o reajuste e seu novo salário. Calcule quanto à empresa vai aumentar 
        sua folha de pagamento;
        */
        
        msg("Exercício 28");
        
        sair();
        
    }
    
    public static void exercicio29(){
        /*
        29. Faça um algoritmo que receba o número do mês e mostre o mês 
        correspondente. Valide mês inválido;
        */
        
        msg("Exercício 29");
        
        sair();
        
    }
    
    public static void exercicio30(){
        /*
        30. Escreva um algoritmo que leia três valores inteiros 
        distintos e os escreva em ordem crescente;
        */
        
        msg("Exercício 30");
        
        sair();
        
    }
    
    public static void exercicio31(){
        /*
        31. Dados três valores A, B e C, em que A e B são números reais 
        e C é um caractere, pede-se para imprimir o resultado da operação 
        de A por B se C for um símbolo de operador aritmético; 
        caso contrário deve ser impressa uma mensagem de operador não definido. 
        Tratar erro de divisão por zero;
        */
        
        msg("Exercício 31");
        
        sair();
        
    }
    
    public static void exercicio32(){
        /*
        32. Escreva um algoritmo que leia três valores inteiros 
        e verifique se eles podem ser os lados de um triângulo.
        Se forem, informar qual o tipo de triângulo que eles formam: 
        equilátero, isóscele ou escaleno.
        Propriedade: o comprimento de cada lado de um triângulo é menor do que 
        a soma dos comprimentos dos outros dois lados.
        a. Triângulo Equilátero: aquele que tem os comprimentos dos três lados iguais;
        b. Triângulo Isóscele: aquele que tem os comprimentos de dois lados iguais.
        c. Triângulo equilátero é também isóscele;
        d. Triângulo Escaleno: aquele que tem os comprimentos de seus três lados diferentes;
        */
        
        msg("Exercício 32");
        
        sair();
        
    }
    
    public static void exercicio33(){
        /*
        33. A escola “APRENDER” faz o pagamento de seus professores por hora/aula. 
        Faça um algoritmo que calcule e exiba o salário de um professor. 
        Sabe-se que o valor da hora/aula segue a tabela abaixo:
        a. Professor Nível 1 R$12,00 por hora/aula;
        b. Professor Nível 2 R$17,00 por hora/aula;
        c. Professor Nível 3 R$25,00 por hora/aula;
        */
        
        msg("Exercício 33");
        
        sair();
        
    }
    
    public static void exercicio34(){
        /*
        34. Elabore um algoritmo que, dada a idade de um nadador. 
        Classifique-o em uma das seguintes categorias:
        a. Infantil A = 5 - 7 anos;
        b. Infantil B = 8 - 10 anos;
        c. Juvenil A = 11- 13 anos;
        d. Juvenil B = 14 - 17 anos;
        e. Sênior = 18 - 25 anos.
        Apresentar mensagem “idade fora da faixa etária” 
        quando for outro ano não contemplado
        */
        
        msg("Exercício 34");
        
        sair();
        
    }
    
    public static void exercicio35(){
        /*
        35. Faça um algoritmo que calcule o valor da conta de luz de uma pessoa. 
        Sabe-se que o cálculo da conta de luz segue a tabela abaixo:
        Tipo de Cliente         Valor do KW/h
        a. (Residência)         0,60;
        b. (Comércio)           0,48;
        c. (Indústria)          1,29.
        */
        
        msg("Exercício 35");
        
        sair();
    }
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int opcao;
        System.out.println("LISTA DE EXERCÍCIOS BÁSICOS DE JAVA\n");

        do {
            System.out.println("\nInforme qual exercício deseja executar: \n");
            System.out.println("1 - Exercício 1: Soma de dois números");
            System.out.println("2 - Exercício 2: Soma, subtração, multiplicação e divisão");
            System.out.println("3 - Exercício 3: Média de consumo de combustível");
            System.out.println("4 - Exercício 4: Comissão do vendedor");
            System.out.println("5 - Exercício 5: Média aritmética");
            System.out.println("6 - Exercício 6: Inverter valores");
            System.out.println("7 - Exercício 7: Conversão de Celsius em Fahrenheit");
            System.out.println("8 - Exercício 8: Conversão Dólar/Real");
            System.out.println("9 - Exercício 9: Calcular rendimento de depósito");
            System.out.println("10 - Exercício 10: Loja Mamão com Açúcar");
            System.out.println("11 - Exercício 11: Preço de custo com acréscimo");
            System.out.println("12 - Exercício 12: Custo de um carro novo");
            System.out.println("13 - Exercício 13: Maior que 10");
            System.out.println("14 - Exercício 14: Qual é o maior?");
            System.out.println("15 - Exercício 15: Entre 100 e 200");
            System.out.println("16 - Exercício 16: Média aritmética e situação do aluno");
            System.out.println("17 - Exercício 17: Números entre 10 e 150");
            System.out.println("18 - Exercício 18: Maior e menor de idade");
            System.out.println("19 - Exercício 19");
            System.out.println("20 - Exercício 20");
            System.out.println("21 - Exercício 21");
            System.out.println("22 - Exercício 22");
            System.out.println("23 - Exercício 23");
            System.out.println("24 - Exercício 24");
            System.out.println("25 - Exercício 25");
            System.out.println("26 - Exercício 26");
            System.out.println("27 - Exercício 27");
            System.out.println("28 - Exercício 28");
            System.out.println("29 - Exercício 29");
            System.out.println("30 - Exercício 30");
            System.out.println("31 - Exercício 31");
            System.out.println("32 - Exercício 32");
            System.out.println("33 - Exercício 33");
            System.out.println("34 - Exercício 34");
            System.out.println("35 - Exercício 35");
            System.out.println("Ou digite 0 para sair.\n");
            System.out.print("Opção: ");
            opcao = input.nextInt();

            switch (opcao) {
                case 1 -> {
                    exercicio1();
                    break;
                }
                case 2 -> {
                    exercicio2();
                    break;
                }
                case 3 -> {
                    exercicio3();
                    break;
                }
                case 4 -> {
                    exercicio4();
                    break;
                }
                case 5 -> {
                    exercicio5();
                    break;
                }
                case 6 -> {
                    exercicio6();
                    break;
                }
                case 7 -> {
                    exercicio7();
                    break;
                }
                case 8 -> {
                    exercicio8();
                    break;
                }
                case 9 -> {
                    exercicio9();
                    break;
                }
                case 10 -> {
                    exercicio10();
                    break;
                }
                case 11 -> {
                    exercicio11();
                    break;
                }
                case 12 -> {
                    exercicio12();
                    break;
                }
                case 13 -> {
                    exercicio13();
                    break;
                }
                case 14 -> {
                    exercicio14();
                    break;
                }
                case 15 -> {
                    exercicio15();
                    break;
                }
                case 16 -> {
                    exercicio16();
                    break;
                }
                case 17 -> {
                    exercicio17();
                    break;
                }
                case 18 -> {
                    exercicio18();
                    break;
                }
                case 19 -> {
                    exercicio19();
                    break;
                }
                case 20 -> {
                    exercicio20();
                    break;
                }
                case 21 -> {
                    exercicio21();
                    break;
                }
                case 22 -> {
                    exercicio22();
                    break;
                }
                case 23 -> {
                    exercicio23();
                    break;
                }
                case 24 -> {
                    exercicio24();
                    break;
                }
                case 25 -> {
                    exercicio25();
                    break;
                }
                case 26 -> {
                    exercicio26();
                    break;
                }
                case 27 -> {
                    exercicio27();
                    break;
                }
                case 28 -> {
                    exercicio28();
                    break;
                }
                case 29 -> {
                    exercicio29();
                    break;
                }
                case 30 -> {
                    exercicio30();
                    break;
                }
                case 31 -> {
                    exercicio31();
                    break;
                }
                case 32 -> {
                    exercicio32();
                    break;
                }
                case 33 -> {
                    exercicio33();
                    break;
                }
                case 34 -> {
                    exercicio34();
                    break;
                }
                case 35 -> {
                    exercicio35();
                    break;
                }
                default -> {
                    msg("OOOOPS!");
                    break;
                }
            }

        } while (opcao != 0);

        if (opcao == 0) {
            System.out.println("\nOBRIGADO E ATÉ LOGO!\n");
        }
    }
}
