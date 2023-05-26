package biblioteca.emprestimo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

public class Multa {
    private int id;
    private LocalDateTime data;
    private double valor;
    private int diasAtraso;
    private Emprestimo emprestimo;

    public Multa(Emprestimo emprestimo) {
        this.id = new Random().nextInt(10000);
        this.diasAtraso = Period.between(emprestimo.getVencimento(), emprestimo.getDataDevolucao()).getDays();
        this.emprestimo = emprestimo;
        this.data = LocalDateTime.now();
        this.valor = Emprestimo.valorPorDiaDeAtraso * diasAtraso;
    }

    public double getValor() {
        return valor;
    }

    public String toString() {
        String saida = "Multa #" + id + "\n";
        saida += "Data: " + data + "\n";
        saida += "Valor: " + valor + "\n";
        saida += "Empréstimo: " + emprestimo.getId() + "\n";
        saida += "Dias em atraso: " + diasAtraso + "\n";
        return saida;
    }
}
