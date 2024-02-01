package code.eighth_homework;

public class EighthHomework {
    public static void main(String[] args){
        firstTask();
        secondTask();



    }
    private static void firstTask(){
        int chislo = 7778;
        double dot = 876.351;
        String name = "Игорь";
        char ny = 'n' + 'y';
        boolean boo = true;

        System.out.println("Значение целочисленной переменной - " + chislo);
        System.out.println("Значение строковой переменной - " + name);
        System.out.println("Значение переменной c плавающей точкой - " + dot);
        System.out.println("Значение символьной переменной - " + ny);
        System.out.println("Значение логической переменной - " + boo);

    }

    private static void secondTask(){
        int chislo = 7778;
        double dot = 876.351;

        String a = String.valueOf(chislo);
        int s = (int)dot;
        System.out.println(a);
        System.out.println(s);
    }
}
