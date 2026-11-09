public abstract class Produto implements Alertavel {
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }

    public void adicionar(int valor) {
        if (valor <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");
        quantidade += valor;
    }

    public void retirar(int valor) {
        if (valor <= 0 || valor > quantidade)
            throw new IllegalArgumentException("Quantidade invalida ou estoque insuficiente.");
        quantidade -= valor;
    }

    public abstract String getTipo();
    public abstract int getMinimo();

    @Override
    public boolean estoqueBaixo() { return quantidade < getMinimo(); }

    public String toString() {
        return nome + " | Tipo: " + getTipo() + " | Quantidade: " + quantidade;
    }
}
