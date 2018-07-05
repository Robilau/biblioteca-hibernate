package Infra;

import Dominio.Endereco;
import java.util.List;
import javax.persistence.EntityManager;

public class EnderecoDAO {
    
    private EntityManager manager;

    public EnderecoDAO(EntityManager manager) {
        this.manager = manager;
    }
    
    public Endereco Adicionar (Endereco endereco){
        manager.getTransaction().begin();
        manager.persist(endereco);
        manager.getTransaction().commit();
        return endereco;
    }
    
    public Endereco Atualizar (Endereco endereco){
        manager.getTransaction().begin();
        manager.merge(endereco);
        manager.getTransaction().commit();   
        return endereco;
    }
    
    public Endereco Pegar (int id){
        manager.getTransaction().begin();
        Endereco endereco = manager.find(Endereco.class, id);
        manager.getTransaction().commit();
        return endereco;
    }
    
    public List<Endereco> PegarTodos(){
        manager.getTransaction().begin();
        List<Endereco> enderecos = manager.createQuery("FROM " + Endereco.class.getName()).getResultList();
        manager.getTransaction().commit();
        return enderecos;
    }
    
    public void deletar (int id){
        Endereco endereco = Pegar(id);
        manager.getTransaction().begin();        
        manager.remove(endereco);
        manager.getTransaction().commit();
    }
}
