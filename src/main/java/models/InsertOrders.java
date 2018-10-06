package models;

import database.QueryBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class InsertOrders implements IInsert {
    private static final String TABLE_NAME = "orders";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_PRODUCT_ID = "product_id";

    private static final String PARAM_ID = "idProduct";
    private static final String PARAM_CLIENT_NAME = "clientName";
    private static final String PARAM_CLIENT_PHONE = "clientPhoneNumber";
    private static final String PARAM_CLIENT_COMMENT = "clientMessage";


    @Override
    public List<String> neutralizeParams(HttpServletRequest req) {
        List<String> paramsList = new ArrayList<String>();
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_ID)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_NAME)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_PHONE)));
        paramsList.add(Neutralizer.neutralize(req.getParameter(PARAM_CLIENT_COMMENT)));
        return paramsList;
    }

    @Override
    public String buildQuery(String[] params) {
        return QueryBuilder.buildInsertPreparedQuery(TABLE_NAME,
                new String[]{COLUMN_NAME, COLUMN_PHONE, COLUMN_COMMENT, COLUMN_PRODUCT_ID},
                params);
    }

    @Override
    public void insertToDatabase(String query) {

    }
}
