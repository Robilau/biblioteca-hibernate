package Infra;

import Dominio.Livro;
import java.util.List;
import javax.persistence.EntityManager;

public class LivroDAO {
    private EntityManager manager;

    public LivroDAO(EntityManager manager) {
        this.manager = manager;
    }

    public Livro Adicionar(Livro livro) {
        manager.getTransaction().begin();
        manager.persist(livro);
        manager.getTransaction().commit();
        return livro;
    }

    public Livro Atualizar(Livro livro) {
        manager.getTransaction().begin();
        manager.merge(livro);
        manager.getTransaction().commit();
        return livro;
    }

    public Livro Pegar(int id) {
        manager.getTransaction().begin();
        Livro livro = manager.find(Livro.class, id);
        manager.getTransaction().commit();
        return livro;
    }

    public List<Livro> PegarTodos() {
        manager.getTransaction().begin();
        List<Livro> livros = manager.createQuery("FROM " + Livro.class.getName()).getResultList();
        manager.getTransaction().commit();
        return livros;
    }

    public void deletar(int id) {
        Livro livro = Pegar(id);
        manager.getTransaction().begin();
        manager.remove(livro);
        manager.getTransaction().commit();
    }
}
