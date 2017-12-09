
/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */


public class gridToCoordinates {
	public int gridX;
	int gridY;

	boolean isStagerred = false;

	int coordX;
	int coordY;

	/**
	 * 
	 * @return Xcoord of mouse
	 */
	public int getCoordX() {

		if (gridX == 0) {
			if (isStagerred == true) {
				double tempX = 30.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 65.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 1) {
			if (isStagerred == true) {
				double tempX = 100.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 135.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 2) {
			if (isStagerred == true) {
				double tempX = 170.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 205.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 3) {
			if (isStagerred == true) {
				double tempX = 240.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 275.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}

		} else if (gridX == 4) {
			if (isStagerred == true) {
				double tempX = 310.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 345.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 5) {
			if (isStagerred == true) {
				double tempX = 380.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 415.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 6) {
			if (isStagerred == true) {
				double tempX = 450.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 485.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 7) {
			if (isStagerred == true) {
				double tempX = 520.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 555.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 8) {
			if (isStagerred == true) {
				double tempX = 590.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 625.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 9) {
			if (isStagerred == true) {
				double tempX = 660.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 695.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 10) {
			if (isStagerred == true) {
				double tempX = 730.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 765.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		} else if (gridX == 11) {
			if (isStagerred == true) {
				double tempX = 800.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 835.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;

			}
		} else if (gridX == 12) {
			if (isStagerred == true) {
				double tempX = 870.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			} else {
				double tempX = 905.0 * PopView.SCALE_FACTOR;
				coordX = (int) tempX;
			}
		}

		return coordX;

	}

	public int getCoordY() {
		if (gridY == 0) {
			double tempY = (-58 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 1) {
			double tempY = (12 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 2) {
			double tempY = (82 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 3) {
			double tempY = (152 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 4) {
			double tempY = (222 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 5) {
			double tempY = (292 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 6) {
			double tempY = (362 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 7) {
			double tempY = (430 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 8) {
			double tempY = (502 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 9) {
			double tempY = (572 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		} else if (gridY == 10) {
			double tempY = (642 + 20) * PopView.SCALE_FACTOR;
			coordY = (int) tempY;
		}

		return coordY;
	}

}
