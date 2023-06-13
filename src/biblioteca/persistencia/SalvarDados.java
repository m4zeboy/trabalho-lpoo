package biblioteca.persistencia;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SalvarDados {
  public static void salvarUsuarios(ArrayList<Usuario> usuarios) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("usuarios.data"));
      arq.writeObject(usuarios);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  public static void salvarAcervo(ArrayList<Exemplar> acervo) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("acervo.data"));
      arq.writeObject(acervo);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  public static void salvarCateogorias(ArrayList<Categoria> categorias) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("categorias.data"));
      arq.writeObject(categorias);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  public static void salvarEmprestimos(ArrayList<Emprestimo> emprestimos) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("emprestimos.data"));
      arq.writeObject(emprestimos);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
  public static void salvarReservas(ArrayList<Reserva> reservas) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("reservas.data"));
      arq.writeObject(reservas);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static void salvarMultas(ArrayList<Multa> multas) {
    try {
      ObjectOutputStream arq = new ObjectOutputStream(new FileOutputStream("multas.data"));
      arq.writeObject(multas);
      arq.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
