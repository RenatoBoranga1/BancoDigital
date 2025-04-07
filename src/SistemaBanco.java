import java.util.ArrayList;
import java.util.Scanner;

public class SistemaBanco {

    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirLogoBanco(); 

        boolean executando = true;

        while (executando) {
            System.out.println("\n--- Banco Digital ---");
            System.out.println("1. Cadastrar novo cliente");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarCliente();
                    break;
                case "2":
                    fazerLogin();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void exibirLogoBanco() {
        System.out.println("===================================");
        System.out.println("     Banco Bradesco Digital");
        System.out.println("===================================");
    }

    private static void cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        if (buscarCliente(nome) != null) {
            System.out.println("Cliente já cadastrado com esse nome.");
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Cliente novoCliente = new Cliente(nome, senha);
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void fazerLogin() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = buscarCliente(nome);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (cliente.autenticar(senha)) {
            System.out.println("Login realizado com sucesso!");
            menuConta(cliente);
        } else {
            System.out.println("Senha incorreta!");
        }
    }

    private static Cliente buscarCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    private static void menuConta(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        ContaCorrente contaCorrente = new ContaCorrente(cliente);
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);

        int opcao;

        do {
            System.out.println("\n=== Menu da Conta ===");
            System.out.println("1. Ver Saldo");
            System.out.println("2. Depositar na Conta Corrente");
            System.out.println("3. Sacar da Conta Corrente");
            System.out.println("4. Transferir para Poupança");
            System.out.println("5. Ver Extrato Corrente");
            System.out.println("6. Ver Extrato Poupança");
            System.out.println("7. Solicitar Empréstimo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.printf("Saldo Conta Corrente: R$ %.2f\n", contaCorrente.getSaldo());
                    System.out.printf("Saldo Conta Poupança: R$ %.2f\n", contaPoupanca.getSaldo());
                    break;
                case 2:
                    System.out.print("Valor para depósito: R$ ");
                    double deposito = scanner.nextDouble();
                    contaCorrente.depositar(deposito);
                    System.out.println("Depósito realizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Valor para saque: R$ ");
                    double saque = scanner.nextDouble();
                    if (saque > contaCorrente.getSaldo()) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        contaCorrente.sacar(saque);
                        System.out.println("Saque realizado com sucesso.");
                    }
                    break;
                case 4:
                    System.out.print("Valor para transferir: R$ ");
                    double transferencia = scanner.nextDouble();
                    if (transferencia > contaCorrente.getSaldo()) {
                        System.out.println("Saldo insuficiente.");
                    } else {
                        contaCorrente.transferir(transferencia, contaPoupanca);
                        System.out.println("Transferência realizada com sucesso.");
                    }
                    break;
                case 5:
                    contaCorrente.imprimirExtrato();
                    break;
                case 6:
                    contaPoupanca.imprimirExtrato();
                    break;
                case 7:
                    System.out.print("Valor do empréstimo: R$ ");
                    double valorEmprestimo = scanner.nextDouble();
                    System.out.print("Número de parcelas: ");
                    int parcelas = scanner.nextInt();
                    contaCorrente.solicitarEmprestimo(valorEmprestimo, parcelas);
                    break;
                case 0:
                    System.out.println("Encerrando sessão...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
