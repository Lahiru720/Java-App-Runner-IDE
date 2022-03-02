package  controller;

interface First
{
    default void car(){
        System.out.println(" thi is lambo ");
    }


}

public class Test implements First{
    public static void main(String[] args) {

        Test t = new Test();
        t.car();
    }
}