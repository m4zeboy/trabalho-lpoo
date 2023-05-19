package biblioteca;

import biblioteca.usuario.Aluno;
import biblioteca.usuario.Servidor;
import biblioteca.usuario.Usuario;

import java.util.ArrayList;

public class Semente {
  public static void semear(ArrayList<Usuario> usuarios) {
    Aluno moises = new Aluno("Moises","1234",1);
    Servidor henrique = new Servidor("Henrique", "3245",3);
    Servidor joao = new Servidor("João", "2643",4);
    Aluno rafael = new Aluno("Rafael", "8376",2);

    usuarios.add(moises);
    usuarios.add(henrique);
    usuarios.add(joao);
    usuarios.add(rafael);
  }
}
