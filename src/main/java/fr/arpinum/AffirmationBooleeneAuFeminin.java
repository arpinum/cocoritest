package fr.arpinum;

public class AffirmationBooleeneAuFeminin extends AffirmationBooleene {

	public AffirmationBooleeneAuFeminin(Boolean valeur) {
		super(valeur);
	}

	public void estVraie() {
		estVraiInterne();
	}

	public void estFausse() {
		estFauxInterne();
	}
}
