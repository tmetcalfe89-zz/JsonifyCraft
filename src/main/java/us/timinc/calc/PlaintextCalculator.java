package us.timinc.calc;

import java.util.*;

public class PlaintextCalculator {
	private static final String[] operands = { "+", "-" };

	private enum EQUATIONARRAYITEMTYPE {
		operand, variable, value
	}

	public static int solve(HashMap<String, Integer> values, String equation) {
		String[] equationArray = getEquationArray(equation);
		int currentValue = 0;
		String currentOp = "+";
		for (String equationArrayItem : equationArray) {
			switch (getEquationArrayItemType(equationArrayItem)) {
			case operand:
				currentOp = equationArrayItem;
				break;
			case value:
				switch (currentOp) {
				case "+":
					currentValue += Integer.parseInt(equationArrayItem);
					break;
				case "-":
					currentValue -= Integer.parseInt(equationArrayItem);
					break;
				}
				break;
			case variable:
				switch (currentOp) {
				case "+":
					currentValue += values.get(equationArrayItem);
					break;
				case "-":
					currentValue -= values.get(equationArrayItem);
					break;
				}
				break;
			default:
				break;
			}
		}
		return currentValue;
	}

	private static EQUATIONARRAYITEMTYPE getEquationArrayItemType(String equationArrayItem) {
		if (isStringInt(equationArrayItem))
			return EQUATIONARRAYITEMTYPE.value;
		if (isOperand(equationArrayItem))
			return EQUATIONARRAYITEMTYPE.operand;
		return EQUATIONARRAYITEMTYPE.variable;
	}

	private static boolean isStringInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private static boolean isOperand(String equationArrayItem) {
		return Arrays.stream(operands).anyMatch(equationArrayItem::equals);
	}

	private static String[] getEquationArray(String equation) {
		ArrayList<String> equationStack = new ArrayList<>();
		String currentStackItem = "";
		for (int i = 0; i < equation.length(); i++) {
			String currentChar = equation.charAt(i) + "";
			if (isOperand(currentChar) || isOperand(currentStackItem)) {
				equationStack.add(currentStackItem);
				currentStackItem = "";
			}
			currentStackItem = currentStackItem.concat(currentChar);
		}
		equationStack.add(currentStackItem);
		return equationStack.toArray(new String[0]);
	}
}
