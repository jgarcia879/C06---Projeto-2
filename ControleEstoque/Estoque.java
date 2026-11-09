import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Estoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    public void cadastrar(Produto produto) {
        if (buscar(produto.getNome()) != null)
            throw new IllegalArgumentException("Produto ja cadastrado.");
        produtos.add(produto);
    }

    public Produto buscar(String nome) {
        for (Produto p : produtos)
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        return null;
    }

    public void listar(boolean apenasBaixos) {
        boolean encontrou = false;
        for (Produto p : produtos) {
            if (!apenasBaixos || p.estoqueBaixo()) {
                System.out.println(p + (p.estoqueBaixo() ? " | ESTOQUE BAIXO" : ""));
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto encontrado.");
    }

    public void salvar() throws IOException {
        ArrayList<String> linhas = new ArrayList<>();
        for (Produto p : produtos)
            linhas.add(p.getTipo() + ";" + p.getNome() + ";" + p.getQuantidade());
        Files.write(Path.of("produtos.txt"), linhas);
    }

    public void carregar() throws IOException {
        Path arquivo = Path.of("produtos.txt");
        if (!Files.exists(arquivo)) return;
        for (String linha : Files.readAllLines(arquivo)) {
            String[] partes = linha.split(";");
            if (partes.length != 3) continue;
            int quantidade = Integer.parseInt(partes[2]);
            if (quantidade < 0) continue;
            Produto p = partes[0].equals("Perecivel")
                ? new ProdutoPerecivel(partes[1], quantidade)
                : new ProdutoComum(partes[1], quantidade);
            cadastrar(p);
        }
    }
}
