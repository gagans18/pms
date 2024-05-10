package com.product.spring.models.respository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryContract<TModel, TKey> {

	List<TModel> getRecords() throws ClassNotFoundException, SQLException, Exception;

	TModel getRecord(TKey id) throws ClassNotFoundException, SQLException, Exception;

	Integer addRecord(TModel model) throws ClassNotFoundException, SQLException, Exception;

	Integer updateRecord(TKey id, TModel model) throws ClassNotFoundException, SQLException, Exception;

	Integer deleteRecord(TKey id) throws ClassNotFoundException, SQLException, Exception;
}
