package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String userName;
    private int age;

    public Member() {
    }

    public Member(Long id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}
