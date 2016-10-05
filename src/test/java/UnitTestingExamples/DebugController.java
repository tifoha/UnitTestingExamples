package UnitTestingExamples;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.condition.RequestCondition;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

/**
 * Created by user on 05.10.16.
 */
//@Controller
//@RequestMapping("debug")
public class DebugController {
//    @Autowired
//    private WebApplicationContext context;
//
//    @RequestMapping("report")
//    @ResponseBody
//    public String printMap() {
//        RequestMappingHandlerMapping requestMappingHandlerMapping = context.getBean(RequestMappingHandlerMapping.class);
//        StringBuilder sb = new StringBuilder("<html>\n" +
//                "<head><title>Endpoint list</title></head>\n" +
//                "<body>\n" +
//                "<table>\n" +
//                "  <thead>\n" +
//                "  <tr>\n" +
//                "    <th>Controller class name</th>\n" +
//                "    <th>Method name</th>\n" +
//                "    <th>path</th>\n" +
//                "    <th>methods</th>\n" +
//                "    <th>consumes</th>\n" +
//                "    <th>produces</th>\n" +
//                "    <th>params</th>\n" +
//                "    <th>headers</th>\n" +
//                "    <th>custom</th>\n" +
//                "  </tr>\n" +
//                "  </thead>\n" +
//                "  <tbody>\n");
//        Map<RequestMappingInfo, HandlerMethod> m = requestMappingHandlerMapping.getHandlerMethods();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : m.entrySet()) {
//            RequestMappingInfo endPoint = entry.getKey();
//            HandlerMethod info = entry.getValue();
//            sb.append("    <tr>\n");
//            addCell(sb, info.getMethod().getDeclaringClass().getSimpleName());
//            addCell(sb, info.getMethod().getName());
//
//            addCell(sb, endPoint.getPatternsCondition());
//            addCell(sb, endPoint.getMethodsCondition());
//            addCell(sb, endPoint.getConsumesCondition());
//            addCell(sb, endPoint.getProducesCondition());
//            addCell(sb, endPoint.getParamsCondition());
//            addCell(sb, endPoint.getHeadersCondition());
//            RequestCondition<?> customCondition = endPoint.getCustomCondition();
//            addCell(sb, customCondition != null ? customCondition : "none");
//            sb.append("    </tr>\n");
//        }
//
//        sb.append("  </tbody>\n" +
//                "</table>\n" +
//                "</body>\n" +
//                "</html>");
//        String result = sb.toString();
//
//        return result;
//
//    }
//
//    private void addCell(StringBuilder sb, Object content) {
//        sb.append("      <td>");
//        sb.append(content);
//        sb.append("</td>");
//    }
}
