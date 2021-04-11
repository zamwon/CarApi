package pl.karnecki.carrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class DbConfig {

    private final DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        jdbcTemplate.execute("CREATE TABLE cars ( " +
//                "  id bigint AUTO_INCREMENT PRIMARY KEY, " +
//                "  mark VARCHAR(250) , " +
//                "  model VARCHAR(250), " +
//                "  color VARCHAR(250) DEFAULT NULL, " +
//                "  productionYear INTEGER DEFAULT NULL " +
//                ")");
        return jdbcTemplate;
    }
}
