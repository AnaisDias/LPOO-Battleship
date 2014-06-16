package controllers;

import logic.Board;
import logic.ShipType;
import logic.HitStatus;

public final class FireControl {

	private final Board target;

	private final WaitControl waiting;

	private final String sunkMessageTemplate = " has SUNK your ";

    private final String hitMessageTemplate = " has hit!";

	private String hitMessage;

	public FireControl(WaitControl waiting, Board target) {
		this.waiting = waiting;
		this.target = target;
		this.hitMessage = null;
	}

	public HitStatus fireShot(int x, int y) {

		HitStatus outcome = this.target.shoot(x, y);

		if (HitStatus.SUNK == outcome) {
			ShipType t = this.target.shipTypeAt(x, y);
			this.hitMessage = waiting.getActivePlayer().getPlayerName()
					+ this.sunkMessageTemplate + t.toString() + "! ";

		} else if (HitStatus.HIT == outcome) {
            this.hitMessage = waiting.getActivePlayer().getPlayerName()
                    + this.hitMessageTemplate;

        }

		if (!this.target.shipsRemaining()) {
			this.waiting.setHasWon(true);
			this.hitMessage += waiting.getActivePlayer().getPlayerName()
					+ " has sunk all your ships and won the game!";
}

		return outcome;
	}

	public boolean isShootable(int x, int y) {
		return !this.target.isViewable(x, y);
	}

	public Board getTarget() {
		return this.target;
	}

	public void endTurn() {

		String tempMsg = this.hitMessage;
		this.hitMessage = null;

		this.waiting.nextScreen(tempMsg);
	}

}