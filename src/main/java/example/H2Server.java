package example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import java.sql.SQLException;

/**
 * Starts up an H2 server so we can connect to it from intellij and inspect the database.
 * Inspiration found here: https://www.baeldung.com/spring-boot-access-h2-database-multiple-apps
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "h2server")
@Data
public class H2Server {

    private Integer port = 9092;

    private boolean disabled;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        if (disabled) {
            log.info("H2 network listener disabled");
            return null;
        } else {
            if (port == null) {
                port = SocketUtils.findAvailableTcpPort();
            }
            log.info("Instantiating H2 network listener on port {}", port);
            return Server.createTcpServer("-tcp", "-tcpPort", String.valueOf(port));
        }
    }

}


