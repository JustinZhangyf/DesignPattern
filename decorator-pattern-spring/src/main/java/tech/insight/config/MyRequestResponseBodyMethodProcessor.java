package tech.insight.config;

import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import tech.insight.TimestampRequestBody;

import java.util.Map;

public class MyRequestResponseBodyMethodProcessor implements HandlerMethodArgumentResolver {

    private RequestResponseBodyMethodProcessor processor;

    private ApplicationContext context;

    public MyRequestResponseBodyMethodProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TimestampRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (processor == null) {
            RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class);
            for (HandlerMethodArgumentResolver argumentResolver : adapter.getArgumentResolvers()) {
                if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                    processor = (RequestResponseBodyMethodProcessor) argumentResolver;
                    break;
                }
            }

        }

        Object o = processor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        if (o instanceof Map<?, ?>) {
            ((Map) o).put("timestamp", System.currentTimeMillis());
        }
        return o;
    }
}
