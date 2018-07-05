package Infra;

import Dominio.Autor;
import java.util.List;
import javax.persistence.EntityManager;

public class AutorDAO {
    private EntityManager manager;

    public AutorDAO(EntityManager manager) {
        this.manager = manager;
    }

    public Autor Adicionar(Autor autor) {
        manager.getTransaction().begin();
        manager.persist(autor);
        manager.getTransaction().commit();
        return autor;
    }

    public Autor Atualizar(Autor autor) {
        manager.getTransaction().begin();
        manager.merge(autor);
        manager.getTransaction().commit();
        return autor;
    }

    public Autor Pegar(int id) {
        manager.getTransaction().begin();
        Autor autor = manager.find(Autor.class, id);
        manager.getTransaction().commit();
        return autor;
    }

    public List<Autor> PegarTodos() {
        manager.getTransaction().begin();
        List<Autor> autors = manager.createQuery("FROM " + Autor.class.getName()).getResultList();
        manager.getTransaction().commit();
        return autors;
    }

    public void deletar(int id) {
        Autor autor = Pegar(id);
        manager.getTransaction().begin();
        manager.remove(autor);
        manager.getTransaction().commit();
    }
}
