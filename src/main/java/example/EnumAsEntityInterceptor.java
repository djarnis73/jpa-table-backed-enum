package example;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;

import java.io.Serializable;

@Slf4j
public class EnumAsEntityInterceptor extends EmptyInterceptor {

    public EnumAsEntityInterceptor() {
        log.info("{} instantiated", EnumAsEntityInterceptor.class.getSimpleName());
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        if (log.isTraceEnabled()) {
            log.trace("instantiate({}, {}, {})", entityName, entityMode, id);
        }
        try {
            Object o;
            Class cls = Class.forName(entityName);
            if (cls.isEnum() && id instanceof Number) {
                o = cls.getEnumConstants()[((Number)id).intValue()];
            } else {
                o = super.getEntity(entityName, id);
            }
            if (log.isTraceEnabled()) {
                log.trace("instantiate({}, {} ({})) -> {}", entityName, id, id.getClass(), o);
            }
            return o;
        } catch (ClassNotFoundException e) {
            // It would be very unexpected if the JPA layer gave us an entity name that does not map to a class
            throw new RuntimeException(e);
        }
    }

}