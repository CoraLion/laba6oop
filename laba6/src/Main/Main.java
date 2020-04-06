package Main;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    private Student[] stud = new Student[0];
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main prog = new Main();
        prog.run();
    }

    private Student[] emptyArray() {
        return new Student[100];
    }

    private void run() {
        int sel;
        while((sel=menu())!= 0) {
            switch (sel) {
                case 1:
                    stud = readfromfile();
                    break;
                case 2:
                    writetofile(stud);
                    break;
                case 3:
                    writearray(stud);
                    break;
                case 4:
                    writetoBinary();
                    break;
                case 5:
                    stud = readtoBinary();
                    break;
                case 6:
                    System.out.println("Введите студента: ");
                    int id = scanner.nextInt();
                    String secname = scanner.next();
                    String name = scanner.next();
                    String threename = scanner.next();
                    int day = scanner.nextInt();
                    int month = scanner.nextInt();
                    int year = scanner.nextInt();
                    String address = scanner.next();
                    int mob = scanner.nextInt();
                    String fac = scanner.next();
                    int curs = scanner.nextInt();
                    int group = scanner.nextInt();
                    Student arr = new Student(id, secname, name, threename, LocalDate.of(year, month, day), address, mob, fac, curs, group);
                    stud = addelement(stud, arr);
                    break;
                case 7:
                    System.out.println("Введите айди удаляемого студента: ");
                    int id1 = scanner.nextInt();
                    stud = deleteelement(stud, id1);
                    break;
                case 8:
                    System.out.println("Введите факультет: ");
                    String fac1 = scanner.next();
                    Student[] findfac = fac(stud, fac1);
                    writearray(findfac);
                    break;
                case 9:
                    System.out.println("Введите год: ");
                    int year1 = scanner.nextInt();
                    Student[] findgod =god(stud, year1);
                    writearray(findgod);
                    break;
                case 10:
                    System.out.println("Введите группу: ");
                    int group1 = scanner.nextInt();
                    Student[] findgroup = group(stud, group1);
                    writearray(findgroup);
                    break;
            }
        }
    }

    private Student[] group(Student[] stud, int group) {
       Student[] arr = emptyArray();
       int count = 0;
        for (Student student : stud) {
            if(student.getGroup()==group) {
                arr[count] = student;
                count++;
            }
        }
        return Arrays.copyOf(arr, count);
    }

    private Student[] god(Student[] stud, int year) {
        Student[] arr = emptyArray();
        int count = 0;
        for (Student student : stud) {
            if(student.getAge().isAfter(LocalDate.of(year, 12, 31))) {
                arr[count] = student;
                count++;
            }
        }
        return Arrays.copyOf(arr, count);
    }

    private Student[] fac(Student[] stud, String fac) {
        Student[] arr = emptyArray();
        int count = 0;
        for (Student student : stud) {
           if(student.getFac().equals(fac)){
               arr[count] = student;
               count++;
           }
        }
        return Arrays.copyOf(arr, count);
    }

    private Student[] deleteelement(Student[] stud, int id) {
        int i;
        Student[] arr = emptyArray();
        int count = 0;
        for (Student student : stud) {
           arr[count] = student;
           count++;
        }
        for (i = 0; i < count; i++) {
            if(arr[i].getId()==id) {
                arr[i]=null;
            }
        }
        for (int k = i; k < count - 1; k++) {
            arr[k] = arr[k + 1];
            count--;
        }
        return Arrays.copyOf(arr, count);
    }

   private Student[] addelement(Student[] stud, Student arr) {
       Student[] newStud = new Student[stud.length+1];
       for (int i = 0; i < stud.length; i++) {
           newStud[i] = stud[i];
       }
       newStud[newStud.length-1] = arr;
       return newStud;
   }

    private Student[] readtoBinary() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("student.dat")))) {
            return (Student[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Student[0];
        }
    }

    private void writetoBinary() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(stud);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writetofile(Student[] stud) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d M yyyy");
        try (PrintWriter printw = new PrintWriter("student1.txt")) {
            for (Student student : stud) {
                printw.print(student.getId() + " " + student.getSecname() + " " + student.getName() + " "
                        + student.getThreename() + " ");
                printw.print(formatter.format(student.getAge()) + " ");
                printw.print(student.getAddress() + " " + student.getMob() + " " + student.getFac() + " "
                        + student.getCurs() + " " + student.getGroup() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Student[] readfromfile() {
        int count = 0;
        Student[] array = emptyArray();
        try (BufferedReader reader = new BufferedReader(new FileReader("student.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] s = line.split(" ");
                int id = Integer.parseInt(s[0]);
                String secname = s[1];
                String name = s[2];
                String threename = s[3];
                int day = Integer.parseInt(s[4]);
                int month = Integer.parseInt(s[5]);
                int year = Integer.parseInt(s[6]);
                String address = s[7];
                int mob = Integer.parseInt(s[8]);
                String fac = s[9];
                int curs = Integer.parseInt(s[10]);
                int group = Integer.parseInt(s[11]);
                Student st = new Student(id, secname, name, threename, LocalDate.of(year, month, day), address, mob, fac, curs, group);
                array[count] = st;
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return Arrays.copyOf(array, count);
    }

    private void writearray(Student[] stud) {
        for (Student student : stud) {
            System.out.println(student);
        }
    }

    private int menu() {
        System.out.println("---------------Меню-----------------");
        System.out.println("Нажмите 1, чтобы прочитать файл");
        System.out.println("Нажмите 2, чтобы записать в файл");
        System.out.println("Нажмите 3, чтобы вывести массива на экран");
        System.out.println("Нажмите 4, чтобы записать в бинарный файл");
        System.out.println("Нажмите 5, чтобы прочитать бинарный файл");
        System.out.println("Нажмите 6, чтобы добавить элемент в массив");
        System.out.println("Нажмите 7, чтобы удалить элемент из массива");
        System.out.println("Нажмите 8, чтобы вывести список студентов заданного факультета");
        System.out.println("Нажмите 9, чтобы вывести список студентов, родившихся после заданного года");
        System.out.println("Нажмите 10, чтобы вывести список студентов заданной группы");
        System.out.println("Нажмите 0, чтобы выйти");
        return new Scanner(System.in).nextInt();
    }
}
