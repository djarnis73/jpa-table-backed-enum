package example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

/**
 * Populates the backed tables on application startup.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class InitializeEnumBackedTables {

    @PersistenceContext
    final EntityManager entityManager;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional
    public void initializeEnumTables() {
        log.info("Initializing tables backing enum types");
        for (EntityType et : entityManager.getMetamodel().getEntities()) {
            if (et.getJavaType().isEnum()) {
                for (Object e : et.getJavaType().getEnumConstants()) {
                    log.trace("Saving enum constant {}", e);
                    entityManager.merge(e);
                }
            }
        }
    }

}
