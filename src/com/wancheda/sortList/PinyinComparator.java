package com.wancheda.sortList;

import java.util.Comparator;

/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<CarEntity> {

	public int compare(CarEntity o1, CarEntity o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
