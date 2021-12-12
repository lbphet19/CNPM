package team.cnpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import team.cnpm.properties.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class CnpmQlNhanKhauApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnpmQlNhanKhauApplication.class, args);
	}

}
