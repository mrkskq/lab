package mk.ukim.finki.wp.lab;

import mk.ukim.finki.wp.lab.web.BookListServlet;
import mk.ukim.finki.wp.lab.web.BookReservationServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean<BookListServlet> bookListServletRegistration(
			BookListServlet servlet) {
		return new ServletRegistrationBean<>(servlet, "/");
	}

	@Bean
	public ServletRegistrationBean<BookReservationServlet> bookReservationServletRegistration(
			BookReservationServlet servlet) {
		return new ServletRegistrationBean<>(servlet, "/bookReservation");
	}

}
