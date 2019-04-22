package com.notthemostcommon.creditcardpayoff;

import com.notthemostcommon.creditcardpayoff.PayoffStrategy.PayoffStrategy;
import com.notthemostcommon.creditcardpayoff.PayoffStrategy.StrategyRepository;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class DatabaseSeeder {

    // https://github.com/SeunMatt/spring-blog/blob/master/src/main/java/com/smatt/seeders/DatabaseSeeder.java

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder bCrypt;
    private StrategyRepository strategyRepository;


    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate,
            BCryptPasswordEncoder bCrypt,
            StrategyRepository strategyRepository){

        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.bCrypt = bCrypt;
        this.strategyRepository = strategyRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event){
        seedUsersTable();
        seedStrategyTable();
    }

    private void seedUsersTable(){
        String sql = "SELECT username FROM users where users.username = 'test@test.com' LIMIT 1";
        List<AppUser> user = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(user == null || user.size() <= 0) {
            AppUser appUser = new AppUser();
            appUser.setUsername("test@test.com");
            appUser.setFirstName("Test");
            appUser.setLastName("McTesterson");
            appUser.setPassword(bCrypt.encode("password"));
            userRepository.save(appUser);
            logger.info("Users Seeded");
        }
        logger.trace("Users Seeding Not Required");
        }

        private void seedStrategyTable(){
        String sql = "SELECT * FROM payoff_strategy";
        List<PayoffStrategy> strategies = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);

        if(strategies.size() <= 0){
            PayoffStrategy snowball = new PayoffStrategy();
            snowball.setStrategy("snowball");
            PayoffStrategy avalanche = new PayoffStrategy();
            avalanche.setStrategy("avalanche");
            PayoffStrategy minPayment = new PayoffStrategy();
            minPayment.setStrategy("minimum payment");

            strategyRepository.saveAll(Arrays.asList(snowball, avalanche, minPayment));
            logger.info("Strategy Seeded");
        }
        logger.trace("Strategy Seeding Not Required");
        }
    }

