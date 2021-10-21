package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

//test코드는 접근제어자가 필요가 없음
class MemberRepositoryTest {
    
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void AfterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);
        
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2); //contains는 member1과 member2 객체를 가지고 있는 지 확인 
    }
}
