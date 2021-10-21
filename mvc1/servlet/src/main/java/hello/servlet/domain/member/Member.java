package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private long id; //id값 저장소에 저장 시 id 발급하여 저장함
    private String username;
    private int age;

    public Member() {
        
    }
    public Member(String username, int age) {
        this.username=username;
        this.age=age;
    }
}
