package biblioteca;

import java.util.Random;

public class Sort {

	
	
	
	public static void mergeSort(Comparable data[],int n)
	{
	   MergeSort merge = new MergeSort(data,n);
	   merge.sort();
	}

	public static void quickSort(Comparable data[],int n)
	{
	   QuickSort quick = new QuickSort(data,n);
	   quick.sort();
	}
	
	public static void selectionSort(Comparable data[],int n)
	{
	   SelectionSort merge = new SelectionSort(data,n);
	   merge.sort();
	}
	
	public static void insertionSort(Comparable data[],int n)
	{
	   InsertionSort merge = new InsertionSort(data,n);
	   merge.sort();
	}
	
	public static void shellSort(Comparable data[],int n)
	{
	   ShellSort merge = new ShellSort(data,n);
	   merge.sort();
	}
	
	public static void heapSort(Comparable data[],int n)
	{
	   HeapSort merge = new HeapSort(data,n);
	   merge.sort();
	}
	
	
}
