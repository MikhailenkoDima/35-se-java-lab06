/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg35.java.lab06;

/**
 *
 * @author Phoen1x
 */
public class GenerateConsoleCommand implements ConsoleCommand {
	
	/** Объект, реализующий интерфейс {@linkplain View};
	 * обслуживает коллекцию объектов {@linkplain ex01.Item2d}
	 */
	private View view;

	/** Инициализирует поле {@linkplain GenerateConsoleCommand#view}
	 * @param view объект, реализующий интерфейс {@linkplain View}
	 */
	public GenerateConsoleCommand(View view) {
		this.view = view;
	}

	@Override
	public char getKey() {
		return 'g';
	}

	@Override
	public String toString() {
		return "'g'enerate";
	}

	@Override
	public void execute() {
		System.out.println("Random generation.");
		view.viewInit();
		view.viewShow();
	}
}