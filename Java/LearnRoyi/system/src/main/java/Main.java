import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    static  ArrayList<Student> studentArrayList;
    static {
        studentArrayList = new ArrayList<>();
        studentArrayList.add(new Student("小明",12,"男"));
        studentArrayList.add(new Student("小六",13,"女"));
        studentArrayList.add(new Student("小十多",14,"男"));
        studentArrayList.add(new Student("小ew",12,"女"));
        studentArrayList.add(new Student("小分隔符",16,"男"));
        studentArrayList.add(new Student("小23",17,"女"));
        studentArrayList.add(new Student("小语言",18,"男"));
        studentArrayList.add(new Student("小从v",19,"女"));
        studentArrayList.add(new Student("小豪哥",20,"男"));
    }

    public static void main(String[] args) {
   List<Student> res= studentArrayList.stream().filter(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getSex().equals("女");
            }
        }).peek(new Consumer<Student>() {
               @Override
               public void accept(Student student) {

               }
           })

        .collect(Collectors.toList());
        System.out.println(res);
    }


    private static class Student{
        private String name;
        private Integer age;
        private String sex;


        public Student(String name, Integer age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex='" + sex + '\'' +
                    '}'+"\n";
        }
    }
}
