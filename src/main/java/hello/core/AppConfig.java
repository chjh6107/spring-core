package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//프로그램 전반에 대한 운영 책임지는 구성
public class AppConfig {
    //중복제거
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    private DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

    public MemberService memberService(){
        //생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

}
