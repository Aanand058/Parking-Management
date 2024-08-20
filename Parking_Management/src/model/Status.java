package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Status {
    private int passId;
    private String startDateTime;
    private String validTill;
    private long hoursDifference;
    private String isValid;

    public Status(int passId, String startDateTime, String validTill) {
        this.passId = passId;
        this.startDateTime = startDateTime;
        this.validTill = validTill;
        this.hoursDifference = calculateHoursDifference();
        this.isValid = isValidOrNot();
    }

    private String isValidOrNot() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime validTillDateTime = LocalDateTime.parse(validTill, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Check if the current date and time is after the validTill date and time
        if (currentDateTime.isAfter(validTillDateTime)) {
            return "Invalid";
        } else {
            return "Valid";
        }
    }

    public long getHoursDifference() {
        return hoursDifference;
    }

    private long calculateHoursDifference() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime end = LocalDateTime.parse(validTill, formatter);
        return Duration.between(start, end).toHours();
    }

    public String getIsValid() {
        return isValid;
    }
}
