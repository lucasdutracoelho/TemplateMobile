package lucasdutracoelho.templatemobile.Manager;

import java.util.List;

import lucasdutracoelho.templatemobile.Model.Filme;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
public interface FilmeManager {

    Filme pesquisar(String nome, String ano, String plot);
    void inserir(Filme filme);
    void deletar(Filme filme);
    List<Filme> listarFilmes();

}
