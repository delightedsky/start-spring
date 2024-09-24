package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장소에 저장
    Optional<Member> findById(Long id);  //null 처리를 옵셔널로 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();  //저장된 모든 회원리스트 반환
}
