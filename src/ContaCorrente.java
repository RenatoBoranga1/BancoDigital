public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
        if (emprestimoSaldoDevedor > 0) {
            System.out.printf("Saldo devedor empréstimo: R$ %.2f\n", emprestimoSaldoDevedor);
            System.out.printf("Parcelas restantes: %d\n", emprestimoParcelasRestantes);
        }
        System.out.println("==============================\n");
    }
}
