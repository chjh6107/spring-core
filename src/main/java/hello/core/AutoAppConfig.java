package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
 //자동으로 스프링 빈으로 올려주는 애노테이션, 
//Component 애노테이션이 붙은 클래스를 찾아 자동으로 빈에 등록
@ComponentScan(
    //등록할때 뺄것 지정
    //AppConfig에 Configuration이 있고, 수동으로 Bean에다가 올려주는 설정을 하는데
    //자동으로 등록되면 안되니 Configuration.class를 등록안하게 함
    //안그러면 충돌남
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {
    
}
