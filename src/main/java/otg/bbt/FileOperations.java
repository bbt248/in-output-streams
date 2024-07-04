package otg.bbt;

import java.io.*;
import java.util.Scanner;

public class FileOperations {

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть текст: ");
            String userText = scanner.nextLine();

            FileWriter fileWriter = new FileWriter("output.txt");
            fileWriter.write(userText);
            fileWriter.close();

            BufferedReader fileReader = new BufferedReader(new FileReader("output.txt"));
            String fileContent = fileReader.readLine();
            fileReader.close();

            System.out.println("Вміст файлу output.txt: " + fileContent);

            FileWriter sourceWriter = new FileWriter("source.txt");
            sourceWriter.write("Це текст у файлі source.txt.");
            sourceWriter.close();

            BufferedReader sourceReader = new BufferedReader(new FileReader("source.txt"));
            StringBuilder sourceContent = new StringBuilder();
            String line;
            while ((line = sourceReader.readLine()) != null) {
                sourceContent.append(line).append("\n");
            }
            sourceReader.close();

            FileWriter destinationWriter = new FileWriter("destination.txt");
            destinationWriter.write(sourceContent.toString());
            destinationWriter.close();

            Person person = new Person("John", 30);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("person.ser"));
            objectOutputStream.writeObject(person);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("person.ser"));
            Person deserializedPerson = (Person) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println("Десеріалізований об'єкт: " + deserializedPerson);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Serializable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}




