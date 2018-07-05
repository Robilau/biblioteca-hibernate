package Infra;

import Dominio.Autor;
import Dominio.Editora;
import Dominio.Endereco;
import Dominio.Livro;
import java.util.ArrayList;

public class ObjectMother {

    public static Endereco pegarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("88999000");
        endereco.setComplemento("Casa");
        endereco.setNumero(264);
        return endereco;
    }

    public static Editora pegarEditora() {
        Editora editora = new Editora();
        editora.setEndereco(pegarEndereco());
        editora.setNome("Editora Abril");
        return editora;
    }
    
    public static Autor pegarAutor() {
        Autor autor = new Autor();
        autor.setNome("Adgar Allan Poe");
        return autor;
    }

    public static Livro pegarLivro() {
        Livro livro = new Livro();
        livro.setEditora(pegarEditora());
        livro.setTitulo("harry potter");
        return livro;
    }    
}
