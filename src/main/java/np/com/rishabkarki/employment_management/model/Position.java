package np.com.rishabkarki.employment_management.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Position {
    INTERN, ADMINS, EXECUTIVES, ASSISTANT_MANAGER,
    MANAGER, SENIOR_MANAGER, ASSISTANT_PRESIDENT,
    PRESIDENT, VICE_PRESIDENT, CMO, CHRO, CFO, CEO;

    @JsonCreator
    public static Position fromString(String value) {
        return Position.valueOf(value.toUpperCase());
    }
}