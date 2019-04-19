package az.test.domain;

import az.test.domain.enums.Status;
import az.test.domain.enums.UserStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerDto extends Customer implements Serializable {

    private static final long serialVersionUID = 3054231317765277505L;

    private String cityName;
    private String channelName;
    private String genderName;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }
}
