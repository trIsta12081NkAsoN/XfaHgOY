// 代码生成时间: 2025-10-01 02:57:19
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Enable component scanning for @Component, @Service, @Repository, etc. annotations -->
    <context:component-scan base-package="com.yourpackage.uicomponents"/>

    <!-- UI Component Service -->
    <bean id="uiComponentService" class="com.yourpackage.uicomponents.service.UIComponentService">
        <!-- Any dependencies can be defined here -->
    </bean>

    <!-- Error Handling Configuration -->
    <bean class="org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController"/>
</beans>