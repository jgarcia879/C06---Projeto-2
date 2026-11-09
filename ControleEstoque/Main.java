
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Estoque estoque = new Estoque();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=== CONTROLE DE ESTOQUE ===");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Adicionar estoque");
            System.out.println("4 - Retirar estoque");
            System.out.println("5 - Verificar estoque baixo");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            try {

                opcao = Integer.parseInt(entrada.nextLine());

                if (opcao == 1) {

                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();

                    if (nome.equals("") || nome.equals(";")) {
                        throw new IllegalArgumentException("Nome invalido.");
                    }

                    System.out.print("Quantidade inicial: ");
                    int qtd = Integer.parseInt(entrada.nextLine());

                    if (qtd < 0) {
                        throw new IllegalArgumentException("Quantidade negativa.");
                    }

                    System.out.print("Tipo (1-Comum, 2-Perecivel): ");
                    int tipo = Integer.parseInt(entrada.nextLine());

                    if (tipo != 1 && tipo != 2) {
                        throw new IllegalArgumentException("Tipo invalido.");
                    }

                    Produto produto;

                    if (tipo == 1) {
                        produto = new ProdutoComum(nome, qtd);
                    } else {
                        produto = new ProdutoPerecivel(nome, qtd);
                    }

                    estoque.cadastrar(produto);

                    System.out.println("Produto cadastrado!");

                } else if (opcao == 2) {

                    estoque.listar(false);

                } else if (opcao == 3 || opcao == 4) {

                    System.out.print("Nome do produto: ");
                    String nome = entrada.nextLine();

                    Produto p = estoque.buscar(nome);

                    if (p == null) {
                        throw new IllegalArgumentException("Produto nao encontrado.");
                    }

                    System.out.print("Quantidade: ");
                    int qtd = Integer.parseInt(entrada.nextLine());

                    if (opcao == 3) {
                        p.adicionar(qtd);
                    } else {
                        p.retirar(qtd);
                    }

                    System.out.println("Estoque atualizado!");

                } else if (opcao == 5) {

                    estoque.listar(true);

                } else if (opcao == 0) {

                    System.out.println("Ate logo!");

                } else {

                    System.out.println("Opcao invalida.");
                }

            } catch (Exception e) {

                System.out.println("Erro: " + e.getMessage());
            }
        }

        entrada.close();
    }
}
