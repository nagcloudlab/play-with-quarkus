package example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Ex2 {

	public static void main(String[] args) {

		List<String> menu = new ArrayList<>();
		menu.add("veg");
		menu.add("veg");
		menu.add("nveg");
		menu.add("veg");
		menu.add("veg");
		menu.add("nveg");
		menu.add("veg");
		menu.add("nveg");

		// ....
		// way-1 : imperative style

		// remove non-veg item ( intention/what & implementation/how tightly coupled
//		Iterator<String> iterator = menu.iterator();
//		while (iterator.hasNext()) {
//			String item = (String) iterator.next();
//			if (item.equals("nveg"))
//				iterator.remove();
//		}
//		System.out.println(menu);

		// way-2 : declarative programming ( class | anonymous | lambda )
//		removeFoodItem(menu, item -> item.equals("nveg"));
		menu.removeIf(item -> item.equals("nveg"));

	}

	public static void removeFoodItem(List<String> menu, Predicate<String> predicate) {
		Iterator<String> iterator = menu.iterator();
		while (iterator.hasNext()) {
			String item = (String) iterator.next();
			if (predicate.test(item))
				;
			iterator.remove();
		}
		System.out.println(menu);
	}

}
