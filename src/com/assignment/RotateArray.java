package com.assignment;

/* Question 1:
Write a program that takes as input an array of numbers of length N and a number p (positions - p is greater than 0 
and less than N) and a number d (direction - either 0 for left or 1 for right). The objective is to return the array 
shifted by p positions in d direction.
Example: For an input array [1, 3, 2, 7, 4, 6] with p=3 and d=0
the expected result would be [7, 4, 6, 1, 3, 2] with the array shifted left by 3 positions.
*/
public class RotateArray {
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 3, 2, 7, 4, 6 }; // given array
		int n = 3;
		System.out.println("Original array: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		for (int i = 0; i < n; i++) {
			int j, first;
			first = arr[0];

			for (j = 0; j < arr.length - 1; j++) {
				arr[j] = arr[j + 1];
			}
			arr[j] = first;
		}

		System.out.println();
		System.out.println("Array after rotation: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
