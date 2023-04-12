import java.io.*;
import java.util.Random;

public class main {

	public static void main(String[] args) throws IOException {
		// create new random object for random stacking
		Random rnd = new Random();
		// open and read "fruits.txt"
		File file = new File("D:\\fruits.txt");
		BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));

		String line;
		// create a new stack for stacking all fruits
		Stack fruitStack = new Stack(10);
		// fill the fruitStack
		while ((line = read.readLine()) != null) {

			line = line.toLowerCase();
			fruitStack.push(line);
		}
		read.close();
		boolean flag;
		// create new stacks (stack1 , stack2)
		/**
		 * WARNING!! if change these stack's sizes you must change temps stack size to
		 * the equal value. so stack1,stack2 and temps are must be equal size and it
		 * can't bigger than fruitStack's size
		 **/
		Stack stack1 = new Stack(5);// you can change size
		Stack stack2 = new Stack(5);// you can change size
		Stack temps = new Stack(5);// you can change size
		Stack tempS = new Stack(10);

		// fill the stack1 randomly
		while (!(stack1.isFull())) {

			// generate random number for stacking randomly
			int random_number = rnd.nextInt(fruitStack.size());
			for (int i = 0; i < random_number; i++) {

				tempS.push(fruitStack.pop());
			}
			// take top fruit on the fruitStack and store it
			String temp = (String) fruitStack.peek();
			flag = true;
			// check to fruits, if new fruit is stock so don't stack it on stack1 just pop
			while (!(stack1.isEmpty())) {

				if ((String) stack1.peek() == (String) temp) {

					flag = false;
				}
				temps.push(stack1.pop());
			}
			// refill stack1
			while (!(temps.isEmpty())) {

				stack1.push(temps.pop());
			}
			if (flag == true) {

				stack1.push(temp);
			}
			// refill fruitStack
			while (!(tempS.isEmpty())) {

				for (int i = 0; i < random_number; i++) {

					fruitStack.push(tempS.pop());
				}
			}

		}
		// same operations with stack1 for stack2
		while (!(stack2.isFull())) {

			int random_number = rnd.nextInt(stack1.size());
			for (int i = 0; i < random_number; i++) {

				tempS.push(stack1.pop());
			}
			String temp = (String) stack1.peek();
			flag = true;
			while (!(stack2.isEmpty())) {

				if ((String) stack2.peek() == (String) temp) {

					flag = false;
				}
				temps.push(stack2.pop());
			}
			while (!(temps.isEmpty())) {

				stack2.push(temps.pop());
			}
			if (flag == true) {

				stack2.push(temp);
			}
			while (!(tempS.isEmpty())) {

				for (int i = 0; i < random_number; i++) {
					stack1.push(tempS.pop());
				}
			}
		}
		// write fruitStack elements on the console and store on the tempS stack
		System.out.print("Fruit Stack:  ");
		while (!(fruitStack.isEmpty())) {

			System.out.print(fruitStack.peek() + "  ");
			tempS.push(fruitStack.pop());
		}
		// refill fruitStack because when we write, its elements gone
		while (!(tempS.isEmpty())) {

			fruitStack.push(tempS.pop());
		}

		System.out.println();
		System.out.println();
		// create score and step variable
		int score = 0;
		int step = 1;

		// until stack1 is empty,do required operations
		while (!(stack1.isEmpty())) {

			// generate random number for randomly stacking to stack1 and stack2
			int random1 = rnd.nextInt(stack1.size()) + 1;
			int random2 = rnd.nextInt(stack1.size()) + 1;
			// write stack1's elements on the console
			System.out
					.println("—————————————————————————————————————————————————————————" + "     " + "score= " + score);
			System.out.print("Stack1: ");
			while (!(stack1.isEmpty())) {

				System.out.print(stack1.peek() + "  ");
				temps.push(stack1.pop());
			}

			// refill stack1
			while (!(temps.isEmpty())) {

				stack1.push(temps.pop());
			}
			// write stack2's elements on the console and refill stack2
			// after all writing operation it must be refill stacks again
			System.out.println();
			System.out.print("Stack2: ");
			while (!(stack2.isEmpty())) {

				System.out.print(stack2.peek() + "  ");
				temps.push(stack2.pop());
			}

			while (!(temps.isEmpty())) {

				stack2.push(temps.pop());
			}
			// write randomly generated numbers on the console
			System.out.println();
			System.out.println();
			System.out.println("Randomly generated numbers: " + random1 + "     " + random2
					+ "                           " + "step= " + step);

			// pop stack1 using random number
			for (int i = 0; i < random1 - 1; i++) {

				temps.push(stack1.pop());
			}
			// store top element on the stack1
			String temp1 = (String) stack1.peek();

			while (!(temps.isEmpty())) {

				stack1.push(temps.pop());
			}
			// like stack1, pop stack2 using random number

			for (int i = 0; i < random2 - 1; i++) {

				temps.push(stack2.pop());
			}
			// and store top element on the stack2 again like stack1
			String temp2 = (String) stack2.peek();

			while (!(temps.isEmpty())) {

				stack2.push(temps.pop());
			}
			// if stored elements same delete them and add +20 points
			if (temp1.equals(temp2)) {

				while (!(stack1.isEmpty())) {

					if ((String) stack1.peek() != (String) temp1) {

						temps.push(stack1.pop());
					} else {

						stack1.pop();
					}
				}
				while (!(temps.isEmpty())) {

					stack1.push(temps.pop());
				}

				while (!(stack2.isEmpty())) {

					if ((String) stack2.peek() != (String) temp2) {

						temps.push(stack2.pop());
					} else {

						stack2.pop();
					}
				}
				while (!(temps.isEmpty())) {

					stack2.push(temps.pop());
				}
				score += 20;
				step++;

			}
			// else stack them again and take out -1 point
			else {

				score -= 1;
				step++;
			}

		}
		// last of program write the empty stacks on the console
		if (stack1.isEmpty()) {

			System.out
					.println("—————————————————————————————————————————————————————————" + "     " + "score= " + score);
			System.out.println("Stack1:");
			System.out.println("Stack2:");
			System.out.println();
			System.out.println("The game is over your score= " + score + "!!!");
			System.out.println("—————————————————————————————————————————————————————————");
		}

		// create changeable name variable for write on the console
		String name = "YOU";
		// open and read "highscoretable.txt"
		File file2 = new File("D:\\highscoretable.txt");
		BufferedReader read2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2), "UTF8"));
		String line2 = read2.readLine();
		// create two stacks to stacking names and scores separately
		// and create two temp's stacks for protecting data
		Stack names = new Stack(10);
		Stack scores = new Stack(10);
		Stack temp = new Stack(10);
		Stack temp2 = new Stack(10);
		// this method for stacking names and scores separately
		// if row number odd -> stack in the names stack
		// else if row number even -> stack in the scores stack
		int i = 0;
		while (line2 != null) {
			i++;// count row number
			if (i % 2 == 1) {// odd->name

				names.push(line2);
			} else {// even score

				scores.push(line2);
			}

			line2 = read2.readLine();
		}
		read2.close();

		// write high score table on the console

		System.out.println("High Score Table:");
		while (!(scores.isEmpty())) {
			temp.push(scores.pop());
		}
		while (!(names.isEmpty())) {
			temp2.push(names.pop());
		}

		boolean flag2 = true;// fix writing your score more than one
		int count = 0;// count player quantity write only top 10 player
		while (!(temp.isEmpty())) {

			if (Integer.parseInt((String) temp.peek()) > score) {// if existing score bigger than your score

				System.out.println(temp2.peek() + "\t" + temp.peek());// write existing score and name to the console
				count++;
				scores.push(temp.pop());// refill scores and names stacks for protecting data
				names.push(temp2.pop());

			} else if (flag2 == true) {

				System.out.println(name + "     " + score);// write your score and name
				count++;
				System.out.println(temp2.peek() + "\t" + temp.peek());
				count++;
				scores.push(temp.pop());
				names.push(temp2.pop());
				flag2 = false;// for don't write your score again and again set flag false
			} else if (count <= 9) {// for don't writing players more than 10

				System.out.println(temp2.peek() + "\t" + temp.peek());
				count++;
				scores.push(temp.pop());
				names.push(temp2.pop());
			}
		}

	}
}
