import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args) {
        popular();

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CursoJpa");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        ClienteDao clienteDao = new ClienteDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Cliente cliente = clienteDao.getOneById(1L);

        Produto produto = produtoDao.getOneById(1L);

        Pedido pedido = new Pedido(cliente);
        pedido.addItem(new ItemPedido(1, produto));
        pedidoDao.insert(pedido);

        em.getTransaction().commit();
    }

    public static void popular(){

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CursoJpa");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        CategoriaDao categoriaDao = new CategoriaDao(em);
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Categoria categoria = new Categoria("Animal");
        categoriaDao.insert(categoria);

        Produto produto = new Produto("Vaca", "Vaca verde", BigDecimal.valueOf(500), categoria);
        produtoDao.insert(produto);

        Cliente cliente = new Cliente("nome", "123456");
        clienteDao.insert(cliente);

        em.getTransaction().commit();
    }
}
