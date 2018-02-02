public class DrawArrow {

    public static void main(String[] args) {
        draw(91);
    }

    public static void draw(int numberedArrow){
        int downarrow = numberedArrow/2;
        int uparrow = numberedArrow/2;

        for(int i=1;i<=numberedArrow;i++){
            if(i<=numberedArrow/2) {
                for (int k = 1; k < uparrow; k++) {
                    System.out.print(" ");
                }
                uparrow = uparrow - 1;
                for (int j = 1; j <= i; j++) {
                    System.out.print("*");
                    System.out.print(" ");
                }
            }
            if(i>downarrow){
                for(int m=1;m<=downarrow/2;m++){
                    System.out.print(" ");
                }
                for(int m=1;m<=downarrow/2;m++){
                    System.out.print("*");
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
