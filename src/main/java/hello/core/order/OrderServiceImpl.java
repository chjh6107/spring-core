package hello.core.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

@Component
//@RequiredArgsConstructor //final키워드가 붙은 필드를 모아 생성자를 자ㄹ동 생성
public class OrderServiceImpl implements OrderService{
    //DIP 위반으로 인터페이스에만 의존하도록 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    private DiscountPolicy rateDiscountPolicy;

    //생성자 의존관계
    //생성자가 한개면 Autowired 생략 가능
    //lombok RequiredArgsConstructor로 인해 생성자가 필요없어지게 됨됨
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
