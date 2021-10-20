package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //lombok으로 getter setter 애노테이션으로 getter setter 설정
public class HelloData {
    private String username;
    private int age;
}
