package dao;

import model.Produto;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {

    private final EntityManager em;

    public ProdutoDao(EntityManager em){
        this.em = em;
    }

    public void insert(Produto produto){
        this.em.persist(produto);
    }

    public void update(Produto produto){
        this.em.merge(produto);
    }

    public void remove(Produto produto){
        this.em.remove(produto);
    }

    public Produto getOneById(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> findAll(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> findByNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> findByNomeCategoria(String nomeCategoria){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria";
        return em.createQuery(jpql, Produto.class)
                .setParameter("nomeCategoria", nomeCategoria)
                .getResultList();
    }

    public List<Produto> findByCategoria2(String nomeCategoria){
        return em.createNamedQuery("findByCategoria2", Produto.class)
                .setParameter("nomeCategoria", nomeCategoria)
                .getResultList();
    }

    public BigDecimal findPrecoProdutoByNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Produto> findWithCriteria(String nome, BigDecimal preco, LocalDate data){

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);
        Predicate filters = builder.and();

        if(nome != null && !nome.trim().isEmpty()){
            filters = builder.and(filters, builder.like(from.get("nome"),"%" + nome + "%"));
        }

        if(preco != null) {
            filters = builder.and(filters, builder.equal(from.get("preco"), preco));
        }

        if(data != null) {
            filters = builder.and(filters, builder.equal(from.get("cadastro"), data));
        }

        query.where(filters);
        return em.createQuery(query).getResultList();
    }
}
