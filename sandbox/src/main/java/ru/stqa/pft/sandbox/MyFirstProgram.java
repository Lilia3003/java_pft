package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello();
Square s = new Square(5);

    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
Rectangle r = new Rectangle(4,5);

    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

Point p1 = new Point(1,1);

Point p2 = new Point(1, 1);

    System.out.println("Расстояние между точками p1(" + p1.x + ";" + p1.y + ") и p2 (" + p2.x + ";" + p2.y +") равно " + p1.distance(p1, p2));

  }

  public static void hello() {
    String somebody = " world!";
    System.out.println("Hello, " + somebody);
  }

}