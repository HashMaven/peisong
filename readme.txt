1、导入jar包 
2、配置web.xml文件
	服务器启动时就加载Spring容器
	<!-- 监听器 -->
		  <listener>
		    <listener-class>org.springframework.web.context.ContextLoaderListener
		    </listener-class>
		  </listener>
		  
		  <context-param>
		    <param-name>contextConfigLocation</param-name>
		    <param-value>classpath:applicationContext.xml</param-value>
		  </context-param>
	
3、配置springMVC核心控制器
4、创建springMVC配置文件
	（默认路径在web-inf下   名称：servletName-servlet.xml）
		扫描handler和service
			<context:component-scan base-package="com.xtkj.handler,com.xtkj.service"></context:component-scan>
		
			
5、整合mybatis
	导包(mybatis必备包+整合spring的jar包+数据库驱动包)
	创建mybatis主配置文件（mybatis-config.xml）
	创建数据源
			<bean id="datasourse" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
				<!-- 数据库URL -->
				<property name="url" value="jdbc:mysql://localhost:3306/MyBatis?useUnicode=true&amp;characterEncoding=utf-8" />
				<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
				<!-- 数据库用户 -->
				<property name="username" value="root" /> 
				<!-- 密码 -->
				<property name="password" value="12345678" />
			</bean>
	
	将mybatis交给spring管理(sqlSessionFactory)
			<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
					<property name="dataSource" ref="datasourse"></property>
				<!-- 加载mybatis主配置文件 -->
					<property name="configLocation" value="classpath:mybatis-config.xml"></property>
			</bean>
	
	自动扫描mapper(dao)
			<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
				<property name="basePackage" value="com.spring.dao"></property>
				<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>
			</bean>
	
6、导入校验包

	启动校验驱动
			<mvc:annotation-driven/>
	注入校验bean
			<bean class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"></bean>
	在数据模型上编写校验规则
	对数据模型做校验

7、创建pojo实例User－－－写入dao业务操作类UserDao.java & UserDao.xml－－－service写入UserServiceInterface接口
		－－－UserService实现接口，通过DAO访问数据库－－－handler写入UserHandler控制器处理业务逻辑

－－－－优化配置
删除spring主配置文件
移动springMVC主配置文件
	<context-param>
	    <param-name>contextConfigLocation</param-name>
	    <param-value>classpath:config/springMVC-servlet.xml</param-value>
  	</context-param>
  	
  	<!-- springMVC核心控制器 -->
	  <servlet>
	  	<servlet-name>springMVC</servlet-name>
	  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	  	<init-param>
	  		<param-name>contextConfigLocation</param-name>
	  		<param-value>classpath:config/springMVC-servlet.xml</param-value>
	  	</init-param>
	  </servlet>

创建数据源配置文件
配置文件集中管理
将springMVC主配置文件拆分

