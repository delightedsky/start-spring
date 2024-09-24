package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach  // @AfterEach 어노테이션이 명시된 메서드는 테스트 메서드 1개 실행 후에 무조건 실행된다.
    public void afterEach() {
        repository.clearStore();  //memberrepsitory로 가서 clearStore 함수 실행
    }

    @Test
    public void save() {  //저장이 되는지 테스트
        Member member = new Member();
        member.setName("spring");
        
        repository.save(member);
        
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
        //System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() { // 이름으로 찾을 수 있는가?
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);


        //shift + f6 하면 똑같은 것들 자동 리네임
        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() { //전체 멤버의 수가 정확히 나오는가?
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
