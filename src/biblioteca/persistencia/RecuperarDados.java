package biblioteca.persistencia;

import biblioteca.Categoria;
import biblioteca.Reserva;
import biblioteca.emprestimo.Emprestimo;
import biblioteca.emprestimo.Multa;
import biblioteca.exemplar.Exemplar;
import biblioteca.usuario.Usuario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class RecuperarDados {
  public static ArrayList<Usuario> recuperarUsuarios() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("usuarios.data"));
      try {
        return (ArrayList<Usuario>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
  public static ArrayList<Exemplar> recuperarAcervo() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("acervo.data"));
      try {
        return (ArrayList<Exemplar>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
  public static ArrayList<Categoria> recuperarCategorias() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("categorias.data"));
      try {
        return (ArrayList<Categoria>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
  public static ArrayList<Emprestimo> recuperarEmprestimos() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("emprestimos.data"));
      try {
        return (ArrayList<Emprestimo>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
  public static ArrayList<Reserva> recuperarReservas() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("reservas.data"));
      try {
        return (ArrayList<Reserva>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
  public static ArrayList<Multa> recuperarMultas() {
    try {
      ObjectInputStream arq = new ObjectInputStream(new FileInputStream("multas.data"));
      try {
        return (ArrayList<Multa>) arq.readObject();
      } catch (ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    } catch(IOException exception) {
      exception.printStackTrace();
    }
    return new ArrayList<>();
  }
}
