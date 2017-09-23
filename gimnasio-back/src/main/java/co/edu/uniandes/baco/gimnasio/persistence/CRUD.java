/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.persistence;

import java.util.List;

/**
 *
 * @author jc.bojaca
 * @param <T>
 */
public interface CRUD<T>{
    /**
     * crea la entity y la persiste
     * @param entity la entidad a crear
     * @return el objeto creado y persistido
     * @throws java.lang.Exception
     */
    T create(T entity)throws Exception;
    
    /**
     * busca la entidad con el id(llave primaria dado)
     * @param id el id de la entida a buscar
     * @return la entidad buscada, null en caso de no encontrarla
     * @throws java.lang.Exception
     */
    T find(Long id)throws Exception;
    
    /**
     * retorna un listado con todas las entidades
     * @return un listado con todas las entidades peristidas
     * @throws java.lang.Exception
     */
    List<T> findAll()throws Exception;
    
    /**
     * actualiza los tados asociados a una entidad
     * @param entity la entidad (con el id) a actualizar
     * @return la entidad actualizada
     * @throws java.lang.Exception
     */
    T update(T entity)throws Exception;

    /**
     * elimina una enidad con el id dado
     * @param id el id de la entida a eliminar
     * @throws java.lang.Exception
     */
    void delete(Long id)throws Exception;
}
