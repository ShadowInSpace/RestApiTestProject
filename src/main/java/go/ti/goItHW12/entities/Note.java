package go.ti.goItHW12.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Note {

    long id;
    String title;
    String content;
}
