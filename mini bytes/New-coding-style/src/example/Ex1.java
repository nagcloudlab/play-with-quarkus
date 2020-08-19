package example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// behavior/function/method parameterization pattern

enum Color {
	RED, GREEN
}

class Apple {

	private int weight = 0;
	private Color color;

	public Apple(int weight, Color color) {
		this.weight = weight;
		this.color = color;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@SuppressWarnings("boxing")
	@Override
	public String toString() {
		return String.format("Apple{color=%s, weight=%d}", color, weight);
	}
	
	public boolean isRedApple() {
		return this.color.equals(Color.RED);
	}
	public boolean isGeenApple() {
		return this.color.equals(Color.GREEN);
	}

}

interface ApplePredicate{
	boolean test(Apple apple);
}

class GreenApplePredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return apple.getColor().equals(Color.GREEN);
	}
}
class RedApplePredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return apple.getColor().equals(Color.RED);
	}
}

public class Ex1 {

	public static void main(String[] args) {
		
		 List<Apple> inventory = Arrays.asList(
			        new Apple(80, Color.GREEN),
			        new Apple(155, Color.GREEN),
			        new Apple(120, Color.RED));
		 
		 
		 //#5
		 List<Apple> heavyApples=filterApples(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return apple.getWeight()>150;
			}
		} );
		 System.out.println(heavyApples);
		 
		 //#6
		 List<Apple> greenApples=filterApples(inventory, Apple::isGeenApple);
		 List<Apple> redApples=filterApples(inventory, Apple::isRedApple);
		 
		 
	}
	
	//#4
	public static List<Apple> filterApples(List<Apple> inventory,ApplePredicate applePredicate) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (applePredicate.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	
	//#3
	public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (flag && apple.getColor() == color || !flag && apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}
	
	
	
	// #2
	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == color) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}
	
	// #1 
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == Color.GREEN) {
				result.add(apple);
			}
		}
		return result;
	}

}
