public class PrimeNumber {

    /*public static void main(String[] args) {
        //PalindromeNumbers();
        sortNumbers();
    }*/

    public static void primenumberorNot() {
        System.out.println("enter no ");
        Scanner scanner = new Scanner(System.in);
        int no = scanner.nextInt();
        int count = 0;
        for (int i = 1; i <= no; i++) {
            if (no % i == 0) {
                count = count + 1;
            }
        }
        if (count == 2) {
            System.out.println("prime number " + no);
        }
        else System.out.println("Not a prime number "+count);
    }


    public static void primeNumbers(){
        System.out.println("enter no ");
        Scanner scanner = new Scanner(System.in);
        int no =scanner.nextInt();
        for (int i = 2; i < no; i++) {
            int count =  0;
            for(int j=1;j<=i;j++){
                if(i%j==0){
                    count=count+1;
                }
            }
            if(count==2){
                System.out.println("prime number "+i);
            }
        }
    }

    public static void sortNumbers(){
        int n,temp;
        System.out.println("Enter the no of elements to sort");
        Scanner scanner = new Scanner(System.in);
        n=scanner.nextInt();
        int a[] = new int[n];
        System.out.println("Enter all the elements");
        for (int i=0;i<n;i++){
            a[i] = scanner.nextInt();
        }
        for (int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(a[i]>a[j]){
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        for (int k=0;k<n;k++){
            System.out.println(a[k]);
        }
    }

    public static void main(String[] args) {
        Predicate<String> i  = (s)-> s.length() > 5;
        Predicate<String> j  = (s)-> s.length() < 3;


    }
