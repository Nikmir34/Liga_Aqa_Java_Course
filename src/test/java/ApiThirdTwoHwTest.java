public class ApiThirdTwoHwTest {
    public static void main(String[] args) {
        ApiThirdOneHwTest firstNote = new ApiThirdOneHwTest.Builder()
                .titleValue("Заметка №1")
                .contentValue("Содержание заметки №1")
                .build();
        firstNote.displayInfo();

        ApiThirdOneHwTest secondNote = new ApiThirdOneHwTest.Builder()
                .titleValue(" ")
                .contentValue(" ")
                .build();
        secondNote.displayInfo();

        ApiThirdOneHwTest thirdNote = new ApiThirdOneHwTest.Builder()
                .titleValue("Check it")
                .contentValue("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                .build();
        thirdNote.displayInfo();
    }
}
