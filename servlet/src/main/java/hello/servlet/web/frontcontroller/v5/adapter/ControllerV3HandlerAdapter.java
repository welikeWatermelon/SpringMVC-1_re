package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    // handler 가 ControllerV3 타입이면 true 반환
    // 즉, "이 어댑터가 이 컨트롤러 타입을 처리할 수 있는지" 판단하는 메서드
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    // 이 메서드는 실제로 Controller 를 실행해주는 역할
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // **supports()**에서 이 타입이 맞다고 확인했기 때문에 안전하게 형변환
        ControllerV3 controller = (ControllerV3) handler;

        // request에서 정보를 다 빼내서 저장

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }


    // HTTP 요청 파라미터를 하나씩 꺼내서 Map<String, String>에 담아주는 유틸 메서드
    // 예를 들어 로그인 폼에서 넘어온 id=ssafy&pass=1234와 같은 파라미터들을 담아줌
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().
                forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));

        return paramMap;
    }
}
