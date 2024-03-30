package ApiFifteenthHw;

import lombok.Data;
import pojo.Notes;

import java.util.ArrayList;
import java.util.List;

@Data
public class NotesPojo {
    private String name;
    private String content;
    private String color;
    private Integer priority;
    private List<Notes> notes;

    public void setDefaultNotes() {
        Notes defaultNotes = new Notes();
        defaultNotes.setName("TestNotes");
        defaultNotes.setContent("TestContent");
        defaultNotes.setColor("#7CFC00");
        defaultNotes.setPriority(0);

        List<Notes> defaultListNotes = new ArrayList<>();
        defaultListNotes.add(defaultNotes);

        this.notes = defaultListNotes;
    }
}
