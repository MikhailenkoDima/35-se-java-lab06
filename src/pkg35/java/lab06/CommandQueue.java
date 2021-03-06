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
import java.util.Vector;


/** Создает обработчик
 * потока, выполняющего
 * объекты с интерфейсом
 * Command; шаблон
 * Worker Thread
 * @author 35
 * @version 1.0
 * @see Command
 */

public class CommandQueue implements Queue {
	
	/** Очередь задач */
	private Vector<Command> tasks;
	
	/** Флаг ожидания */
	private boolean waiting;
	
	/** Флаг завершения */
	private boolean shutdown;
	
	/** Устанавливает флаг завершения */
	public void shutdown() {
		shutdown = true;
	}
	
	/** Инициализация {@linkplain CommandQueue#tasks}
	 * {@linkplain CommandQueue#waiting}
	 * {@linkplain CommandQueue#waiting};
	 * создает поток для класса {@linkplain CommandQueue.Worker}
	 */
	public CommandQueue() {
		tasks = new Vector<Command>();
		waiting = false;
		new Thread(new Worker()).start();
	}
	
	@Override
	public void put(Command r) {
		tasks.add(r);
		if (waiting) {
			synchronized (this) {
				notifyAll();
			}
		}
	}
	
	@Override
	public Command take() {
		if (tasks.isEmpty()) {
			synchronized (this) {
				waiting = true;
				try {
					wait();
				} catch (InterruptedException ie) {
					waiting = false;
				}
			}
		}
		return (Command)tasks.remove(0);
	}
	
	/** Обслуживает очередь
	 * задач; шаблон
	 * Worker Thread
	 * @author 35
	 * @version 1.0
	 * @see Runnable
	 */
	private class Worker implements Runnable {
		
		/** Извлекает из очереди
		 * готовые к выполнению
		 * задачи; шаблон
		 * Worker Thread */
		public void run() {
			while (!shutdown) {
				Command r = take();
				r.execute();
	
			}
		}
        }}