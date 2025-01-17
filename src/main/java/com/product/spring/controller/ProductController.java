package com.product.spring.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.spring.models.dtos.Product;
import com.product.spring.models.services.ServiceContract;

@RestController
public class ProductController {

	@Autowired
	public ServiceContract<Product, Integer> _service;

	@GetMapping(path = "/product/sort/{sortChoice}")
	@CrossOrigin("*")
	public ResponseEntity<List<Product>> getProducts(@PathVariable("sortChoice") int sortChoice)
			throws ClassNotFoundException, SQLException, Exception {
		List<Product> listProduct=null;
		try {
			listProduct = _service.fetchRecords(sortChoice);
			return ResponseEntity.ok(listProduct);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping(path = "/product/view/{id}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id)
			throws ClassNotFoundException, SQLException, Exception {
		try {
			Product product = _service.fetchRecord(id);
			return ResponseEntity.ok(product);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}

	}

	@PostMapping(path = "product/add")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Integer> addProduct(@RequestBody Product product)
			throws ClassNotFoundException, SQLException, Exception {

		try {
			Integer res = _service.insertRecord(product);
			if (res > 0) {
				return ResponseEntity.of(Optional.of(res)).status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.of(Optional.empty()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (

		ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(path = "/product/update/{eid}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Integer> updateEmployee(@RequestBody Product product, @PathVariable("eid") int id)
			throws ClassNotFoundException, SQLException, Exception {
		try {
			int res = _service.modifyRecord(id, product);
			if (res > 0) {
				return ResponseEntity.of(Optional.of(res)).status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.of(Optional.empty()).status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(path = "/product/delete/{eid}")
	@CrossOrigin(origins = "*")
	public ResponseEntity<Integer> deleteEmplpoyeeById(@PathVariable("eid") int eid)
			throws ClassNotFoundException, SQLException {
		try {
			int res = _service.removeRecord(eid);
			return ResponseEntity.ok(res);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
}