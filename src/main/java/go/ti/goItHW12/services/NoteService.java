package go.ti.goItHW12.services;

import go.ti.goItHW12.entities.Note;
import go.ti.goItHW12.repositories.NoteRepository;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Random;

@Data
@Service
@RequiredArgsConstructor
public class NoteService {
    @NonNull
    private final HashMap<Long,Note> storage;
    private final NoteRepository repository;

    public HashMap<Long,Note> listAll() {
        return storage;
    }

    public Note add(Note note) {
        long id = getIndex();
        note.setId(id);
        storage.put(id,note);
        return note;
    }

    private Long getIndex() {
        Random randomizer = new Random();
        while (true) {
            Long i = randomizer.nextLong();
            if(!storage.containsKey(i)) return i;

        }
    }

    public void deleteById(long id) {
        try {
            storage.remove( id);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No such note exists");
        }
    }

    public void update(Note note) {
        Note origin = getById(note.getId());
        origin.setTitle(note.getTitle());
        origin.setContent(note.getContent());
        storage.put(note.getId(), origin);
    }

    public Note getById(long id) {
        try {
            return storage.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("No such note exists");
        }
    }
}
