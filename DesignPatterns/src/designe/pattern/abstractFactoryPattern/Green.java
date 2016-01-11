package designe.pattern.abstractFactoryPattern;
import designe.pattern.abstractFactoryPattern.Color;

public class Green implements Color {

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}

}
