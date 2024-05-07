import java.util.*;

public class selection{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size :");
        int size = sc.nextInt();
        int arr[] = new int[size];

        System.out.println("Enter numbers in array :");
        for(int i=0; i<size; i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<size; i++){
            int min = i;
            for(int j=i+1; j<size; j++){
                if(arr[min]>arr[j]){
                    int temp = arr[min];
                    arr[min] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("Sorted array !");
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" | ");
        }

    }
}
