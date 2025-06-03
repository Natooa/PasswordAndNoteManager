package securenote.controllerUI;
import securenote.DBnote.NoteDB;
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
            System.out.println("\n1. Add Notes \n2. Show All Notes \n3. Change Note\n4. Delete Note \n5. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine();

            switch(choice) {
                case "1" -> addNotes();
                case "2" -> showAllNotes();
                case "3" -> changeNotes();
                case "4" -> deleteNote();
                case "5" -> {
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
        Note newNote = new Note(title, content);
        int row = NoteDB.addNote(newNote);
    }

    private void showAllNotes(){
        List<Note> notes = noteService.getAllNotes();
        if(notes.isEmpty()) {
            System.out.println("notes not found");
            return;
        }
        for(int i = 0; i < notes.size(); i++) {
            System.out.println(i + ": " + notes.get(i));
        }
    }

    private void changeNotes() {
        List<Note> notes = noteService.getAllNotes();

        if(notes.isEmpty()) {
            System.out.println("notes not found");
        }

        showAllNotes();

        System.out.println("Enter note index you want to change: ");
        int index;

        try{
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }

        if(index < 0 || index > notes.size()) {
            System.out.println("Invalid index");
            return;
        }

        Note selectedNote = notes.get(index);
        System.out.println("What you would like to change:\n1. Title\n2. Notes\n3. Cancel");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> selectedNote.setTitle(scanner.nextLine());
            case "2" -> selectedNote.setNote(scanner.nextLine());
            case "3" -> {
                return;
            }
            default -> System.out.println("Invalid choice");
        }

        boolean success = noteService.updateNote(selectedNote);
        if(success) {
            System.out.println("Note updated successfully");
        } else {
            System.out.println("Error updating note");
        }
    }

    public void deleteNote() {
        List<Note> notes = noteService.getAllNotes();

        if(notes.isEmpty()) {
            System.out.println("notes not found");
            return;
        }

        showAllNotes();

        System.out.println("Enter note index you want to delete: ");

        int index;

        try{
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid index");
            return;
        }

        if(index < 0 || index >= notes.size()) {
            System.out.println("Invalid index");
        }

        Note selectedNote = notes.get(index);
        boolean success = noteService.deleteNote(selectedNote);
        if(success) {
            System.out.println("Note deleted successfully");
        } else {
            System.out.println("Error deleting note");
            return;
        }
    }
}
