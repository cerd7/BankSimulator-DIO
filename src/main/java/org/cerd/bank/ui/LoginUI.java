package org.cerd.bank.ui;

import java.util.Scanner;

/**
 * Interface de usuário para operações de login.
 * Responsável por coletar credenciais e exibir mensagens relacionadas à autenticação.
 */
public class LoginUI implements AutoCloseable {
    
    private final Scanner scanner;

    /**
     * Construtor padrão que inicializa o Scanner.
     */
    public LoginUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Construtor que recebe um Scanner externo (útil para testes).
     *
     * @param scanner Scanner a ser utilizado
     */
    public LoginUI(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Exibe a tela de login e coleta as credenciais.
     *
     * @return Array com [accountId, password]
     */
    public String[] collectCredentials() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║                    LOGIN                           ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.print("Número da conta: ");
        String accountId = scanner.nextLine().trim();
        
        System.out.print("Senha: ");
        String password = scanner.nextLine().trim();
        
        return new String[]{accountId, password};
    }

    /**
     * Solicita apenas o número da conta.
     *
     * @return Número da conta informado
     */
    public String requestAccountId() {
        System.out.print("Informe o número da conta: ");
        return scanner.nextLine().trim();
    }

    /**
     * Solicita apenas a senha.
     *
     * @return Senha informada
     */
    public String requestPassword() {
        System.out.print("Informe a senha: ");
        return scanner.nextLine().trim();
    }

    /**
     * Exibe mensagem de login bem-sucedido.
     *
     * @param userName Nome do usuário logado
     */
    public void showLoginSuccess(String userName) {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         ✓ LOGIN REALIZADO COM SUCESSO!             ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║  Bem-vindo(a), %-35s║%n", userName + "!");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Exibe mensagem de falha no login.
     */
    public void showLoginFailed() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         ❌ FALHA NO LOGIN                          ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  Conta ou senha incorretos.                        ║");
        System.out.println("║  Verifique os dados e tente novamente.             ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Pergunta se o usuário deseja tentar novamente.
     *
     * @return true se deseja tentar novamente, false caso contrário
     */
    public boolean askRetry() {
        System.out.println("Deseja tentar novamente?");
        System.out.println("  [1] Sim");
        System.out.println("  [2] Não");
        System.out.print("Escolha uma opção: ");
        return readValidOption(1, 2) == 1;
    }

    /**
     * Exibe mensagem de conta bloqueada.
     */
    public void showAccountBlocked() {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         ⚠️  CONTA BLOQUEADA                        ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.println("║  Você excedeu o número máximo de tentativas.       ║");
        System.out.println("║  Entre em contato com o suporte para desbloquear.  ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println();
    }

    /**
     * Lê e valida uma opção numérica dentro de um intervalo.
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
