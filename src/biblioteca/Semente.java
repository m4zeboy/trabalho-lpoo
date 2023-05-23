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
  public static void semear(ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias) {
    Aluno moises = new Aluno("Moises","1234",1);
    Servidor henrique = new Servidor("Henrique", "3245",3);
    Servidor joao = new Servidor("João", "2643",4);
    Aluno rafael = new Aluno("Rafael", "8376",2);

    usuarios.add(moises);
    usuarios.add(henrique);
    usuarios.add(joao);
    usuarios.add(rafael);

    Categoria drama = new Categoria("drama");
    Categoria acao = new Categoria("ação");
    Categoria romance = new Categoria("romance");
    Categoria fantasia = new Categoria("fantasia");
    Categoria desPessoal = new Categoria("desenvolvimento pessoal");
    Categoria biografia = new Categoria("biografia");

    categorias.add(drama);
    categorias.add(romance);
    categorias.add(acao);
    categorias.add(fantasia);

    Livro senhorDosAneis = new Livro("O Senhor dos Anéis", 1954);
    senhorDosAneis.adicionarCategoria(fantasia);

    Midia homemAranha3 = new Midia("Homem Aranha III", "DVD");
    homemAranha3.adicionarCategoria(acao);

    Digital ebookHabitosAtomicos = new Digital("Hábitos Atomicos");
    ebookHabitosAtomicos.adicionarCategoria(desPessoal);

    Livro jobs = new Livro("Steve Jobs", 2011);
    jobs.adicionarCategoria(biografia);

    Midia bastardosInglorios = new Midia("Bastardos Inglorios", "DVD");
    bastardosInglorios.adicionarCategoria(acao);
    bastardosInglorios.adicionarCategoria(drama);

    acervo.add(senhorDosAneis);
    acervo.add(homemAranha3);
    acervo.add(ebookHabitosAtomicos);
    acervo.add(bastardosInglorios);
    acervo.add(jobs);

  }
}
