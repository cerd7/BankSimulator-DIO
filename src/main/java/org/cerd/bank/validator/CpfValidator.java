package org.cerd.bank.validator;

import org.cerd.bank.config.AppConfig;
import org.cerd.bank.exception.InvalidCpfException;

/**
 * Validador de CPF brasileiro.
 * Implementa o algoritmo oficial de validação dos dígitos verificadores.
 */
public class CpfValidator {

    private static final int FIRST_DIGIT_INDEX = 9;
    private static final int SECOND_DIGIT_INDEX = 10;
    private static final int FIRST_DIGIT_MULTIPLIER_START = 10;
    private static final int SECOND_DIGIT_MULTIPLIER_START = 11;
    private static final int DIVISOR = 11;

    /**
     * Valida um CPF, lançando exceção se inválido.
     *
     * @param cpf CPF a ser validado (somente números)
     * @throws InvalidCpfException se o CPF for inválido
     */
    public void validate(String cpf) throws InvalidCpfException {
        if (!isValidFormat(cpf)) {
            throw new InvalidCpfException("CPF deve conter exatamente 11 dígitos numéricos");
        }
        if (isAllSameDigit(cpf)) {
            throw new InvalidCpfException("CPF não pode ter todos os dígitos iguais");
        }
        if (!hasValidCheckDigits(cpf)) {
            throw new InvalidCpfException("Dígitos verificadores do CPF são inválidos");
        }
    }

    /**
     * Verifica se um CPF é válido sem lançar exceção.
     *
     * @param cpf CPF a ser validado
     * @return true se válido, false caso contrário
     */
    public boolean isValid(String cpf) {
        try {
            validate(cpf);
            return true;
        } catch (InvalidCpfException e) {
            return false;
        }
    }

    /**
     * Verifica se o CPF tem formato válido (11 dígitos numéricos).
     */
    private boolean isValidFormat(String cpf) {
        return cpf != null &&
                cpf.length() == AppConfig.CPF_LENGTH &&
                cpf.matches("\\d{11}");
    }

    /**
     * Verifica se todos os dígitos do CPF são iguais (ex: 111.111.111-11).
     * CPFs com todos os dígitos iguais são considerados inválidos.
     */
    private boolean isAllSameDigit(String cpf) {
        return cpf.matches("(\\d)\\1{10}");
    }

    /**
     * Valida os dígitos verificadores do CPF usando o algoritmo oficial.
     * 
     * O algoritmo funciona da seguinte forma:
     * 1. Calcula o primeiro dígito verificador usando os 9 primeiros dígitos
     * 2. Calcula o segundo dígito verificador usando os 10 primeiros dígitos
     * 3. Compara com os dígitos nas posições 10 e 11 do CPF
     */
    private boolean hasValidCheckDigits(String cpf) {
        int[] digits = cpf.chars().map(c -> c - '0').toArray();

        // Cálculo do primeiro dígito verificador
        int sum1 = 0;
        for (int i = 0; i < FIRST_DIGIT_INDEX; i++) {
            sum1 += digits[i] * (FIRST_DIGIT_MULTIPLIER_START - i);
        }
        int firstVerifier = calculateVerifierDigit(sum1);
        if (firstVerifier != digits[FIRST_DIGIT_INDEX]) {
            return false;
        }

        // Cálculo do segundo dígito verificador
        int sum2 = 0;
        for (int i = 0; i < SECOND_DIGIT_INDEX; i++) {
            sum2 += digits[i] * (SECOND_DIGIT_MULTIPLIER_START - i);
        }
        int secondVerifier = calculateVerifierDigit(sum2);
        
        return secondVerifier == digits[SECOND_DIGIT_INDEX];
    }

    /**
     * Calcula o dígito verificador a partir da soma ponderada.
     *
     * @param sum Soma ponderada dos dígitos
     * @return Dígito verificador (0-9)
     */
    private int calculateVerifierDigit(int sum) {
        int remainder = sum % DIVISOR;
        int verifier = DIVISOR - remainder;
        return verifier >= 10 ? 0 : verifier;
    }
}