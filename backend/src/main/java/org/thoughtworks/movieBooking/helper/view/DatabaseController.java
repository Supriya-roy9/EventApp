package org.thoughtworks.movieBooking.helper.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class DatabaseController {

    private static final Logger log = LoggerFactory.getLogger(DatabaseController.class);
    @Autowired
        private DataSource dataSource;

        @GetMapping("/test-db")
        public String testConnection() {
            System.out.println("PPPP");
            log.info("HE:::O");
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("Hello");
                return "Database connected successfully!";
            } catch (SQLException e) {
                return "Failed to connect to the database: " + e.getMessage();
            }

    }

}
