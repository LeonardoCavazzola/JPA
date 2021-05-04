package dao;

import model.Pedido;
import vo.RelatorioVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private final EntityManager em;

    public PedidoDao(EntityManager em){
        this.em = em;
    }

    public void insert(Pedido pedido){
        this.em.persist(pedido);
    }

    public void update(Pedido pedido){
        this.em.merge(pedido);
    }

    public void remove(Pedido pedido){
        this.em.remove(pedido);
    }

    public BigDecimal getTotalPedidosValue(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<Object[]> getRelatorioFeio(){ //jeito feio
        String jpql = "SELECT " +
                "produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itemPedidoList item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade DESC ";

        return em.createQuery(jpql, Object[].class)
                .getResultList();
    }

    public List<RelatorioVo> getRelatorio(){ //jeito correto
        String jpql = "SELECT new vo.RelatorioVo(produto.nome, " +
                "SUM(item.quantidade), " +
                "MAX(pedido.data)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.itemPedidoList item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade DESC ";

        return em.createQuery(jpql, RelatorioVo.class)
                .getResultList();
    }

    public Pedido getOneByIdWithCliente(Long id) {
        String jpql = ("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id");

        return em.createQuery(jpql, Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
