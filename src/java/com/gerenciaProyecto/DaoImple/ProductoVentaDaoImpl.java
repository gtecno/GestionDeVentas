/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerenciaProyecto.DaoImple;

import com.gerenciaProyecto.Dao.Producto_has_ventaDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Agandio
 */
public class ProductoVentaDaoImpl implements Producto_has_ventaDao {
    
     @PersistenceContext
    private EntityManager entityManager;
     
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
