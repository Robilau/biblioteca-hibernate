package Infra;

import Dominio.Livro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivroDAOIT {
    
    private LivroDAO dao;
    private Livro livro;
    private EntityManagerFactory factory;
    private EntityManager manager;
    
    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory("biblioteca_hibernate");
        manager = factory.createEntityManager();
        dao = new LivroDAO(manager);
        SeedDatabase.seedCompleto(manager);
        livro = new Livro();
    }
    
    @After
    public void tearDown() {
        manager.close();
    }

    @Test
    public void testAdicionar() {
        livro = ObjectMother.pegarLivro();
        Livro livroRetornado = dao.Adicionar(livro);
        Assertions.assertThat(livroRetornado.getId()).isEqualTo(2);
    }

    @Test
    public void testAtualizar() {
        livro = ObjectMother.pegarLivro();
        livro.setTitulo("NovoNome");
        livro.setId(1); // id já existente no banco
        Livro livroRetornado = dao.Atualizar(livro);
        Assertions.assertThat(livro.getTitulo()).isEqualToIgnoringCase(livroRetornado.getTitulo());
    }

    @Test
    public void testPegar() {
        Livro livroRetornado = dao.Pegar(1);
        Assertions.assertThat(livroRetornado).isNotNull();
        Assertions.assertThat(livroRetornado.getEditora()).isNotNull();
        Assertions.assertThat(livroRetornado.getAutores()).isNotNull();
        Assertions.assertThat(livroRetornado.getEditora().getEndereco()).isNotNull();
    }

    @Test
    public void testPegarTodos() {
        List<Livro> livros = dao.PegarTodos();
        Assertions.assertThat(livros).hasSize(1);
    }

    @Test
    public void testDeletar() {
        dao.deletar(1);
        List<Livro> livros = dao.PegarTodos();
        Assertions.assertThat(livros).hasSize(0);
    }
    
}
