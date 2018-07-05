package Infra;

import Dominio.Endereco;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnderecoDAOIT {
    private EnderecoDAO dao;
    private Endereco endereco;
    private EntityManagerFactory factory;
    private EntityManager manager;
    
    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("biblioteca_hibernate");
        manager = factory.createEntityManager();
        dao = new EnderecoDAO(manager);
        SeedDatabase.seedEndereco(manager);
        endereco = new Endereco();
    }
    
    @After
    public void tearDown() {
        manager.close();
    }

    @Test
    public void testAdicionar() {
        endereco = ObjectMother.pegarEndereco();
        Endereco enderecoRetornado = dao.Adicionar(endereco);
        Assertions.assertThat(enderecoRetornado.getId()).isEqualTo(2);
    }

    @Test
    public void testAtualizar() {
        endereco = ObjectMother.pegarEndereco();
        endereco.setComplemento("NovoComplemento");
        endereco.setId(1); // id já existente no banco
        Endereco enderecoRetornado = dao.Atualizar(endereco);
        Assertions.assertThat(endereco.getComplemento()).isEqualToIgnoringCase(enderecoRetornado.getComplemento());
    }

    @Test
    public void testPegar() {
        Endereco enderecoRetornado = dao.Pegar(1);
        Assertions.assertThat(enderecoRetornado).isNotNull();
    }

    @Test
    public void testPegarTodos() {
        List<Endereco> enderecos = dao.PegarTodos();
        Assertions.assertThat(enderecos).hasSize(1);
    }

    @Test
    public void testDeletar() {
        dao.deletar(1);
        List<Endereco> enderecos = dao.PegarTodos();
        Assertions.assertThat(enderecos).hasSize(0);
    }
    
}
