package biblioteca.exemplar;

public class Midia extends Exemplar {
    private String tipoArquivo;

    public Midia(int id,String titulo, String tipoArquivo) {
        super(id,titulo);
        this.tipoArquivo = tipoArquivo;
    }
    public Midia(String titulo, String tipoArquivo) {
        super(titulo);
        this.tipoArquivo = tipoArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String toString() {
        String saida = super.toString();
        saida += "Tipo de Arquivo: " + tipoArquivo + "\n";
        return saida;
    }
}
