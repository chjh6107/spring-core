package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
            NoSuchBeanDefinitionException.class,
            ()->ac.getBean("beanB",BeanB.class)
        );
    }
    @Configuration
    @ComponentScan(
        // includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
        //type=FilterType.ANNOTATION은 기본값이어서 생략 가능
        includeFilters = @Filter(classes = MyIncludeComponent.class),
        excludeFilters = @Filter(classes = MyExcludeComponent.class)    
    )
    static class ComponentFilterAppConfig{

    }
}
