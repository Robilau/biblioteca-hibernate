package Infra;

import Dominio.Autor;
import Dominio.Editora;
import Dominio.Endereco;
import Dominio.Livro;
import java.util.ArrayList;
import javax.persistence.EntityManager;

public class SeedDatabase {

    public static void seedEndereco(EntityManager manager) {
        manager.getTransaction().begin();
        manager.persist(ObjectMother.pegarEndereco());
        manager.getTransaction().commit();
    }

    public static void seedEditora(EntityManager manager) {
        manager.getTransaction().begin();
        manager.persist(ObjectMother.pegarEditora());
        manager.getTransaction().commit();
    }

    public static void seedCompleto(EntityManager manager) {
        Livro livro = ObjectMother.pegarLivro();
        Autor autor = ObjectMother.pegarAutor();
        manager.getTransaction().begin();
        manager.persist(autor);
        livro.getAutores().add(autor);
        livro.getEditora().getLivros().add(livro);
        manager.persist(livro);
        autor.getLivros().add(livro);
        manager.merge(autor);
        manager.getTransaction().commit();
    }
}
