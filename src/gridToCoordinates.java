
public class gridToCoordinates {
	public int gridX;
	int gridY;

	boolean isStagerred = false;

	int coordX;
	int coordY;

	public int getCoordX() {

		if (gridX == 0) {
			if (isStagerred == true)
				coordX = 30;
			else
				coordX = 65;
		} else if (gridX == 1) {
			if (isStagerred == true)
				coordX = 100;
			else
				coordX = 135;
		} else if (gridX == 2) {
			if (isStagerred == true)
				coordX = 170;
			else
				coordX = 205;
		} else if (gridX == 3) {
			if (isStagerred == true)
				coordX = 240;
			else
				coordX = 275;

		} else if (gridX == 4) {
			if (isStagerred == true)
				coordX = 310;
			else
				coordX = 345;
		} else if (gridX == 5) {
			if (isStagerred == true)
				coordX = 380;
			else
				coordX = 415;
		} else if (gridX == 6) {
			if (isStagerred == true)
				coordX = 450;
			else
				coordX = 485;
		} else if (gridX == 7) {
			if (isStagerred == true)
				coordX = 520;
			else
				coordX = 555;
		} else if (gridX == 8) {
			if (isStagerred == true)
				coordX = 590;
			else
				coordX = 625;
		} else if (gridX == 9) {
			if (isStagerred == true)
				coordX = 660;
			else
				coordX = 695;
		} else if (gridX == 10) {
			if (isStagerred == true)
				coordX = 730;
			else
				coordX = 765;
		} else if (gridX == 11) {
			if (isStagerred == true)
				coordX = 800;
			else
				coordX = 835;
		} else if (gridX == 12) {
			if (isStagerred == true)
				coordX = 870;
			else
				coordX = 905;
		}

		return coordX;

	}

	public int getCoordY() {
		if (gridY == 0) {
			coordY = -58 + 20;
		} else if (gridY == 1) {
			coordY = 12 + 20;
		} else if (gridY == 2) {
			coordY = 82 + 20;
		} else if (gridY == 3) {
			coordY = 152 + 20;
		} else if (gridY == 4) {
			coordY = 222 + 20;
		} else if (gridY == 5) {
			coordY = 292 + 20;
		} else if (gridY == 6) {
			coordY = 362 + 20;
		} else if (gridY == 7) {
			coordY = 430 + 20;
		} else if (gridY == 8) {
			coordY = 502 + 20;
		} else if (gridY == 9) {
			coordY = 572 + 20;
		} else if (gridY == 10) {
			coordY = 642 + 20;
		}

		return coordY;
	}

}
