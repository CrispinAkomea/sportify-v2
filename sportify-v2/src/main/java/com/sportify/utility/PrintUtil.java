package com.sportify.utility;

import java.util.Collection;

public class PrintUtil {

	public static <C> void printCollection(Collection<C> collection) {
		for (C item : collection)
			System.out.println(item);
	}

	public static <C, T> void printArray(T[] array) {
		for (T item : array)
			System.out.println(item);
	}

}