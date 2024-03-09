public class ApiThirdOneHwTest {
    private String noteTitle;
    private String noteContent;

    public static class Builder {
        private ApiThirdOneHwTest newNote;

        public Builder() {
            newNote = new ApiThirdOneHwTest();
        }

        public Builder titleValue(String noteTitle) {
            newNote.noteTitle = noteTitle;
            return this;
        }

        public Builder contentValue(String noteContent) {
            newNote.noteContent = noteContent;
            return this;
        }

        public ApiThirdOneHwTest build() {
            return newNote;
        }

    }

    void displayInfo() {
        System.out.printf("Заголовок - %s\nОписание - %s\n\n", noteTitle, noteContent);
    }
}
