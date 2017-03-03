package de.larmic.joinfaces.test;

import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class JsfSpringBeanBuilder {

   private final Map<String, String> parameterMap = new HashMap<>();
   private final ApplicationContext context;

   public JsfSpringBeanBuilder(ApplicationContext context) {
      this.context = context;
   }

   public JsfSpringBeanBuilder withExternalParameter(String key, String value) {
      this.parameterMap.put(key, value);
      return this;
   }

   public <T> T build(Class<T> beanClass) {
      new FacesContextMock()
            .withExternalParameter(parameterMap)
            .replaceIn(context);

      return context.getBean(beanClass);
   }
}