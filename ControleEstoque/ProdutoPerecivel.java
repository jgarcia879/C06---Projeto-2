
public class ProdutoPerecivel extends Produto {

    public ProdutoPerecivel(String nome, int quantidade) {
        super(nome, quantidade);
    }

    @Override
    public String getTipo() {
        return "Perecivel";
    }

    @Override
    public int getMinimo() {
        return 5;
    }
}
