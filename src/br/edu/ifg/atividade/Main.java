package br.edu.ifg.atividade;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import br.edu.ifg.vo.Cadastro;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        Cadastro cad = new Cadastro();
        int opcao = -1;
        pararPrograma:
        while (true) {
            System.out.println("-----------MENU------------");
            System.out.println("Entre com a opção desejada:");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Setor");
            System.out.println("3 - Deletar Funcionário");
            System.out.println("4 - Listar Funcionários");
            System.out.println("5 - Listar Funcionário pelo código");            
            System.out.println("6 - Listar todos os setores");
            System.out.println("7 - Listar Setores pelo código");
            System.out.println("8 - Sair");
            System.out.println("---------------------------");
            try {
                opcao = Integer.parseInt(ler.readLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Tipo de dado inválido, entre com o dado correto.");
            }
            switch (opcao) {
                case 1:
                    cad.cadastrarFuncionario();
                    opcao = 0;
                break;
                case 2:
                    cad.cadastrarSetor();
                    opcao = 0;
                break;
                case 3:
                    cad.deletarFuncionario();
                    opcao = 0;
                break;
                case 4:
                    cad.listaFuncionario();
                    opcao = 0;
                break;
                case 5:
                    cad.listaFuncionarioEspecifico();
                    opcao = 0;
                break;
                case 6:
                    cad.listarSetor();
                    opcao = 0;
                break;
                case 7:
                    cad.listarSetorEspecifico();
                    opcao = 0;
                break;
                case 8:
                    break pararPrograma;
                default:
                    System.out.println("Observe as opções listadas e entre com um número válido.");
                break;
            }
        }
    }
    
}