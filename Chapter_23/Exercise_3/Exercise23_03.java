/*
Author: 
Date: 

Description: 
*/
import java.util.Comparator;

public class Exercise23_03 {
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {
      new Circle(2), new Circle(3), new Circle(2),
      new Circle(5), new Circle(6), new Circle(1), new Circle(2),
      new Circle(3), new Circle(14), new Circle(12)
    };

    quickSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.println(list1[i] + " ");
    }
  }
  //To use Comparable interface
  public static <E extends Comparable<E>> void quickSort(E[] list) {
    quickSort(list, 0, list.length-1);
  }

  //To use Recursive method
  private static <E extends Comparable<E>> void quickSort(E[] list, int first, int last) {
    if (first< last) {
      int pivotIndex = partition(list, first, last);

    quickSort(list, first, pivotIndex - 1);
    quickSort(list, pivotIndex + 1, last);
    }
  }
  //Divides the array for the Comparable version.
  private static <E extends Comparable<E>> int partition(E[] list, int first, int last) {
    E pivot = list[last];
    int i = first-1;

    for (int j = first; j<last; j++) {
      if (list[j].compareTo(pivot) <= 0) {
        i++;
        swap(list, i, j);
      }
    }
    swap(list, i+1, last);
    return i+1;
  }

  //Sort the comparator
  public static <E> void quickSort(E[] list, Comparator<? super E> comparator) {
    quickSort(list, 0, list.length -1, comparator);
  }
  //Sorting array from first to last.
  private static <E> void quickSort(E[] list, int first, int last, Comparator<? super E> comparator) {
    if (first < last) {
      int pivotIndex = partition(list, first, last, comparator);

      quickSort(list, first, pivotIndex - 1, comparator);
      quickSort(list, pivotIndex + 1, last, comparator);
    }
  }

  //Dividing the parts of the array for the Comparator version
  private static <E> int partition(E[] list, int first, int last, Comparator<? super E> comparator) {
    E pivot = list[last];
    int i = first -1;
    for (int j = first; j < last; j++) {
      if(comparator.compare(list[j], pivot) <= 0) {
        i++;
        swap(list, i, j);
      }
    }
    swap(list, i+1, last);
    return i+1;
  }
  //Finally, swap two elements in the array.
  private static <E> void swap(E[] list, int index1, int index2) {
    E temp = list[index1];
    list[index1] = list[index2];
    list[index2] = temp;
  }
}