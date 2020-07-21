package sort;

public class Student implements Comparable<Student>{

    private String username;
    private int age;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    //自定义比较规则,我们使用继承Comparable接口重写compareTo()方法的好处就是一次定义多次使用
    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }
}
