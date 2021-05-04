package dao;

import model.Categoria;
import javax.persistence.EntityManager;

public class CategoriaDao {

    private final EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }

    public void insert(Categoria categoria){
        this.em.persist(categoria);
    }

    public void update(Categoria categoria){
        this.em.merge(categoria);
    }
}
