package android.dev.alex.connect_to_rest.model;

import android.icu.util.LocaleData;

import java.time.LocalDate;

public class University {

    private long id;
    private String name;
    private int accreditation_level;
    private LocalDate creation_date;
    private String address;
    private String phone;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAccreditation_level() {
        return accreditation_level;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
