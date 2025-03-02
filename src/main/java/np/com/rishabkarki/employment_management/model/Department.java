package np.com.rishabkarki.employment_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Department {
    GENERAL, SALES, ADMINISTRATION, IT, PRODUCT;

    @JsonCreator
    public static Department fromString(String value) {
        return Department.valueOf(value.toUpperCase());
    }
}