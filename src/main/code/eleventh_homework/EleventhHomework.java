package code.eleventh_homework;

public class EleventhHomework {
    public static void main(String[] Args){
        firstTask();
        secondTask();
        thirdTask();
        fourthTask();
    }
    public static void firstTask(){
        System.out.println("Я помню чудное мгновение");
        System.out.println("Передо мной явилась ты");
        System.out.println("Как мимолетное видение");
        System.out.println("Как гений чистой красоты!\n");
    }

    public static void secondTask(){
        String Java = "Java";
        String best = "лучший";
        String language = "язык";
        String program = "программирования";
        System.out.println(String.join(" ", Java + " -", best, language, program + "!\n"));
    }

    public static void thirdTask(){
        String textLang ="Обожаю изучать новые языки";
        int startFirstTask = 7;
        int endFirstTask = 26;
        char [] frs=new char[endFirstTask - startFirstTask];
        textLang.getChars(startFirstTask, endFirstTask, frs, 0 );
        int startSecondTask = 7;
        int endSecondTask = 14;
        char [] dst=new char[endSecondTask - startSecondTask];
        textLang.getChars(startSecondTask, endSecondTask, dst, 0 );
        System.out.println(frs);
        System.out.println(dst);
        System.out.print("\n");
    }

    public static void fourthTask(){
        String textIndex ="Домашнее задание не проблема";;
        int index1 = textIndex.indexOf("не");
        int index2 = textIndex.lastIndexOf("не");
        System.out.println(index1);
        System.out.println(index2);
    }
}
