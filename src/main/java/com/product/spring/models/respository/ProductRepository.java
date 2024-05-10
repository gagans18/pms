package com.product.spring.models.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.product.spring.models.dtos.Product;
import com.product.spring.utility.DbQueries;
import com.product.spring.utility.DbUtility;

@Repository
public class ProductRepository implements RepositoryContract<Product, Integer> {

	@Override
	public List<Product> getRecords() throws ClassNotFoundException, SQLException, Exception {
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		List<Product> listProduct = null;
		try {

			con = DbUtility.createConncection();
			stmt = con.createStatement();
			res = stmt.executeQuery(DbQueries.SELECT_ALL_QUERY);
			if (res != null) {
				listProduct = new ArrayList<Product>();
				while (res.next()) {
					int id = res.getInt("product_id");
					String name = res.getString("product_name");
					float price = res.getFloat("Price");
					String desc = res.getString("description");
					String productCode = res.getString("product_code");
					String releaseDate = res.getString("release_date");
					String imgUrl = res.getString("image_url");
					float starRating = res.getFloat("star_rating");
					Product product = new Product(id, name, price, desc, productCode, releaseDate, imgUrl, starRating);
					listProduct.add(product);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		return listProduct;

	}

	@Override
	public Product getRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet res = null;
		Product product = null;
		try {

			con = DbUtility.createConncection();
			stmt = con.prepareStatement(DbQueries.SELECT_SINGLE_QUERY);
			stmt.setInt(1, id);
			res = stmt.executeQuery();
			while (res.next()) {
				int pid = res.getInt("product_id");
				String name = res.getString("product_name");
				float price = res.getFloat("Price");
				String desc = res.getString("description");
				String productCode = res.getString("product_code");
				String releaseDate = res.getString("release_date");
				String imgUrl = res.getString("image_url");
				float starRating = res.getFloat("star_rating");
				product = new Product(pid, name, price, desc, productCode, releaseDate, imgUrl, starRating);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}
		return product;

	}

	@Override
	public Integer addRecord(Product product) throws ClassNotFoundException, SQLException, Exception {

		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = DbUtility.createConncection();
			stmt = con.prepareStatement(DbQueries.INSERT_QUERY);
			stmt.setInt(1, product.getProduct_id());
			stmt.setString(2, product.getProduct_name());
			stmt.setFloat(3, product.getPrice());
			stmt.setString(4, product.getDescription());
			stmt.setString(5, product.getProduct_code());
			stmt.setString(6, product.getRelease_date());
			stmt.setString(7, product.getImage_url());
			stmt.setFloat(8, product.getStar_rating());
			int res = stmt.executeUpdate();

			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}

	}

	@Override
	public Integer updateRecord(Integer id, Product product) throws ClassNotFoundException, SQLException, Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DbUtility.createConncection();
			stmt = con.prepareStatement(DbQueries.UPDATE_QUERY);
			stmt.setString(1, product.getProduct_name());
			stmt.setFloat(2, product.getPrice());
			stmt.setString(3, product.getDescription());
			stmt.setString(4, product.getProduct_code());
			stmt.setString(5, product.getRelease_date());
			stmt.setString(6, product.getImage_url());
			stmt.setFloat(7, product.getStar_rating());
			stmt.setInt(8, id);
			int res = stmt.executeUpdate();
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}

	}

	@Override
	public Integer deleteRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {

			con = DbUtility.createConncection();
			stmt = con.prepareStatement(DbQueries.DELETE_QUERY);
			stmt.setInt(1, id);
			int res = stmt.executeUpdate();
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (con != null)
				con.close();
		}

	}

}
