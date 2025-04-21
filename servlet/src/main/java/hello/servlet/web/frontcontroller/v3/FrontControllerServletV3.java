package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        // 적절한 컨트롤러 생성

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 파라미터를 paramMap에 저장
        Map<String, String> paramMap = createParamMap(request);

        // URI에 해당하는 컨트롤러를 실행하고 결과를 받음
        ModelView mv = controller.process(paramMap);
        // 파라미터를 이용하여 해당 컨트롤러에 paramMap 전달 (save 예시)
        //      안에서
        //       member을 저장소(memberRepository)에 저장
        //       ModelView를 만들어서 ModelView 내부의 viewName이 "save-result"라고 저장


        String viewName = mv.getViewName();
        // "save-result" 저장

        MyView view = viewResolver(viewName);
        // ModelView -> MyView
        // viewResolver : 실 주소로 바꿔주는것 (save-result)를 이용해서 !

        
//        view.render(mv.getModel(),request,response);
        view.render(request,response);
        // 주소 던져줘 !
    }

    // HTTP 요청에서 전달된 모든 파라미터를 하나의 Map으로 추출해서 반환하는 메서드
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}

