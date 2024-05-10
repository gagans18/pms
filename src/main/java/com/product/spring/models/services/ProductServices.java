package com.product.spring.models.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.spring.models.dtos.Product;
import com.product.spring.models.respository.RepositoryContract;

@Service
public class ProductServices implements ServiceContract<Product, Integer> {

	@Autowired
	public RepositoryContract<Product, Integer> _repository;

	@Override
	public List<Product> fetchRecords(Integer sortChoice) throws ClassNotFoundException, SQLException, Exception {

		try {
			List<Product> listProduct = _repository.getRecords();
			List<Product> sortProduct = null;
			switch (sortChoice) {
			case 1: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> Integer.compare(e1.getProduct_id(), e2.getProduct_id())).toList();
				break;

			}
			case 2: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> e1.getProduct_name().compareTo(e2.getProduct_name())).toList();
				break;
			}

			case 3: {

				sortProduct = listProduct.stream().sorted((e1, e2) -> Float.compare(e1.getPrice(), e2.getPrice()))
						.toList();
				break;
			}
			case 4: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> e1.getDescription().compareTo(e2.getDescription())).toList();
				break;
			}
			case 5: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> e1.getProduct_code().compareTo(e2.getProduct_code())).toList();
				break;
			}
			case 6: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> e1.getRelease_date().compareTo(e2.getRelease_date())).toList();
				break;
			}
			case 7: {

				sortProduct = listProduct.stream().sorted((e1, e2) -> e1.getImage_url().compareTo(e2.getImage_url()))
						.toList();
				break;
			}
			case 8: {

				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> Float.compare(e1.getStar_rating(), e2.getStar_rating())).toList();
				break;

			}
			default:
				sortProduct = listProduct.stream()
						.sorted((e1, e2) -> Integer.compare(e1.getProduct_id(), e2.getProduct_id())).toList();
				break;

			}

			return sortProduct;
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Product fetchRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		Product product = null;
		try {
			product = _repository.getRecord(id);
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return product;
	}

	@Override
	public Integer insertRecord(Product product) throws ClassNotFoundException, SQLException, Exception {

		int res = 0;

		try {
			res = _repository.addRecord(product);

		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return res;
	}

	@Override
	public Integer modifyRecord(Integer id, Product product) throws ClassNotFoundException, SQLException, Exception {
		int res = 0;
		try {
			res = _repository.updateRecord(id, product);
		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return res;
	}

	@Override
	public Integer removeRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		int res = 0;
		try {
			res = _repository.deleteRecord(id);

		} catch (ClassNotFoundException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return res;
	}

}
