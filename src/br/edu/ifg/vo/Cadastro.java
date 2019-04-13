package br.edu.ifg.vo;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Cadastro {
    private final List <Funcionario> funcionarios = new ArrayList<>();
    private final List <Setor> setores = new ArrayList<>();
    private Funcionario funcionario;
    private Setor setor;
    private int contador = 0;
    private int codigo;
    BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
    
    public int cadastrarFuncionario() {
        funcionario = new Funcionario();
        try {
            if (setores.isEmpty()) {
                System.out.println("Não há nenhum setor cadastrado, adicione um antes de cadastar um funcionário.");
            }
            else {
                contador = 0;
                boolean flagCodigo = true;
                boolean flagPegaFunc = true;
                while(flagCodigo) {
                    if (contador == 4) {
                        System.out.println("Cadastro cancelado, muitas tentativas.");
                        return 0;
                    }
                    System.out.println("Entre com o código do funcionário:");
                    try {
                        codigo = Integer.parseInt(ler.readLine());
                        flagCodigo = false;
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                        flagCodigo = true;
                        flagPegaFunc = false;
                        ++contador;
                    }
                    if (pegaFuncionario(codigo) != null && (flagPegaFunc)) {
                        System.out.println("O código inserido já existe, entre com um novo código.");
                        ++contador;
                        flagCodigo = true;
                        flagPegaFunc = true;
                    }
                }
                funcionario.setCodigo(codigo);
                contador = 0;
                System.out.println("Entre com o nome do funcionário:");
                String nome;
                while((nome = ler.readLine()).length() < 4) {
                    System.out.println("Para este campo, não são aceitos valores nulos ou com menores que 4 caracteres");
                    if (contador == 4) {
                        System.out.println("Cadastro cancelado, muitas tentativas realizadas.");
                        return 0;
                    }
                    ++contador;
                }
                funcionario.setNome(nome);
                boolean flagIdade = true;
                contador = 0;
                while(flagIdade) {
                    ++contador;
                    if (contador >= 4) {
                        System.out.println("Cadastro cancelado, muitas tentativas realizadas.");
                        return 0;
                    }
                    System.out.println("Entre com a idade do funcionário:");
                    int idade = -1;
                    try {
                        idade = Integer.parseInt(ler.readLine());
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                        flagIdade = true;
                        ++contador;
                    }
                    while(idade < 18) {
                        ++contador;
                        if (contador >= 4) {
                            System.out.println("Cadastro cancelado, muitas tentativas realizadas.");
                            return 0;
                        }
                        System.out.println("Não é permitido o cadastro de funcionários menores de idade ou com idade inválida.");
                        System.out.println("Entre com a idade do funcionário:");
                        try {
                            idade = Integer.parseInt(ler.readLine());
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                            flagIdade = true;
                            ++contador;
                        }
                    }
                    funcionario.setIdade(idade);
                    flagIdade = false;
                }
                boolean flag = true;
                contador = 0;
                while (flag) {
                    boolean flagSetor = true;
                    ++contador;
                    while (flagSetor) {
                        if (contador == 4) {
                            System.out.println("Cadastro cancelado, muitas tentativas realizadas.");
                            return 0;
                        }
                        try {
                            System.out.println("Entre com o código do setor:");
                            int codigoSetor = Integer.parseInt(ler.readLine());
                            flagSetor = false;
                            setor = pegaSetor(codigoSetor);
                            while (setor == null) {
                                System.out.println("Código inválido. Entre com um código existente:");
                                codigoSetor = Integer.parseInt(ler.readLine());
                                setor = pegaSetor(codigoSetor);
                                flagSetor = false;
                                ++contador;
                                if(contador == 4) {
                                    System.out.println("Cadastro cancelado, muitas tentativas ralizadas.");
                                    return 0;
                                }
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                            flagSetor = true;
                            ++contador;
                        }
                    }
                    System.out.println("O setor escolhido é o " + setor.getNome() + ", isso está correto? (S/n)");
                    String n;
                    contador = 0;
                    while(!((n = ler.readLine()).equalsIgnoreCase("s") || n.equalsIgnoreCase("n"))) {
                        if (contador == 4) {
                            System.out.println("Cadastro cancelado, muitas tentativas.");
                            return 0;
                        }
                        System.out.println("Entre somente com uma opção válida (S/n)");
                        ++contador;
                    }
                    if (n.equalsIgnoreCase("s")) {
                        funcionario.setSetor(setor);
                        flag = false;
                    }
                    else {
                        flag = true;
                    }
                }
                funcionarios.add(funcionario);
                System.out.println("Funcionário cadastrado com sucesso");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int cadastrarSetor() {
        setor = new Setor();
        contador = 0;
        try {
            boolean flagSetor = true;
            while (flagSetor) {
                if (contador == 4) {
                    System.out.println("Cadastro cancelado, muitas tentativas.");
                    return 0;
                }
                System.out.println("Entre com o código do setor:");
                try {
                    codigo = Integer.parseInt(ler.readLine());
                    flagSetor = false;
                }
                catch (NumberFormatException e) {
                    System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                    flagSetor = true;
                    ++contador;
                }
                if(pegaSetor(codigo) != null) {
                    System.out.println("O código inserido já existe, entre com um novo código.");
                    ++contador;
                    flagSetor = true;
                }
            }
            setor.setCodigo(codigo);
            contador = 0;
            System.out.println("Entre com o nome do setor:");
            String nome;
            while((nome = ler.readLine()).length() < 4) {
                if (contador == 4) {
                    System.out.println("Cadastro cancelado, muitas tentativas realizadas.");
                    return 0;
                }
                System.out.println("Para este campo, não são aceitos valores nulos ou com menores que 4 caracteres.");
                System.out.println("Entre com o nome do setor:");
                ++contador;
            }
            setor.setNome(nome);
            setores.add(setor);
            System.out.println("Setor cadastrado com sucesso.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void listarSetor() {
        if (setores.isEmpty()) {
            System.out.println("O cadastro de setores está vazio, impossível listar.");
        }
        else {
            for (int i = 0; i < setores.size(); i++) {
                System.out.printf("Setor %s\n" , i+1);
                System.out.printf("Código: %s\n" , setores.get(i).getCodigo());
                System.out.printf("Nome: %s\n" , setores.get(i).getNome());
            }
        }
    }
    
    public int listarSetorEspecifico() throws IOException {
        int codigo = -1;
        boolean flagSetor = false;
        if (setores.isEmpty()) {
            System.out.println("O cadastro de setores está vazio, impossível listar.");
        }
        else {
            boolean flagCodigo = true;
            contador = 0;
            while(flagCodigo) {
                if (contador == 4) {
                    System.out.println("Operação cancelada, muitas tentativas.");
                    return 0;
                }
                System.out.println("Entre com o código do setor:");
                try {
                    codigo = Integer.parseInt(ler.readLine());
                    flagCodigo = false;
                }
                catch(NumberFormatException e) {
                    System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                    flagCodigo = true;
                }
                ++contador;
            }
            for (int i = 0; i < setores.size(); i++) {
                if (codigo == setores.get(i).getCodigo()) {
                    System.out.printf("Setor %s\n" , i+1);
                    System.out.printf("Código: %s\n" , setores.get(i).getCodigo());
                    System.out.printf("Nome: %s\n" , setores.get(i).getNome());
                    flagSetor = true;
                }
            }
            if (!flagSetor) {
                System.out.println("Nenhum resultado encontrado para o código " + codigo + ".");
            }
        }
        return 0;
    }
    
    public void listaFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("O cadastro de funcionários está vazio, impossível listar.");
        }
        else {
            System.out.println("Lista de Funcionários:");
            for (int i = 0; i < funcionarios.size(); i++) {
                System.out.printf("Funcionário %s:\n" , i+1);
                System.out.printf("Código: %s\n" , funcionarios.get(i).getCodigo());
                System.out.printf("Nome: %s\n" , funcionarios.get(i).getNome());
                System.out.printf("Idade: %s\n" , funcionarios.get(i).getIdade());
                System.out.printf("Setor: %s\n" , funcionarios.get(i).getSetor().getNome());
            }
        }
    }
    
    public int listaFuncionarioEspecifico() throws IOException {
        if (funcionarios.isEmpty()) {
            System.out.println("O cadastro de funcionários está vazio, impossível listar.");
        }
        else {
            int codigo = -1;
            boolean flagCodigo = true;
            contador = 0;
            while(flagCodigo) {
                if (contador == 4) {
                    System.out.println("Operação cancelada, muitas tentativas.");
                    return 0;
                }
                System.out.println("Entre com o código do funcionário:");
                try {
                    codigo = Integer.parseInt(ler.readLine());
                    flagCodigo = false;
                }
                catch (NumberFormatException e) {
                    System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                    flagCodigo = true;
                    ++contador;
                }
            }
            System.out.println("Lista de Fincionários:");
            boolean flagFunc = false;
            for (int i = 0; i < funcionarios.size(); i++) {
                if (codigo == funcionarios.get(i).getCodigo()) {
                    System.out.printf("Funcionário %s:\n" , i+1);
                    System.out.printf("Código: %s\n" , funcionarios.get(i).getCodigo());
                    System.out.printf("Nome: %s\n" , funcionarios.get(i).getNome());
                    System.out.printf("Idade: %s\n" , funcionarios.get(i).getIdade());
                    System.out.printf("Setor: %s\n" , funcionarios.get(i).getSetor().getNome());
                    flagFunc = true;
                }
                if (!flagFunc) {
                    System.out.println("Nenhum resultado encontrado para o código " + codigo + ".");
                }
            }
        }
        return 0;
    }
    
    public int deletarFuncionario() {
        if (funcionarios.isEmpty()) {
            System.out.println("O cadastro de funcionários está vazio, impossível deletar.");
        }
        else {
            try {
                boolean flagFunc = true;
                int codigo = 0;
                contador = 0;
                while(flagFunc) {
                    ++contador;
                    if (contador == 4) {
                        System.out.println("Operação cancelada, muitas tentativas realizadas.");
                        return 0;
                    }
                    System.out.println("Entre com o código do funcionário:");
                    try {
                        codigo = Integer.parseInt(ler.readLine());
                        flagFunc = false;
                    }
                    catch(NumberFormatException e) {
                        System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                        flagFunc = true;
                        ++contador;
                    }
                    while ((funcionario = pegaFuncionario(codigo)) == null) {
                        ++contador;
                        if (contador == 6) {
                            System.out.println("Operação cancelada, muitas tentativas realizadas.");
                            return 0;
                        }
                        System.out.println("Código inválido. Entre com um código válido");
                        try {
                            codigo = Integer.parseInt(ler.readLine());
                            flagFunc = false;
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Tipo de dado inválido. O tipo permitido para esse campo é inteiro.");
                            flagFunc = true;
                        }
                    }
                }
                funcionarios.remove(funcionario);
                System.out.println("Funcionário excluído.");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    private Setor pegaSetor(int codigo) {
        for (int i = 0; i < setores.size(); i++) {
            if (setores.get(i).getCodigo() == codigo) {
                return setores.get(i);
            }
        }
        return null;
    }
    
    private Funcionario pegaFuncionario(int codigo) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getCodigo() == codigo) {
                return funcionarios.get(i);
            }
        }
        return null;
    }

}