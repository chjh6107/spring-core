package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig appConfig=new AppConfig();
        // MemberService memberService = appConfig.memberService();

        //스프링은 이친구로부터 시작되고 스프링 컨테이너라고 보면 됨
        //AppConfig에 있는 정보를 보고 스프링이 Bean 애노테이션이 붙은 메소드들은 모두 스프링 컨테이너에 넣어 관리해줌
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        
        //이름은 default가 메소드 이름이고 거기에 맞는 반환타입을 .class하여 작성
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        ((ConfigurableApplicationContext)applicationContext).close();
    }
}
