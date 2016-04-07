package lucasdutracoelho.templatemobile.Model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

import lucasdutracoelho.templatemobile.DAO.Utils.Tables;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public class Filme implements Entidade, Serializable {

    @DatabaseField(generatedId = true)
    private Long id;
    @SerializedName("Title")
    @DatabaseField
    private String titulo;
    @SerializedName("Year")
    @DatabaseField
    private String ano;
    @SerializedName("Genre")
    @DatabaseField
    private String genero;
    @SerializedName("Runtime")
    @DatabaseField
    private String duracao;
    @SerializedName("Plot")
    @DatabaseField
    private String descricao;
    @SerializedName("Poster")
    @DatabaseField
    private String urlPoster;
    @SerializedName("Response")
    private String response;
    @SerializedName("Error")
    private String erro;

    public Filme(){};

    public Filme(String titulo, String ano, String genero, String duracao, String descricao, String urlPoster, String response, String erro) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
        this.duracao = duracao;
        this.descricao = descricao;
        this.urlPoster = urlPoster;
        this.response = response;
        this.erro = erro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Filme{");
        sb.append("Id='").append(id).append('\'');
        sb.append(", Titulo='").append(titulo).append('\'');
        sb.append(", Ano='").append(ano).append('\'');
        sb.append(", Genero='").append(genero).append('\'');
        sb.append(", Duracao='").append(duracao).append('\'');
        sb.append(", Descricao='").append(descricao).append('\'');
        sb.append(", UrlPoster='").append(urlPoster).append('\'');
        sb.append(", Response='").append(response).append('\'');
        sb.append(", Erro='").append(erro).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Tables getTableClass() {
        return Tables.FILME;
    }
}
