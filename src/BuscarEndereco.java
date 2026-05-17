
import java.util.Scanner;

public class BuscarEndereco {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);

        try {
            System.out.println("Olá, bem vindo!");
            System.out.println("===== SISTEMA DE CONSULTA DE CEP =====");
            System.out.print("Favor informe um CEP válido para consulta: ");
            String cep = ler.next().replaceAll("[^0-9]", "");

            if (cep.length() != 8) {
                throw new IllegalArgumentException("CEP deve conter 8 numeros validos.");
            }

            ConsultaCep consultaCep = new ConsultaCep();
            Endereco endereco = consultaCep.buscar(cep);

            System.out.println("\nPara o CEP informado " + cep + ", segue o endereço:");
            System.out.println(endereco);
                GeradorDeArquivo gerador = new GeradorDeArquivo();
                gerador.salvaJson(endereco);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}