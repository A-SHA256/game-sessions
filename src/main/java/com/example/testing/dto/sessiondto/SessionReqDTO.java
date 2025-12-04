package com.example.testing.dto.sessiondto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SessionReqDTO {

    @NotNull
    @Min(0)
    @Max(100_000)
    private Integer hoursPlayed = 0;

    public Integer getHoursPlayed() {
        return hoursPlayed;
    }

    public void setHoursPlayed(Integer hoursPlayed) {
        this.hoursPlayed = hoursPlayed;
    }
}
