package sample;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CustomTextField extends TextField {
	public CustomTextField() {

		this.addEventFilter(KeyEvent.KEY_TYPED, t -> {
			try {
				char ar[] = t.getCharacter().toCharArray();
				char ch = ar[t.getCharacter().toCharArray().length - 1];
				if (!(ch >= '0' && ch <= '9')) {
					t.consume();
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		});
	}

}
