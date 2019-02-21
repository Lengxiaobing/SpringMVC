# SpringMVC学习
## 非注解方式
### 1.配置前端控制器
在/webapp/WEB-INF/web.xml中配置前端控制器
```xml
    <!-- SpringMVC 前端控制器-->
    <servlet>
        <servlet-name>SpringMVC</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!--
            配置SpringMVC加载的配置文件（处理器映射器、适配器等等）
            如果不配置contextConfigLocation，默认加载/WEB-INF/servlet（名称）-servlet.xml（springmvc-servlet.xml）
        -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-*.xml</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>SpringMVC</servlet-name>
        <!--
            有三种方式配置
            第一种：*.action， 访问以.action结尾的文件，由DispatcherServlet进行解析
            第二种：/， 所有访问的地址都由DispatcherServlet进行解析,对于静态文件需要配置不让DispatcherServlet进行解析，
                       使用此种方式可以实现RESTful风格的URL
            第三种：/*，这种方式不对，使用这种配置，最终要转发到一个jsp页面，仍然会由DispatcherServlet解析jsp地址，不能
                       根据jsp页面找到handler，会报错
        -->
        <url-pattern>*.action</url-pattern>
    </servlet-mapping>
```

### 2.配置处理器适配器
在前端控制器所指定的配置文件中配置，即spring/spring-*.xml文件
```xml
    <!-- 配置处理器适配器
        所有处理器适配器都实现HandlerAdapter接口
    -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
```
### 3.开发Handler
创建com.spring.mvc.controller.UserController类
```java
public class UserController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ArrayList<Object> list = new ArrayList<>();
        list.add("name");
        list.add("age");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("WEB-INF/jsp/user.jsp");

        return modelAndView;
    }
}
```
### 4.开发jsp页面
在WEB-INF/jsp中，开发jsp页面
### 5.配置Handler
在前端控制器所指定的配置文件中配置，即spring/spring-*.xml文件
```xml
    <!-- 配置Handler-->
    <bean name="" class="com.spring.mvc.controller.UserController"/>
```
### 6.配置处理器映射器
在前端控制器所指定的配置文件中配置，即spring/spring-*.xml文件
```xml
    <!-- 配置处理器映射器
        将bean的name作为url进行查找，需要在配置Handler时指定beanname（就是url）
    -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
```
### 8.配置视图解析器
在前端控制器所指定的配置文件中配置，即spring/spring-*.xml文件
```xml
    <!-- 配置视图解析器
        解析jsp解析，默认使用jstl标签，需要引入jstl依赖
     -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"/>
```
### 9.spring-*.xml完整配置
```xml
    <!-- 配置Handler-->
    <bean name="" class="com.spring.mvc.controller.UserController"/>

    <!-- 配置处理器映射器
        将bean的name作为url进行查找，需要在配置Handler时指定beanname（就是url）
    -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>

    <!-- 配置处理器适配器
        所有处理器适配器都实现HandlerAdapter接口
    -->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>

    <!-- 配置视图解析器
        解析jsp解析，默认使用jstl标签，需要引入jstl依赖
     -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"/>
```
### 10.常见的几种非注解的处理器适配器和映射器
#### 非注解的处理器适配器
- org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
    
    要求编写的Handler时间Controller接口
```java
    public class UserController implements Controller {
        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
            ArrayList<Object> list = new ArrayList<>();
            list.add("name");
            list.add("age");
    
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("list", list);
            modelAndView.setViewName("WEB-INF/jsp/user.jsp");
    
            return modelAndView;
        }
    }
```
- org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter

    要求编写的Handler实现HttpRequestHandler接口
```java
public class UserController2 implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Object> list = new ArrayList<>();
        list.add("name");
        list.add("age");
        request.setAttribute("list",list);
        request.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(request,response);
    }
}
```
#### 非注解的处理器映射器
- org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
- 多种处理器适配器可以共存
```xml
    <!-- 配置Handler-->
    <bean name="/user.action" class="com.spring.mvc.controller.UserController"/>

    <!-- 配置处理器映射器  -->
    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
```
- org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
```xml
        <!-- 配置Handler-->
        <bean id="UserController" name="/user.action" class="com.spring.mvc.controller.UserController"/>
    
        <!-- 配置处理器映射器  -->
        <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
            <property name="mappings">
                <props>
                    <prop key="/user.action">UserController</prop>
                </props>
            </property>
        </bean>
```
- 多种处理器映射器可以共存，前端控制器判断URL能让那些映射器处理，就让正确的映射器处理。