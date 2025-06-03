package securenote.secureNoteService;
import java.util.ArrayList;
import java .util.List;

import securenote.DBnote.NoteDB;
import securenote.note.Note;

public class NoteService {
    private final List<Note> notes = new ArrayList<>();

    public List<Note> getAllNotes () {
        return NoteDB.getAllNotes();
    }

    public boolean updateNote (Note note) {
        return NoteDB.updateNote(note, note.getId());
    }

    public boolean deleteNote (Note note) {
        return NoteDB.deleteNote(note.getId());
    }
}
