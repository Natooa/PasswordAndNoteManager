package securenote.controllerUI;
import securenote.secureNoteService.NoteService;
import java.util.Scanner;
import java.util.ArrayList;
import java .util.List;
import securenote.note.Note;


public class ControllerUI {
    private final NoteService noteService = new NoteService();
    private final Scanner scanner = new Scanner(System.in);

    public void startNotesMenu() {
        while(true) {
            System.out.println("\n1. Add Notes \n2. Show All Notes \n3. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1" -> addNotes();
                case "2" -> showAllNotes();
                case "3" -> {
                    System.out.println("Bye");
                    return;
                }
            }
        }
    }

    private void addNotes() {
        System.out.println("\nEnter note title: ");
        String title = scanner.nextLine();

        System.out.println("Enter note content: ");
        String content = scanner.nextLine();

        noteService.addNote(title, content);
    }

    public void showAllNotes(){
        List<Note> notes = noteService.getAllNotes();
        if(notes.isEmpty()) {
            System.out.println("notes not found");
            return;
        }
        for(int i = 0; i < notes.size(); i++) {
            System.out.println(i + ": " + notes.get(i));
        }
    }
}
