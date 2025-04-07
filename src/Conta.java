import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected List<Transacao> historico;
    protected double emprestimoSaldoDevedor;
    protected int emprestimoParcelasRestantes;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
        this.saldo = 0;
        this.emprestimoSaldoDevedor = 0;
        this.emprestimoParcelasRestantes = 0;
        this.historico = new ArrayList<>();
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque.");
            return;
        }
        saldo -= valor;
        historico.add(new Transacao("Saque", valor, "Saque efetuado"));
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido.");
            return;
        }
        saldo += valor;
        historico.add(new Transacao("Depósito", valor, "Depósito efetuado"));
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para transferência.");
            return;
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
        historico.add(new Transacao("Transferência", valor, "Transferido para conta " + ((Conta) contaDestino).getNumero()));
    }

    @Override
    public void solicitarEmprestimo(double valor, int parcelas) {
        if (valor <= 0 || parcelas <= 0) {
            System.out.println("Valor e parcelas devem ser maiores que zero.");
            return;
        }

        double juros = 0.05; // 5% de juros simples
        double totalComJuros = valor + (valor * juros);
        this.emprestimoSaldoDevedor += totalComJuros;
        this.emprestimoParcelasRestantes = parcelas;

        this.depositar(valor);
        historico.add(new Transacao("Empréstimo", valor, String.format("Empréstimo aprovado: R$ %.2f em %d parcelas", totalComJuros, parcelas)));

        System.out.printf("✅ Empréstimo aprovado: R$ %.2f com juros.\n", totalComJuros);
        System.out.printf("📥 Valor creditado na conta: R$ %.2f\n", valor);
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s\n", cliente.getNome());
        System.out.printf("Agência: %d\n", agencia);
        System.out.printf("Número: %d\n", numero);
        System.out.printf("Saldo: R$ %.2f\n", saldo);

        if (emprestimoSaldoDevedor > 0) {
            System.out.printf("💸 Empréstimo pendente: R$ %.2f em %d parcelas restantes\n",
                emprestimoSaldoDevedor, emprestimoParcelasRestantes);
        }

        System.out.println("\n--- Histórico de Transações ---");
        if (historico.isEmpty()) {
            System.out.println("Sem transações registradas.");
        } else {
            for (Transacao t : historico) {
                System.out.println(t);
            }
        }
    }

    // (opcional) para testes ou pré-visualização de empréstimo
    public void simularEmprestimo(double valor, int parcelas) {
        double juros = 0.05;
        double totalComJuros = valor + (valor * juros);
        System.out.printf("📊 Simulação: empréstimo de R$ %.2f em %d parcelas.\n", totalComJuros, parcelas);
    }
}
