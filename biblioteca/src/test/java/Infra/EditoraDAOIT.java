package Infra;

import Dominio.Editora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EditoraDAOIT {
    
    private EditoraDAO dao;
    private Editora editora;
    private EntityManagerFactory factory;
    private EntityManager manager;
    
    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("biblioteca_hibernate");
        manager = factory.createEntityManager();
        dao = new EditoraDAO(manager);
        SeedDatabase.seedEditora(manager);
        editora = new Editora();
    }
    
    @After
    public void tearDown() {
        manager.close();
    }

    @Test
    public void testAdicionar() {
        editora = ObjectMother.pegarEditora();
        Editora editoraRetornado = dao.Adicionar(editora);
        Assertions.assertThat(editoraRetornado.getId()).isEqualTo(2);
    }

    @Test
    public void testAtualizar() {
        editora = ObjectMother.pegarEditora();
        editora.setNome("NovoNome");
        editora.setId(1); // id já existente no banco
        Editora editoraRetornado = dao.Atualizar(editora);
        Assertions.assertThat(editora.getNome()).isEqualToIgnoringCase(editoraRetornado.getNome());
    }

    @Test
    public void testPegar() {
        Editora editoraRetornado = dao.Pegar(1);
        Assertions.assertThat(editoraRetornado).isNotNull();
        Assertions.assertThat(editoraRetornado.getEndereco()).isNotNull();
    }

    @Test
    public void testPegarTodos() {
        List<Editora> editoras = dao.PegarTodos();
        Assertions.assertThat(editoras).hasSize(1);
    }

    @Test
    public void testDeletar() {
        dao.deletar(1);
        List<Editora> editoras = dao.PegarTodos();
        Assertions.assertThat(editoras).hasSize(0);
    }
    
}
