package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemeberController {
//
//    @Autowired private MemberService memberService;  //필드주입. 중간에 변경할 수 없음. 스프링이 안 좋아함
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {  //setter 주입. 잘못 바꾸면 문제생김
//        this.memberService = memberService;
//    }

    private final MemberService memberService;
    //DI
    @Autowired  //스프링 컨테이너에서 멤버서비스 가져옴
    public MemeberController(MemberService memberService) {  //생성자 주입. 최고 권장
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createFrom() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
