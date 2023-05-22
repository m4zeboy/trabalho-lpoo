package biblioteca.exemplar;

public class Livro extends Exemplar {
    private int ano;

    public Livro(String titulo, int ano) {
        super(titulo);
        this.ano = ano;
    }
    public Livro(String titulo, String ano) {
        super(titulo);
        this.ano = Integer.parseInt(ano);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String toString() {
        String saida = super.toString();
        saida += "Ano: " + ano + "\n";
        return saida;
    }
}
