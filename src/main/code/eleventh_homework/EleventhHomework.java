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
        String index1 = textLang.substring(textLang.indexOf("изучать"));
        String index2 = textLang.substring(textLang.indexOf("изучать"), textLang.indexOf("новые"));
        System.out.println(index1);
        System.out.println(index2);
        System.out.println("\n");
    }

    public static void fourthTask(){
        String textIndex ="Домашнее задание не проблема";;
        int index1 = textIndex.indexOf("не");
        int index2 = textIndex.lastIndexOf("не");
        System.out.println(index1);
        System.out.println(index2);
    }
}
