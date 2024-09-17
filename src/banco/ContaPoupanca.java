package banco;

import cliente.Cliente;

public class ContaPoupanca extends Conta {

    private double rendimentoMensal;
    private double taxaBanco;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void rentabilidade() {
        rendimentoMensal = saldo * 0.01;
        taxaBanco = 0.005 * saldo;
        saldo = saldo + rendimentoMensal - taxaBanco;
    }

    @Override
    public void imprimirExtrato() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        rentabilidade();
        return String.format("=== Extrato Conta Poupança ===\n%s\nRendimento mensal: %.2f\nTaxa do banco: %.2f",
                super.toString(), rendimentoMensal, taxaBanco);
    }
}
