package lucasdutracoelho.templatemobile.DAO;

import android.content.Context;

import org.androidannotations.annotations.EBean;

import lucasdutracoelho.templatemobile.Model.Filme;

/**
 * Created by lucas.coelho.dutra on 10/03/2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class FilmeDAO extends BaseDAO<Filme, Long> {

    //Não invocar o método, utilizar a anotação @Bean
    protected FilmeDAO(final Context context) {
        super(Filme.class, context);
    }
}
