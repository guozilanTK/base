package tk.guozilan.vesta.service.impl.bean;

public enum IdType {
    SECONDS("seconds"), MILLISECONDS("milliseconds");

    private String name;

    private IdType(String name) {
        this.name = name;
    }

    public static IdType parse(String name) {
        if ("seconds".equals(name)) {
            return SECONDS;
        } else if ("milliseconds".equals(name)) {
            return MILLISECONDS;
        }

        throw new IllegalArgumentException("Illegal IdType name <[" + name + "]>, available names are seconds and milliseconds");
    }

    public static IdType parse(long type) {
        if (type == 1) {
            return MILLISECONDS;
        } else if (type == 0) {
            return SECONDS;
        }

        throw new IllegalArgumentException("Illegal IdType value <[" + type + "]>, available values are 0 (for seconds) and 1 (for milliseconds)");
    }

    public long value() {
        switch (this) {
            case SECONDS:
                return 0;
            case MILLISECONDS:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
