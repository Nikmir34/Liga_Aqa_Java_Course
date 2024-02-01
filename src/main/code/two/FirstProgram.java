package code.two;

public class FirstProgram {
    public static void main(String[] args){
        System.out.println("Текст для консоли");
        hello();
        welcome();
        welcome();
        text("Ross");
    }
    private static void hello(){
        System.out.println("Hello");
    }

    private static void welcome(){
        System.out.println("Welcome to my program");
    }

    private static void text(String name){
        System.out.println("Name: "+name);
    }
}
