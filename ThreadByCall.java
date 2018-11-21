public class ThreadByCall implements Callable<Integer> {
    private Integer[] arr;
    private Integer findNo;

    public ThreadByCall(Integer[] arr, Integer findValue){
        this.arr = arr;
        this.findNo = findValue;
    }

    @Override
    public Integer call() throws Exception {
        return binarysearch();

    }

    public Integer binarysearch(){
        System.out.println(Thread.currentThread().getName());
        int start=0; int end = this.arr.length-1;
        while(start<=end){
            int mid= start+(end-start)/2;
            if(arr[mid]==this.findNo){
                return mid;
            }
            if(arr[mid]<this.findNo){
                start = mid+1;
            }
            else end=mid-1;
        }
        return -1;
    }

}

public class AsyncProgramme {

    public static void comppletableFuture() {

        System.out.println("==================Async programming==============================");
        //without return anyvalue
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->
        {
            System.out.println("Running lambda expression without return type");
        });
        CompletableFuture<String> completableFutureReturn = CompletableFuture.supplyAsync(()->{
            System.out.println("Running lambda expression with return type");
           return "Bill Prasanna Kumar";
        });
        System.out.println(completableFutureReturn.join());// will not throw any checked exceptions
        CompletableFuture<Void> completableFutureRunnable = CompletableFuture.runAsync(new Runnable(){
            @Override
             public void run(){
                System.out.println("will run in a separate thread than the main thread.");
            }
        });

        CompletableFuture<String> completableFutureCallable = CompletableFuture.supplyAsync(new Supplier<String>(){
            @Override
            public String get(){
                return "billa prasanna kumar";
            }
        });
        try {
            System.out.println(completableFutureCallable.get());//get() will throw exceptions
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        completableFuture();
    }
    public static void completableFuture(){
        ExecutorService cpuService =  Executors.newFixedThreadPool(5);
                CompletableFuture.supplyAsync(() -> "Hello world")// it will use forkJoin common pool if we are not providing any pool.
                        // we can provide our own pool like below
                         .thenApplyAsync(s -> s + " from  Future1!", cpuService)
                        .thenApply(s -> s + " from  Future2!")
                        .thenApply(s -> s + " from  Future3!")
                        .thenAccept(param-> System.out.println(param.toUpperCase()))
                        .thenRun(() -> System.out.println("Done processing this chain"));
    }
    
    public class ThreadByCall implements Callable<Integer> {
    private Integer[] arr;
    private Integer findNo;

    public ThreadByCall(Integer[] arr, Integer findValue){
        this.arr = arr;
        this.findNo = findValue;
    }

    @Override
    public Integer call() throws Exception {
        return binarysearch();

    }

    public Integer binarysearch(){
        System.out.println(Thread.currentThread().getName());
        int start=0; int end = this.arr.length-1;
        while(start<=end){
            int mid= start+(end-start)/2;
            if(arr[mid]==this.findNo){
                return mid;
            }
            if(arr[mid]<this.findNo){
                start = mid+1;
            }
            else end=mid-1;
        }
        return -1;
    }

}

public class ThreadByRunnable implements Runnable {

    private int value;
    public ThreadByRunnable(int value){
        this.value = value;
    }
    @Override
    public void run(){
        int fact=1;
        for(int i=1;i<=value;i++){
            fact*=i;
        }
        System.out.println(Thread.currentThread().getName()+" Factorial of "+value+" is "+fact);
    }
}


//===================================================
public class App 
{

    public static void main( String[] args ) {
        //get count available cores
        //int currentProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future> futureList =  new ArrayList<>();
        for (int i = 5; i <= 60; i = i * 2) {
            Future<Integer> future = service.submit(new ThreadByCall(new Integer[]{5,10,15,20,25,30,35,40,45,50,55,60}, i));
            futureList.add(future);
            System.out.println("The value of i is  " + i);
        }
            futureList.stream().forEach(future-> {
                try {
                    System.out.println("The indexes are "+future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        service.shutdown();
        while (!service.isTerminated()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Callable service terminated "+service.isTerminated());
        threadByRunnable();


        AsyncProgramme.comppletableFuture();
        }


        public static void threadByRunnable(){
            ExecutorService service = Executors.newFixedThreadPool(5);

            for(int i=1;i<=10;i++) {
                service.execute(new ThreadByRunnable(i));
            }
            service.shutdown();
        }

}


//for scheduling of threads
//ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
//task to run after 10 seconds
//service.schedule(new ThreadByRunnable(5),5,TimeUnit.SECONDS);
//task to run repeatedly for every 15 seconds
//service.scheduleAtFixedRate(new ThreadByRunnable(10),10,15,TimeUnit.SECONDS);
//task to run for every 15 seconds after previous task completes
//service.scheduleWithFixedDelay(new ThreadByRunnable(50),10,15,TimeUnit.SECONDS);
