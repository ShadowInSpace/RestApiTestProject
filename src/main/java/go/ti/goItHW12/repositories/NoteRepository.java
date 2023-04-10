package go.ti.goItHW12.repositories;

import go.ti.goItHW12.entities.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {
}
