package Infra;

import Dominio.Editora;
import java.util.List;
import javax.persistence.EntityManager;

public class EditoraDAO {

    private EntityManager manager;

    public EditoraDAO(EntityManager manager) {
        this.manager = manager;
    }

    public Editora Adicionar(Editora editora) {
        manager.getTransaction().begin();
        manager.persist(editora);
        manager.getTransaction().commit();
        return editora;
    }

    public Editora Atualizar(Editora editora) {
        manager.getTransaction().begin();
        manager.merge(editora);
        manager.getTransaction().commit();
        return editora;
    }

    public Editora Pegar(int id) {
        manager.getTransaction().begin();
        Editora editora = manager.find(Editora.class, id);
        manager.getTransaction().commit();
        return editora;
    }

    public List<Editora> PegarTodos() {
        manager.getTransaction().begin();
        List<Editora> editoras = manager.createQuery("FROM " + Editora.class.getName()).getResultList();
        manager.getTransaction().commit();
        return editoras;
    }

    public void deletar(int id) {
        Editora editora = Pegar(id);
        manager.getTransaction().begin();
        manager.remove(editora);
        manager.getTransaction().commit();
    }
}
