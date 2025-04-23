package hello.servlet.web.springmvc.v1;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 스프링이 자동으로 스프링 빈으로 등록
// 내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨
@Controller
public class SpringMemberFormControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/new-form")
    // 요청 정보를 매핑함. 해당 URL이 호출되면 이 메서드가 호출됨
    // 메서드의 이름은 임의로
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);

        return mv;
    }
}

//@Component // 컴포넌트 스캔을 통해 스프링 빈으로 등록
//@RequestMapping
//public class SpringMemberFormControllerV1 {
//    @RequestMapping("/springmvc/v1/members/new-form")
//    // 요청 정보를 매핑함. 해당 URL이 호출되면 이 메서드가 호출됨
//    // 메서드의 이름은 임의로
//    public ModelAndView process(){
//        return new ModelAndView("new-form");
//    }
//}

//// 물론 컴포넌트 스캔 없이 아래와 같이 스프링 빈으로 직접 등록해도 동작함
//@RequestMapping
//public class SpringMemberFormControllerV1 {
//    @RequestMapping("/springmvc/v1/members/new-form")
//    // 요청 정보를 매핑함. 해당 URL이 호출되면 이 메서드가 호출됨
//    // 메서드의 이름은 임의로
//    public ModelAndView process(){
//        return new ModelAndView("new-form");
//    }
//}