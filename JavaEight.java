public class JavaEight 
{
    public static void main( String[] args ) {

        Configuration configuration = new Configuration();
       // configuration.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        //ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
       // SessionFactory sessionFactory = configuration.buildSessionFactory(srvcReg);
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();
        Employee emp = new Employee();
       // emp.setId(101);
        emp.setName("prasanna");
        emp.setSalary(20150);
        session.save(emp);
        transaction.commit();
        Employee employee = session.get(Employee.class,3);
        System.out.println(employee.getId() +"\t"+employee.getName()+"\t"+employee.getSalary());

        BiFunction<String, String,Integer> biFunction = App::getLength;
        System.out.println(biFunction.apply("Prasanna", "kumar"));

        Function<Integer,Integer> function = App::getSquare;
        System.out.println(function.apply(5));

        BiConsumer<String, String> consumer = App::getConsume;
        consumer.accept("Prasanna", "kumar");

        Consumer<String> consumer2 = App::Consume;
        consumer2.accept("Prasanna");

        BiPredicate<String,String> biPredicate = App::getPredicate;

        System.out.println();
        System.out.println("it will prnt true or false");
        System.out.println();
        System.out.println(biPredicate.test("prasanna","prasanna"));
    }


    public static int getLength(String m1, String m2){
        System.out.println(m1+" "+m2);
        return (m1+m2).length();
    }
    public static int getSquare(int m2){

        return m2*m2;
    }

    public static void Consume(String m2){
        System.out.println("calling single consumer");
        System.out.println(m2);
    }

    public static void getConsume(String m1, String m2){
        System.out.println("calling consumer");
        System.out.println(m1+" "+m2);
    }

    public static boolean getPredicate(String m1, String m2){
        return m1.equalsIgnoreCase(m2);
    }



}
