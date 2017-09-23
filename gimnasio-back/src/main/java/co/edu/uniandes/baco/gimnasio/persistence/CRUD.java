/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author jc.bojaca
 * @param <T>
 */
public interface CRUD<T>{
	T create(T entity);
	T find(Long id);
        List<T> findAll();
	T update(T entity);
	void delete(Long id);
}
