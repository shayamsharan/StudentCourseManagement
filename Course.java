public class Course {
    private String code;
    private String title;

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }

    public String toString() {
        return code + "," + title;
    }

    public static Course fromString(String data) {
        String[] parts = data.split(",");
        return new Course(parts[0], parts[1]);
    }
}
