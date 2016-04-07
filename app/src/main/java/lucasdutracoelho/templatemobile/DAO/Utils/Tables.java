package lucasdutracoelho.templatemobile.DAO.Utils;

import lucasdutracoelho.templatemobile.Model.Filme;

/**
 * Created by lucas.coelho.dutra on 29/02/2016.
 */
public enum Tables {

    FILME(Filme.class);

    Tables(Class tableClass) {
        this.tableClass = tableClass;
    }

    private Class tableClass;

    public Class getTableClass() {
        return tableClass;
    }

    public void setTableClass(Class tableClass) {
        this.tableClass = tableClass;
    }
}
