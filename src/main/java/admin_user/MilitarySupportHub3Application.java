package admin_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"admin_user.LoginSingup", "admin_user.donate", "admin_user.volunteer", "admin_user.monthtyGift", "admin_user.contact", "admin_user.resources", "admin_user.memorial", "admin_user.fallen_soldiers", "admin_user.feedback"})
@EntityScan(basePackages = {"admin_user.LoginSingup","admin_user.donate", "admin_user.volunteer", "admin_user.monthtyGift", "admin_user.contact", "admin_user.resources", "admin_user.memorial", "admin_user.fallen_soldiers", "admin_user.feedback"})
@EnableJpaRepositories(basePackages = {"admin_user.LoginSingup","admin_user.donate", "admin_user.volunteer", "admin_user.monthtyGift", "admin_user.contact", "admin_user.resources", "admin_user.memorial", "admin_user.fallen_soldiers", "admin_user.feedback"})
public class MilitarySupportHub3Application {

    public static void main(String[] args) {
        SpringApplication.run(MilitarySupportHub3Application.class, args);
    }
}

