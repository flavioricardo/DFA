import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public class DFA {

	private static String ABC[];
	private static String states[];
	private static String transitions[][];
	private static String initialState;
	private static String finalStates[];
	private static Integer count;
	private static ArrayList<String> outputs = new ArrayList<String>();
	private static ArrayList<String> entries = new ArrayList<String>();

	public static void main(String[] args) {
		fileReader(new File("entrada.in"));
		for (Iterator<String> iterator = entries.iterator(); iterator.hasNext();)
			outputs.add(simulator(iterator.next()));
		fileWriter();
	}

	public static String simulator(String word) {
		int initialState = simpleItemPosition(getStates(), getInitialState());
		for (int i = 0; i < word.length(); i++)
			initialState = simpleItemPosition(
					getStates(),
					transitions[initialState][simpleItemPosition(getABC(),
							word.charAt(i))]);
		if (simpleItemPosition(getFinalStates(), getStates()[initialState]) != -1)
			return "aceita";
		else
			return "rejeitada";
	}

	public static int simpleItemPosition(String[] itens, char item) {
		for (int i = 0; i < itens.length; i++)
			if (itens[i].toString().equals(String.valueOf(item)))
				return i;
		return -1;
	}

	public static int simpleItemPosition(String[] itens, String item) {
		for (int i = 0; i < itens.length; i++)
			if (itens[i].equals(item))
				return i;
		return -1;
	}

	public static int specialItemPosition(ArrayList<String> itens, String item) {
		for (int i = 0; i < itens.size(); i++) {
			if (itens.get(i).equals(item))
				return i;
		}
		return -1;
	}

	public static void fileReader(File file) {
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);

			try {

				DFA.setABC(bufferedReader.readLine().split(" "));
				DFA.setStates(bufferedReader.readLine().split(" "));

				transitions = new String[getStates().length][getABC().length];

				for (int line = 0; line < transitions.length; line++) {
					String readLine = bufferedReader.readLine();
					for (int column = 0; column < readLine.split(" ").length; column++)
						transitions[line][column] = readLine.split(" ")[column];
				}

				DFA.setInitialState(bufferedReader.readLine());
				DFA.setFinalStates(bufferedReader.readLine().split(" "));

				bufferedReader.readLine();
				DFA.setCount(Integer.parseInt(bufferedReader.readLine()));

				while (bufferedReader.ready())
					entries.add(bufferedReader.readLine());

				bufferedReader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void fileWriter() {
		try {
			File file = new File("saida.out");
			Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(getCount() + "\n");
			for (Iterator<String> iterator = entries.iterator(); iterator
					.hasNext();) {
				String entry = iterator.next();
				writer.write(entry + ":"
						+ getOutputs().get(specialItemPosition(entries, entry))
						+ "\n");
			}
			System.out.println("Simulação efetuada com sucesso!");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getABC() {
		return ABC;
	}

	public static void setABC(String[] ABC) {
		DFA.ABC = ABC;
	}

	public static String[] getStates() {
		return states;
	}

	public static void setStates(String[] states) {
		DFA.states = states;
	}

	public static String getInitialState() {
		return initialState;
	}

	public static void setInitialState(String initialState) {
		DFA.initialState = initialState;
	}

	public static String[] getFinalStates() {
		return finalStates;
	}

	public static void setFinalStates(String[] finalStates) {
		DFA.finalStates = finalStates;
	}

	public static Integer getCount() {
		return count;
	}

	public static void setCount(Integer count) {
		DFA.count = count;
	}

	public static String[][] getTransitions() {
		return transitions;
	}

	public static ArrayList<String> getEntries() {
		return entries;
	}

	public static void setOutputs(ArrayList<String> outputs) {
		DFA.outputs = outputs;
	}

	public static ArrayList<String> getOutputs() {
		return outputs;
	}

}