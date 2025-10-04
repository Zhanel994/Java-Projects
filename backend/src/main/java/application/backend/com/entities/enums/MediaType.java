package application.backend.com.entities.enums;


import lombok.Getter;

@Getter
public enum MediaType {
    JPEG("image/jpeg"),
    JPG("image/jpg"),
    PNG("image/png");

    private final String contentType;

    MediaType(String contentType) {
        this.contentType = contentType;
    }

    public static MediaType getByExtension(String name) {
        for (MediaType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Media type not found: " + name);
    }
}
