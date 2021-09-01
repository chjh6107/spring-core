package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
 //자동으로 스프링 빈으로 올려주는 애노테이션, 
//Component 애노테이션이 붙은 클래스를 찾아 자동으로 빈에 등록
@ComponentScan(

    //basePackges를 사용하면 입력한 디렉터리 하위만 스캔의 대상이 됨
    basePackages="hello.core.member",
    //등록할때 뺄것 지정
    //AppConfig에 Configuration이 있고, 수동으로 Bean에다가 올려주는 설정을 하는데
    //자동으로 등록되면 안되니 Configuration.class를 등록안하게 함
    //안그러면 충돌남
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {
    //수동등록 빈이 우선권을 가지므로 @Component로 등록한 MemoryMemberRepository는 무시당함
    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
