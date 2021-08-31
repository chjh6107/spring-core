package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
//아직 DB가 정해지지 않았다는 전제하에 대충 하나 만든것
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store=new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
    
}
