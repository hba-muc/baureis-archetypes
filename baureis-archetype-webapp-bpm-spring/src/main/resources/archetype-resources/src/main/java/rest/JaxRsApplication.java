package ${package}.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        // add your own classes

        // add camunda engine rest classes that you need
        classes.add(org.camunda.bpm.engine.rest.impl.ProcessEngineRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.ProcessDefinitionRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.ProcessInstanceRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.TaskRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.IdentityRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.MessageRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.JobRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.ExecutionRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.VariableInstanceRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.UserRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.GroupRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.AuthorizationRestServiceImpl.class);
        classes.add(org.camunda.bpm.engine.rest.impl.history.HistoryRestServiceImpl.class);
        // mandatory
        classes.add(org.camunda.bpm.engine.rest.mapper.JacksonConfigurator.class);
        classes.add(org.codehaus.jackson.jaxrs.JacksonJsonProvider.class);
        classes.add(org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper.class);
        classes.add(org.codehaus.jackson.jaxrs.JsonParseExceptionMapper.class);

        classes.add(org.camunda.bpm.engine.rest.exception.RestExceptionHandler.class);
        classes.add(org.camunda.bpm.engine.rest.exception.ProcessEngineExceptionHandler.class);
        return classes;
    }
}
