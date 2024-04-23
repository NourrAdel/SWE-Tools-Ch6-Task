package EJBs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class Controller {

    @PersistenceContext(unitName = "hello")
    private EntityManager entityManager;

    @POST
    @Path("/calc")
    public int calculate(Calculation calc) {
        int res = 0;
        res = calc.Calculate();
        entityManager.persist(calc);
        return res;
    }

    @GET
    @Path("/calculations")
    public List<Calculation> getCalculations() {
    	return entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class).getResultList();
         
    }
}