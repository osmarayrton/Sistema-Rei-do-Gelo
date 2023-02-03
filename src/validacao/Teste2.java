/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;

import java.util.Scanner;
import validacao.ValidaCNPJ;

/**
 *
 * @author osmar
 */
public class Teste2 {
    
     public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);

    String CNPJ;

    System.out.printf("Informe um CNPJ: ");
    CNPJ = ler.next();

    System.out.printf("\nResultado: ");
// usando os métodos isCNPJ() e imprimeCNPJ() da classe "ValidaCNPJ"
    if (ValidaCNPJ.isCNPJ(CNPJ) == true)
       System.out.printf("%s\n", ValidaCNPJ.imprimeCNPJ(CNPJ));
    else System.out.printf("Erro, CNPJ inválido !!!\n");
  }
    
}
