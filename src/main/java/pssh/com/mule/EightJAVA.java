package pssh.com.mule;

import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewLibConn {

    public static void main(String[] args) {

        Employee e1 = new Employee(1, 23, "M", "Rick", "Beethovan");
        Employee e2 = new Employee(2, 13, "F", "Martina", "Hengis");
        Employee e3 = new Employee(3, 43, "M", "Ricky", "Martin");
        Employee e4 = new Employee(4, 26, "M", "Jon", "Lowman");
        Employee e5 = new Employee(5, 19, "F", "Cristine", "Maria");
        Employee e6 = new Employee(6, 15, "M", "David", "Feezor");
        Employee e7 = new Employee(7, 68, "F", "Melissa", "Roy");
        Employee e8 = new Employee(8, 79, "M", "Alex", "Gussin");
        Employee e9 = new Employee(9, 15, "F", "Neetu", "Singh");
        Employee e10 = new Employee(10, 45, "M", "Naveen", "Jain");
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.addAll(Arrays.asList(new Employee[]{e1, e2, e3, e4, e5, e6, e7, e8, e9, e10}));

        System.out.println(filterEmployees(employeeList, isAdultMale()));
        System.out.println(filterEmployees(employeeList, isAduleFeMale()));

       getNum();

    }

    public static Predicate<Employee> isAdultMale() {
        return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M");
    }

    public static Predicate<Employee> isAduleFeMale() {
        return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("F");
    }

    public static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> predicate) {
        return employees.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void getNum() {
    List<Integer> myList = new ArrayList<>();
    for(int i = 0; i<100;i++)myList.add(i);
        //filter numbers greater than 90
        myList.stream().filter(p->p>90).forEach(System.out::println);

        System.out.println(Stream.of("abc","def","ghi","kjl").map(param->{
        return param.toUpperCase();
    }).collect(Collectors.toList()));


        List<String> listStr = new ArrayList<String>();
        listStr.add("prasanna");
        Stream<List<String>> namesOriginalList = Stream.of(listStr,
                Arrays.asList("Pankaj"),
                Arrays.asList("David", "Lisa"),
                Arrays.asList("Amit"));
//flat the stream from List<String> to String stream
        Stream<String> flatStream = namesOriginalList
                .flatMap(strList -> strList.stream());

        listStr = flatStream.sorted().collect(Collectors.toList());
        System.out.println(listStr);
        //flatStream.forEach(System.out::println);


        Stream<List<Integer>> listStreamInt = Stream.of(Arrays.asList(58),Arrays.asList(92),Arrays.asList(95));
        Stream<Integer> integerStream = listStreamInt.flatMap(param-> param.stream());
        integerStream.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

//get list of unique squares
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println("Square List"+squaresList);

 }
}




