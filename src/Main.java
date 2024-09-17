import banco.Banco;
import banco.Conta;
import banco.ContaCorrente;
import banco.ContaPoupanca;
import cliente.Cliente;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Scanner sc = new Scanner(System.in);

        // Obter nome do cliente
        System.out.println("Insira o nome do cliente: ");
        cliente.setNome(sc.nextLine());

        // Banco e Contas
        Banco banco = new Banco();
        banco.setNome("Meu Banco");
        Conta cc = new ContaCorrente(cliente);
        Conta poupanca = new ContaPoupanca(cliente);
        banco.adicionarConta(cc);
        banco.adicionarConta(poupanca);

        // Mapeia comandos para operações
        Map<String, Consumer<Scanner>> operacoes = new HashMap<>();
        operacoes.put("SACAR", scanner -> {
            System.out.println("Digite o valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consome a nova linha restante
            cc.sacar(valor);
        });
        operacoes.put("DEPOSITAR", scanner -> {
            System.out.println("Digite o valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consome a nova linha restante
            cc.depositar(valor);
        });
        operacoes.put("TRANSFERIR", scanner -> {
            System.out.println("Digite o valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consome a nova linha restante
            cc.transferir(valor, poupanca);
        });
        operacoes.put("EXTRATO", scanner -> {
            System.out.println("Poupança ou Conta Corrente?");
            String escolha = scanner.nextLine();
            if (escolha.equalsIgnoreCase("poupança")) {
                System.out.println(poupanca);
            } else {
                System.out.println(cc);
            }
        });

        // Loop principal
        String comando;
        do {
            System.out.println();
            System.out.println("Olá " + cliente.getNome() + ", o que você gostaria de fazer?");
            System.out.println("SACAR - DEPOSITAR - TRANSFERIR - EXTRATO - SAIR");
            comando = sc.nextLine().toUpperCase();

            if (comando.equals("SAIR")) {
                System.out.println("Obrigado por usar o sistema bancário. Até logo!");
                break; // Sai do loop imediatamente, sem realizar mais operações
            }

            if (operacoes.containsKey(comando)) {
                operacoes.get(comando).accept(sc);
            } else {
                System.out.println("Não entendi o que você gostaria!");
            }
        } while (true);

        sc.close();
        System.out.println("Obrigado por usar o sistema bancário. Até logo!");

        // Exibe todas as contas cadastradas no banco (exemplo de uso)
        banco.exibirContas();
    }
}
