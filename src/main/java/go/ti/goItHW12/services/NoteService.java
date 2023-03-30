package go.ti.goItHW12.services;

import go.ti.goItHW12.entities.Note;
import lombok.Data;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Service
@RequiredArgsConstructor
public class NoteService {
    @NonNull
    private final ArrayList<Note> storage;

    public NoteService(){
        this.storage = new ArrayList<>();
    }


    public List<Note> listAll() {
        return storage;
    }

    public Note add(Note note) {
        int id = getIndex();
        note.setId(id);
        storage.add(id, note);
        return note;
    }

    private int getIndex() {
        Random randomizer = new Random();
        while (true) {
            int i = randomizer.nextInt();
            try {
                Note note = storage.get(i);
            } catch (IndexOutOfBoundsException e) {
                return i;
            }

        }
    }

    void deleteById(long id) {
        try {
            storage.remove((int) id);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No such note exists");
        }
    }

    void update(Note note) {
        Note origin = getById(note.getId());
        origin.setTitle(note.getTitle());
        origin.setContext(note.getContext());
        storage.set(note.getId(), origin);
    }

    Note getById(long id) {
        try {
            return storage.get((int) id);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No such note exists");
        }
    }
}
