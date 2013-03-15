package fr.arpinum.affirmation.booleene;

import fr.arpinum.affirmation.ExceptionAffirmation;
import fr.arpinum.outils.Objets;

public abstract class AffirmationBooleene {

	protected AffirmationBooleene(Boolean valeur) {
		booléen = valeur;
	}

	protected void estVraiInterne() {
		if (!estSatisfaite()) {
			throw new ExceptionAffirmation("La valeur n'est pas vraie.");
		}
	}

	protected void estFauxInterne() {
		if (estSatisfaite()) {
			throw new ExceptionAffirmation("La valeur n'est pas fausse.");
		}
	}

	private Boolean estSatisfaite() {
		return Objets.egaux(booléen, true);
	}

	private final Boolean booléen;
}