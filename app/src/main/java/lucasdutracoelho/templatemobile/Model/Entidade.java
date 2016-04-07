package lucasdutracoelho.templatemobile.Model;

import java.io.Serializable;

import lucasdutracoelho.templatemobile.DAO.Utils.Tables;

/**
 * Created by lucas.coelho.dutra on 29/02/2016.
 */
public interface Entidade extends Serializable {

    Tables getTableClass();
}
