package EJBs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int ID;

    @Column
    private int number1;

    @Column
    private int number2;

    @Column
    private String operation;
    
    @Column 
    private int result;

    
    public void setID(int id) {
        this.ID = id;
    }
    public int getID() {
        return ID;
    }

    
    public void setNumber1(int num1) {
        this.number1 = num1;
    }
    public int getNumber1() {
        return number1;
    }

    
    public void setNumber2(int num2) {
        this.number2 = num2;
    }
    public int getNumber2() {
        return number2;
    }

    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getOperation() {
        return operation;
    }
    
    public int Calculate() 
    {
    	int result = 0;
    	switch (operation) 
    	{
        case "+":
        	result = number1 + number2;
            break;
        case "-":
        	result = number1 - number2;
            break;
        case "*":
        	result = number1 * number2;
            break;
        case "/":
            if (number2 == 0) 
            {
                throw new IllegalArgumentException("Division by zero");
            }
            result = number1 / number2;
            break;
        default:
            throw new IllegalArgumentException("Unsupported operation: " + operation);
    	}
    		return result;
    		
    }
                
    
    public void saveOperation() {
        EntityManagerFactory EMF = Persistence.createEntityManagerFactory("persistence");
        EntityManager EM = EMF.createEntityManager();
        try {
            EM.getTransaction().begin();
            EM.persist(this);
            EM.getTransaction().commit();
        } 
        catch(Exception e) {
        	
        	EM.getTransaction().rollback();
        	
        }
        
        finally {
        	
            EM.close();
            EMF.close();
            
        }
    }

    
}