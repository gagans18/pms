package com.product.spring.models.services;

import java.sql.SQLException;
import java.util.List;

public interface ServiceContract<TModel, TKey> {

	List<TModel> fetchRecords(TKey id) throws ClassNotFoundException, SQLException, Exception;

	TModel fetchRecord(TKey id) throws ClassNotFoundException, SQLException, Exception;

	Integer insertRecord(TModel model) throws ClassNotFoundException, SQLException, Exception;

	Integer modifyRecord(TKey id, TModel model) throws ClassNotFoundException, SQLException, Exception;

	Integer removeRecord(TKey id) throws ClassNotFoundException, SQLException, Exception;

}
