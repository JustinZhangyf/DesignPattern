1. 现有需求：@RequestBody注解接收了一个map, 需要在request映射到map的时候自动添加一组key-value为timestamp-当前时间戳
2. 分析：debug spring的请求处理流程，看spring是如何映射request到参数的
   1.  debug 发现在InvocableHandlerMethod类中invokeForRequest方法中Object returnValue = doInvoke(args);此时args已经是HashMap了，所以逐步向上debug寻找args来源
   2.  同类中寻找参数来源，最终在AbstractHandlerMethodAdapter类中invokeHandlerMethod方法中Object[] args = getMethodArgumentValues(webRequest, handlerMethod);
   3.  该方法中有两个方法，一个是resolvers.supportsParameter(parameter)，
   4.  一个是resolvers.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
   5.  最终找到HandlerMethodArgumentResolverComposite 组合类，该类中有一个集合，该集合中存放了所有的HandlerMethodArgumentResolver实现类
   6.  spring容器启动是创建HandlerMethodArgumentResolverComposite对象，将所有的HandlerMethodArgumentResolver实现类添加到集合中
   7.  如果我们需要自定义或者增强参数映射，则需要实现HandlerMethodArgumentResolver接口，并重写supportsParameter和resolveArgument方法
   8.  然后将自定义的HandlerMethodArgumentResolver实现类在spring容器中注册
   9.  最后通过实现WebMvcConfigurer接口的addArgumentResolvers方法，将自定义的HandlerMethodArgumentResolver实现类添加到spring的处理器链中
3. 解决方案：使用spring的HandlerMethodArgumentResolver接口，重写supportsParameter和resolveArgument方法
3. 通过@Component注解将自定义的HandlerMethodArgumentResolver实现类交给spring管理
   4. 通过实现WebMvcConfigurer接口的addArgumentResolvers方法，将自定义的HandlerMethodArgumentResolver实现类添加到spring的处理器链中