package es.uca.iiss.aspectj;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoginAspect {
    @Before("execution(public void Bank.makeTransaction()) || execution(public void Bank.takeMoneyOut())")
    public void before(JoinPoint joinPoint){
        System.out.println("The login is required");
    }

    @After("execution(public void Bank.showUsers())")
    public void after(JoinPoint joinPoint){
        System.out.println("The database is empty");
    }
}
