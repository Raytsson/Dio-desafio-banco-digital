package banco;

import cliente.Cliente;

public class ContaCorrente extends Conta {

    private Double credito = 1500.00;

    public ContaCorrente(Cliente cliente, Double credito) {
        super(cliente);
        this.credito = credito;
    }

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("=== Extrato Conta Corrente ===\n%s\nCredito: %.2f",
                super.toString(), credito);
    }
}
