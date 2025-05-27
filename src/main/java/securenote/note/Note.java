package securenote.note;

public class Note {
    private String title;
    private String note;

    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }

    @Override
    public String toString() {
        return "title: " + title + "\nNote: " + note;
    }
}
