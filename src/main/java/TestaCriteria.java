import dao.ProdutoDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TestaCriteria {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CursoJpa");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        ProdutoDao produtoDao = new ProdutoDao(em);

        produtoDao.findWithCriteria("va", BigDecimal.valueOf(40), null)
                .forEach(System.out::println);

        em.close();
    }
}
