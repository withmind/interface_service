package com.bootdo.common.db;

import com.bootdo.interfaceext.vo.InterfaceVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author DGD
 * @date 2018/2/7.
 */

@Component
@Aspect
//@Order(-100)
public class DataSourceSwitchAspect {

    public final Logger log = LoggerFactory.getLogger(DataSourceSwitchAspect.class);

    @Pointcut("execution(* com.bootdo..*.service..*.*(..))")
    private void dbAspect() {
    }

    @Pointcut("execution(* com.bootdo.system.controller.LoginController.*(..))")
    private void db2Aspect() {
    }

    /*@Pointcut("execution(* com.bootdo.system.service.MenuService.*(..))")
    private void db3Aspect() {
    }*/


 /*   @Pointcut("execution(* com.bootdo.interfaceext.service..*.*(..))")
    private void db2Aspect() {
    }
*/
    @Before( "dbAspect()" )
    public void db1(JoinPoint joinPoint) {
        log.info("切换到master1数据源...");
        setDataSource(joinPoint,DBTypeEnum.master);
    }

    @Before("db2Aspect()" )
    public void db2 (JoinPoint joinPoint) {
        log.info("切换到master2数据源...");
        setDataSource(joinPoint,DBTypeEnum.master);
    }

   /* @Before("db3Aspect()" )
    public void db3 (JoinPoint joinPoint) {
        log.info("切换到master3数据源...");
        setDataSource(joinPoint,DBTypeEnum.master);
    }*/

    @After("dbAspect()" )
    public void after(){
        DbContextHolder.clearDbType();
    }

    @After("db2Aspect()" )
    public void afterdb2(){
        DbContextHolder.clearDbType();
    }

    /**
     * 添加注解方式,如果有注解优先注解,没有则按传过来的数据源配置
     * @param joinPoint
     * @param dbTypeEnum
     */
    private void setDataSource(JoinPoint joinPoint, DBTypeEnum dbTypeEnum) {

        log.info("切换到master 数据源...");
        DbContextHolder.setDbTypestr("master");
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            if (argItem instanceof InterfaceVO) {
                InterfaceVO paramVO = (InterfaceVO) argItem;
                String dbCode = paramVO.getDbCode();
                if(dbCode != null && !"".equals(dbCode)){
                    log.info("切换到"+dbCode+" 数据源...");
                    DbContextHolder.setDbTypestr(dbCode);
                }else{
                    log.info("切换到master 数据源...");
                    DbContextHolder.setDbTypestr("master");
                }
            }
        }
    }
}
