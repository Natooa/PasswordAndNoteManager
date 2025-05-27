package securenote.secureNoteService;
import java.util.ArrayList;
import java .util.List;
import securenote.note.Note;

public class NoteService {
    private final List<Note> notes = new ArrayList<>();

    public void addNote(String title, String note) {
        notes.add(new Note(title, note));
    }

    public List<Note> getAllNotes () {
        return notes;
    }
}
