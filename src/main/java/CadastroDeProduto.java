
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroDeProduto {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("CursoJpa");
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();
        //Operacoes
        em.close();
    }
}
