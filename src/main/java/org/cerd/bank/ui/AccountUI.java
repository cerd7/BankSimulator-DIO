package org.cerd.bank.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import org.cerd.bank.model.Account;

/**
 * Interface de usuário para operações de conta bancária.
 * Responsável por exibir menus e coletar dados para operações como
 * depósito, saque e consulta de saldo.
 */
public class AccountUI implements AutoCloseable {
    
    private final Scanner scanner;

    /**
     * Construtor padrão que inicializa o Scanner.
     */
    public AccountUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Construtor que recebe um Scanner externo (útil para testes).
     *
     * @param scanner Scanner a ser utilizado
     */
    public AccountUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Exibe o menu de operações da conta.
     *
     * @return Opção escolhida pelo usuário
     */
    public int showAccountMenu() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              Menu da Conta                         ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Consultar Saldo                               ║");
        System.out.println("║  [2] Realizar Depósito                             ║");
        System.out.println("║  [3] Realizar Saque                                ║");
        System.out.println("║  [4] Ver Extrato                                   ║");
        System.out.println("║  [5] Sair                                          ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
        return readValidOption(1, 5);
    }

    /**
     * Exibe o saldo atual da conta.
     *
     * @param account Conta a ter o saldo exibido
     */
    public void showBalance(Account account) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              Consulta de Saldo                     ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║  Conta: %-42s║%n", account.getAccountID());
        System.out.printf("║  Titular: %-40s║%n", account.getInfoUser().getName());
        System.out.printf("║  Saldo: R$ %-39.2f║%n", account.getBalance());
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Solicita o valor para depósito.
     *
     * @return Valor informado pelo usuário
     */
    public BigDecimal requestDepositAmount() {
        System.out.println();
        System.out.println("═══════════ Depósito ═══════════");
        System.out.print("Informe o valor do depósito: R$ ");
        return readValidAmount();
    }

    /**
     * Solicita o valor para saque.
     *
     * @return Valor informado pelo usuário
     */
    public BigDecimal requestWithdrawAmount() {
        System.out.println();
        System.out.println("═══════════ Saque ═══════════");
        System.out.print("Informe o valor do saque: R$ ");
        return readValidAmount();
    }

    /**
     * Exibe mensagem de sucesso em uma operação.
     *
     * @param operationType Tipo da operação (Depósito, Saque, etc.)
     * @param amount        Valor da operação
     * @param newBalance    Novo saldo após a operação
     */
    public void showOperationSuccess(String operationType, BigDecimal amount, BigDecimal newBalance) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.printf("║  ✓ %s realizado com sucesso!%n", operationType);
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║  Valor: R$ %.2f%n", amount);
        System.out.printf("║  Novo saldo: R$ %.2f%n", newBalance);
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Exibe mensagem de erro.
     *
     * @param errorMessage Mensagem de erro
     */
    public void showError(String errorMessage) {
        System.out.println();
        System.out.println("❌ ERRO: " + errorMessage);
        System.out.println();
    }

    /**
     * Exibe uma mensagem genérica.
     *
     * @param message Mensagem a ser exibida
     */
    public void showMessage(String message) {
        System.out.println();
        System.out.println("ℹ️  " + message);
        System.out.println();
    }

    /**
     * Lê e valida um valor monetário positivo.
     *
     * @return Valor válido como BigDecimal
     */
    private BigDecimal readValidAmount() {
        while (true) {
            try {
                String input = scanner.nextLine().trim().replace(",", ".");
                BigDecimal amount = new BigDecimal(input);
                if (amount.compareTo(BigDecimal.ZERO) > 0) {
                    return amount;
                }
                System.out.print("O valor deve ser positivo. Tente novamente: R$ ");
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido. Digite um número: R$ ");
            }
        }
    }

    /**
     * Lê e valida uma opção numérica dentro de um intervalo.
     *
     * @param min Valor mínimo aceito
     * @param max Valor máximo aceito
     * @return Opção válida escolhida pelo usuário
     */
    private int readValidOption(int min, int max) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());
                if (option >= min && option <= max) {
                    return option;
                }
                System.out.printf("Por favor, digite um valor entre %d e %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    @Override
    public void close() {
        // Não fecha o scanner aqui pois pode ser compartilhado
    }
}
