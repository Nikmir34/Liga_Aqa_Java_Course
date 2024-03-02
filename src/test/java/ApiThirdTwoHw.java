public class ApiThirdTwoHw {
    public static void main(String[] args) {
        ApiThirdOneHw firstNote = new ApiThirdOneHw.Builder()
                .titleValue("Заметка №1")
                .contentValue("Содержание заметки №1")
                .build();
        firstNote.displayInfo();

        ApiThirdOneHw secondNote = new ApiThirdOneHw.Builder()
                .titleValue(" ")
                .contentValue(" ")
                .build();
        secondNote.displayInfo();

        ApiThirdOneHw thirdNote = new ApiThirdOneHw.Builder()
                .titleValue("Check it")
                .contentValue("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                .build();
        thirdNote.displayInfo();
    }
}
