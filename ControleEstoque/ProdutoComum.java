
public class ProdutoComum extends Produto {

    public ProdutoComum(String nome, int quantidade) {
        super(nome, quantidade);
    }

    @Override
    public String getTipo() {
        return "Comum";
    }

    @Override
    public int getMinimo() {
        return 5;
    }
}
