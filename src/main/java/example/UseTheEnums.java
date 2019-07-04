package example;

import example.model.Color;
import example.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

/**
 * Tries to insert regular data using the enums that was populated in {@link InitializeEnumBackedTables}
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UseTheEnums {

    @PersistenceContext
    final EntityManager entityManager;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void afterPropertiesSet() {
        entityManager.merge(new Product().setNewStyleColor(Color.RED).setOldStyleColor(Color.ALMOND));
        entityManager.merge(new Product().setNewStyleColor(Color.BLUE).setOldStyleColor(Color.GREEN));
    }

}
