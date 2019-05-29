package application.utils;

public enum NotificationType {
    SUCCESS ("success.png"),
    ERROR ("error.png"),
    NOTICE("notice.png");

    private String type;

    NotificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
