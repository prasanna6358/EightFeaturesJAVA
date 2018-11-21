public class App
{
    public static void main(String[] args) {
       usingStreams();

       //method references
        MyNames methodReference = MethodReferenceOne::printPersonnames;
        methodReference.printNames();


        Function<Integer,Double> functionsqrt = MethodReferenceTwo::findSqrt;
        Double no = functionsqrt.apply(25);
        System.out.println("SQRT of no is "+no);

        //using instance methods
        Function<Integer, Integer> functionFactorial= new MethodReferenceTwo()::factorial;
        Integer factorial = functionFactorial.apply(6);
        System.out.println("Fcatorial of 6 is "+factorial);

        BiFunction<String, String, Integer> functionLength = MethodReferenceTwo::getStringsLength;
        Integer length = functionLength.apply("billa", "prasanna");
        System.out.println("Length of two Strings is: "+length);

        System.out.println("lambda expreesion directly");
        Consumer<List<String>> consumer = (name)->name.stream().map(param->{
          param = param.toUpperCase();
            System.out.println(param);
            return param;
        }).collect(Collectors.toList());
        consumer.accept(Arrays.asList("billa","prasanna","kumar","ravi"));
    }

    interface MyNames{
        void printNames();
    }
    public static void usingStreams() {

        System.out.println("creating a stream");
        Stream<Integer> integerStream = Stream.of(new Integer[]{1,2,3,4,5,6});

        System.out.println("finding squares using map method");
        List<Integer> squaresList= integerStream.map(item->item*item).collect(Collectors.toList());
        squaresList.forEach(number-> System.out.print(number+" "));
        System.out.println("getting the element of sqrt(25)");
        squaresList.stream().filter(item->{ return Math.sqrt(item)==5;}).collect(Collectors.toList()).forEach(item-> System.out.println("\n"+item));

        System.out.println();
        List<Character> characterList = new ArrayList<>();
        for(Character character='a';character<='z';character++){
            characterList.add(character);
        }
        System.out.println("printing uppercase characters using map method");
        characterList.stream().map(character->{return character.toString().toUpperCase();}).collect(Collectors.toList()).forEach(value-> System.out.print(value+" "));
        System.out.println("creating sequential/normal streams");
        Stream<Character> characterStream = characterList.stream();
        System.out.println("creating parallel streams");
        Stream<Character> characterparallelStream = characterList.parallelStream();

        System.out.println();
        long l1 =  System.currentTimeMillis();
        characterStream.forEach(item-> System.out.print(item));
        long l2 =  System.currentTimeMillis();
        System.out.print("\n"+"totaltime using sequential stream: "+(l2-l1)+"\n");

        System.out.println("==================================");
        long l3  =  System.currentTimeMillis();
        System.out.println("For large date processing Parallel streams is better");
        characterparallelStream.forEach(item-> System.out.print(item));
        long l4  =  System.currentTimeMillis();
        System.out.print("\n"+"totaltime using parallel stream: "+(l4-l3)+"\n");

        System.out.println("stream of List<String>");
        Stream<List<String>> listStringStream = Stream.of(Arrays.asList("billa"), Arrays.asList("prasanna"),Arrays.asList("bhuvan"));

        System.out.println("converting Stream<List<String>> to Stream<String>");
        Stream<String> streamString = listStringStream.flatMap(strList->strList.stream());

        System.out.println("using Optional class");
        Optional<String> stringOptional = streamString.filter(v->v.startsWith("b")).findFirst();
        if(stringOptional.isPresent()) {
            System.out.println(stringOptional.get());
        }

        System.out.println("using Map");
        Map<Integer, String> countries = new HashMap<>();
        countries.put(1,"INDIA");
        countries.put(2,"USA");
        countries.put(3,"JAPAN");
        countries.put(4,"KOREA");

        System.out.println("print vkey of the value");
        Optional<Integer> optionalInteger = countries.entrySet().stream().filter(param->param.getValue().equals("JAPAN")).map(param->param.getKey()).findFirst();
        if(optionalInteger.isPresent()) {
            System.out.println(optionalInteger.get());
        }
        
        ============================================
        public class MethodReferenceOne {

    public static void printPersonnames() {
        List<Employee> employees = Arrays.asList(
                new Employee(101, "prasanna", "prasanna@gmail.com", "hyderabad"),
                new Employee(102, "abhi", "abhi@gmail.com", "vikarabad"),
                new Employee(103, "suman", "suamn@gmail.com", "cyberabad"),
                new Employee(104, "suraj", "suraj@gmail.com", "adilabad"),
                new Employee(105, "ravi", "ravi@gmail.com", "secunderabad")
        );
        employees.stream().map(emp -> {
            return emp.getName();
        }).collect(Collectors.toList()).forEach(name -> System.out.println(" " + name));
    }
}

=====================================================
public class MethodReferenceTwo {

    public static Double findSqrt(Integer no){
        return Math.sqrt(no);
    }

    public Integer factorial(Integer no){
        int fact = 1;
        for(int i=1;i<no;i++){
            fact = fact*i;
        }
        return fact;
    }

    public static Integer getStringsLength(String s1, String s2){
        return (s1+s2).length();
    }
}

public class Search {

    public static Integer binarysearch(Integer[] arr, int findNo){
        System.out.println(Thread.currentThread().getName());
        int start=0; int end = arr.length-1;
        while(start<=end){
            int mid= start+(end-start)/2;
            if(arr[mid]==findNo){
                return mid;
            }
            if(arr[mid]<findNo){
                start = mid+1;
            }
            else end=mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        for(int i=50;i<=500;i=i*2) {
            System.out.println(binarysearch(new Integer[]{50, 100, 150, 200, 300, 400, 450, 500, 700, 800, 1000, 1200}, i));
        }
    }
}

====================================================

public class ThreadTest  {

    static class SmallTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return new Random().nextInt(10000000);
        }
    }

    public static void getServiceThreads(String[] args) {
        List<Future> features = new ArrayList();
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            Future<Integer> future = service.submit(new SmallTask());
            features.add(future);
//            try {
//                Integer intValue = future.get();
//                System.out.println("The value is  " + intValue);
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
        }
        features.forEach(param-> {
            try {
                        System.out.println(param.get());
                    }
                    catch (InterruptedException | ExecutionException e){
                        e.printStackTrace();
                    }
                });
        service.shutdown();
        while (!service.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    public static void getAllFeatures() {
        for (int i = 1; i <= 10; i++) {
            CompletableFuture.supplyAsync(()->{return  new Random().nextInt(10000000);})
                    .thenAccept(param-> System.out.println(param));
        }
        System.out.println("Finished all threads");
    }

    public static void main(String[] args) {
        getAllFeatures();
    }
}

====================================================

public class ThreadTestTwo implements Runnable{

    private double value;
    public ThreadTestTwo(Double value){
        this.value = value;
    }
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+" Random value  is "+value);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i=1;i<=10;i++) {
            service.execute(new ThreadTestTwo(Math.random()));
        }
        service.shutdown();
        while(!service.isTerminated()){
        }
        System.out.println("terminated");
    }

}



