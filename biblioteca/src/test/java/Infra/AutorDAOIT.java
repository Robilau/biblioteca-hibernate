/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infra;

import Dominio.Autor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author T0KS1CK
 */
public class AutorDAOIT {
    
    private AutorDAO dao;
    private Autor autor;
    private EntityManagerFactory factory;
    private EntityManager manager;
    
    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("biblioteca_hibernate");
        manager = factory.createEntityManager();
        dao = new AutorDAO(manager);
        SeedDatabase.seedCompleto(manager);
        autor = new Autor();
    }
    
    @After
    public void tearDown() {
        manager.close();
    }

    @Test
    public void testAdicionar() {
        autor = ObjectMother.pegarAutor();
        Autor autorRetornado = dao.Adicionar(autor);
        Assertions.assertThat(autorRetornado.getId()).isEqualTo(2);
    }

    @Test
    public void testAtualizar() {
        autor = ObjectMother.pegarAutor();
        autor.setNome("NovoNome");
        autor.setId(1); // id já existente no banco
        Autor autorRetornado = dao.Atualizar(autor);
        Assertions.assertThat(autor.getNome()).isEqualToIgnoringCase(autorRetornado.getNome());
    }

    @Test
    public void testPegar() {
        Autor autorRetornado = dao.Pegar(1);
        Assertions.assertThat(autorRetornado).isNotNull();
        Assertions.assertThat(autorRetornado.getLivros()).isNotNull();
    }

    @Test
    public void testPegarTodos() {
        List<Autor> autors = dao.PegarTodos();
        Assertions.assertThat(autors).hasSize(1);
    }

    @Test
    public void testDeletar() {
        dao.deletar(1);
        List<Autor> autors = dao.PegarTodos();
        Assertions.assertThat(autors).hasSize(0);
    }
    
}
