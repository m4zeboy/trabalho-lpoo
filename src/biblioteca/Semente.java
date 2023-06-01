package biblioteca;

import biblioteca.emprestimo.Emprestimo;
import biblioteca.exemplar.Digital;
import biblioteca.exemplar.Exemplar;
import biblioteca.exemplar.Livro;
import biblioteca.exemplar.Midia;
import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Semente {
  public static void semear(ArrayList<Usuario> usuarios, ArrayList<Exemplar> acervo, ArrayList<Categoria> categorias, ArrayList<Emprestimo> emprestimos) {
    String[] nomes = {
            "Aaron Hicks",
            "Peggy Brown",
            "Deborah Orozco",
            "Jerry Hensley",
            "Anna Smith",
            "Shirley Stevens",
            "Sheila Morris",
            "Christine Anderson",
            "Deborah Olson",
            "Samantha Yates",
            "Kathleen Rush",
            "Jennifer Bennett",
            "Ryan Roth",
            "Michael Lowe",
            "Susan Warren",
            "Terri Macdonald",
            "Sabrina Blair",
            "Anthony Powell",
            "Jane Gordon",
            "Dennis Robertson"
    };

    for(int i = 0; i < nomes.length; i++) {
      if(i % 2 == 0) {
        Aluno aluno = new Aluno(i + 1, nomes[i],Integer.toString((i + 1 )* 100), (i + 1) * 10);
        usuarios.add(aluno);
      } else {
        Servidor servidor = new Servidor(i + 1, nomes[i], Integer.toString((i + 1) * 100), (i + 1)* 10);
        usuarios.add(servidor);
      }
    }
    String[] nomesCategorias = {
            "Fantasia",
            "Ficção científica",
            "Distopia",
            "Ação e aventura",
            "Ficção Policial",
            "Horror",
            "Thriller e Suspense",
            "Ficção histórica",
            "Romance",
            "Novela",
            "Ficção Feminina",
            "LGBTQ+",
            "Ficção Contemporânea",
            "Realismo mágico",
            "Graphic Novel",
            "Conto",
            "Young adult – Jovem adulto",
            "New adult – Novo Adulto",
            "Infantil",
            "Memórias e autobiografia",
            "Biografia",
            "Gastronomia",
            "Arte e Fotografia",
            "Autoajuda",
            "História",
            "Viagem",
            "Crimes Reais",
            "Humor",
            "Ensaios",
            "Guias & Como fazer ",
            "Religião e Espiritualidade",
            "Humanidades e Ciências Sociais",
            "Paternidade e família",
            "Tecnologia e Ciência"

    };

    for(String nomeCategoria: nomesCategorias) {
      Categoria categoria = new Categoria(nomeCategoria);
      categorias.add(categoria);
    }

    String[] nomesExemplares = {
            "Forsaken By My Past",
            "Inventing Nightmares",
            "Admiring The Titans",
            "Driving Into The Ocean",
            "Walking Technology",
            "Dead In The Animals",
            "Visiting My Nightmares",
            "Battle Of My Friends",
            "Dwelling In The Forest",
            "Breaking The Titans",
            "Possessed By My Enemies",
            "Growing In History",
            "Scared At My End",
            "Choking In The West",
            "Life At The Light",
            "Symbols In My Family",
            "Crying In The Town",
            "Whispers Of The Leaders",
            "Battling In The Nation",
            "Painting The Elements",
            "Rescue In My Leader",
            "Sailing Into The Fires",
            "Losing The Shadows",
            "Shelter At The Animals"
    };

    for(int i = 0; i < nomesExemplares.length; i++) {
      if(i % 7 == 0) {
        Digital digital = new Digital(i + 1, nomesExemplares[i]);
        for(int j = i; j < i + 2; j++) {
          Categoria cat = categorias.get(j);
          digital.adicionarCategoria(cat);
        }
        acervo.add(digital);
      } else if(i % 2 == 0) {
        Livro livro = new Livro(i+ 1, nomesExemplares[i], new Random().nextInt(1800, 2023));
        for(int j = i; j < i + 2; j++) {
          Categoria cat = categorias.get(j);
          livro.adicionarCategoria(cat);
        }
        acervo.add(livro);
      } else {
        Midia midia = new Midia(i+ 1, nomesExemplares[i], "DVD");
        for(int j = i; j < i + 2; j++) {
          Categoria cat = categorias.get(j);
          midia.adicionarCategoria(cat);
        }
        acervo.add(midia);
      }
    }

    for(int i = 0; i < 10; i++) {
      emprestimos.add(new Emprestimo(i+1,usuarios.get(i), acervo.get(i)));
    }
  }
}
