package biblioteca;

import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public class Semente {
  public static void semear(ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo) {
    Aluno moises = new Aluno("Moises","1234",1);
    Servidor henrique = new Servidor("Henrique", "3245",3);
    Servidor joao = new Servidor("João", "2643",4);
    Aluno rafael = new Aluno("Rafael", "8376",2);

    usuarios.add(moises);
    usuarios.add(henrique);
    usuarios.add(joao);
    usuarios.add(rafael);

    Livro senhorDosAneis = new Livro("O Senhor dos Anéis", 1954);
    Midia homemAranha3 = new Midia("Homem Aranha III", "DVD");
    Digital ebookHabitosAtomicos = new Digital("Hábitos Atomicos");
    Livro jobs = new Livro("Steve Jobs", 2011);
    Midia bastardosInglorios = new Midia("Bastardos Inglorios", "DVD");

    acervo.add(senhorDosAneis);
    acervo.add(homemAranha3);
    acervo.add(ebookHabitosAtomicos);
    acervo.add(bastardosInglorios);
    acervo.add(jobs);

  }
}
