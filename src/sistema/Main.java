package sistema;
import java.util.*;
public class Main {
	
    private static Scanner scanner = new Scanner(System.in);
    private static List<Conta> contas = new ArrayList<>();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            System.out.println("\n--- SISTEMA BANCÁRIO ----");
            System.out.println("1. Criar Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir ");
            System.out.println("5. Mostrar Contas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> depositar();
                case 3 -> sacar();
                case 4 -> transferir();
                case 5 -> mostrarContas();
                case 6 -> executando = false;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarConta() {
        scanner.nextLine();
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente(nome);
        Conta conta = new Conta(cliente);
        contas.add(conta);
        System.out.println("Conta criada com sucesso! Número: " + conta.getNumero());
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private static Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    private static void depositar() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        Conta conta = buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do depósito: ");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
        scanner.nextLine();
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void sacar() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        Conta conta = buscarConta(numero);
        if (conta != null) {
            System.out.print("Valor do saque: ");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
        scanner.nextLine();
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void transferir() {
        System.out.print("Conta origem: ");
        int origem = scanner.nextInt();
        Conta contaOrigem = buscarConta(origem);

        System.out.print("Conta destino: ");
        int destino = scanner.nextInt();
        Conta contaDestino = buscarConta(destino);

        if (contaOrigem != null && contaDestino != null) {
            System.out.print("Valor da transferência: ");
            double valor = scanner.nextDouble();
            contaOrigem.transferir(valor, contaDestino);
        } else {
            System.out.println("Conta(s) não encontrada(s).");
        }
        scanner.nextLine();
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void mostrarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            for (Conta c : contas) {
                c.imprimirExtrato();
            }
        }
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}

// Classe Cliente
class Cliente {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

// Classe Conta
class Conta {
    private static int SEQUENCIAL = 1;
    private int numero;
    private double saldo;
    private Cliente cliente;

    public Conta(Cliente cliente) {
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(double valor, Conta destino) {
        if (saldo >= valor) {
            this.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    public void imprimirExtrato() {
        System.out.println("\n--- Extrato da Conta " + numero + " ---");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Saldo: R$" + saldo);
    }

    public int getNumero() {
        return numero;
    }
} 

