package banco;

import cliente.Cliente;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    @Override
    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para o saque, seu saldo é de: R$" + saldo);
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (valor > saldo) {
            System.out.println("Seu saldo é insuficiente para transferir.");
        } else {
            saldo -= valor;
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        }
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

    @Override
    public String toString() {
        return String.format("Titular: %s\nAgencia: %d\nNumero: %d\nSaldo: %.2f",
                cliente.getNome(), agencia, numero, saldo);
    }
}
