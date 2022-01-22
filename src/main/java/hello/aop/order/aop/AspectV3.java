package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {

    /**
     * hello.aop.order 하위 패키지
     */
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder() {} // 포인트컷 시그니처

    /**
     * 클래스 이름 패턴이 *Service
     */
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // joinPoint 시그니처
        return joinPoint.proceed();
    }

    /**
     * hello.aop.order 패키지 이면서 클래스 이름이 *Service
     * @param joinPoint
     * @return Object
     * @throws Throwable
     */
    @Around("allService() && allOrder()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트렌젝션 시작] {}", joinPoint.getSignature());
            final Object proceed = joinPoint.proceed();
            log.info("[트렌젝션 커밋] {}", joinPoint.getSignature());
            return proceed;
        } catch (Exception e) {
            log.info("[트렌젝션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
