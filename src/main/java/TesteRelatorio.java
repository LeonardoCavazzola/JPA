import dao.PedidoDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteRelatorio {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CursoJpa");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        PedidoDao pedidoDao = new PedidoDao(em);

        pedidoDao.getRelatorio().forEach(System.out::println);
    }
}
