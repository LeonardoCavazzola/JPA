package dao;

import model.Cliente;

import javax.persistence.EntityManager;

public class ClienteDao {

    private final EntityManager em;

    public ClienteDao(EntityManager em){
        this.em = em;
    }

    public void insert(Cliente cliente){
        this.em.persist(cliente);
    }

    public void update(Cliente cliente){
        this.em.merge(cliente);
    }

    public void remove(Cliente cliente){
        this.em.remove(cliente);
    }

    public Cliente getOneById(Long id){
        return em.find(Cliente.class, id);
    }
}
