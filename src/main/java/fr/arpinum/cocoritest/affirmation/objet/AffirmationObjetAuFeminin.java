package fr.arpinum.cocoritest.affirmation.objet;

/**
 * Représente une affirmation concernant un objet typé. L'affirmation est au féminin.
 *
 * @param <T> le type de l'objet concerné par l'affirmation.
 */
public interface AffirmationObjetAuFeminin<T> {

	/**
	 * Affirme que la valeur est la valeur attendue.
	 *
	 * @param valeurAttendue la valeur attendue.
	 * @throws fr.arpinum.cocoritest.affirmation.ExceptionAffirmation
	 *          si l'affirmation est erronée.
	 */
	void est(T valeurAttendue);

	/**
	 * Affirme que la valeur n'est pas la valeur attendue.
	 *
	 * @param valeurNonAttendue la valeur non attendue.
	 * @throws fr.arpinum.cocoritest.affirmation.ExceptionAffirmation
	 *          si l'affirmation est erronée.
	 */
	void nEstPas(T valeurNonAttendue);

	/**
	 * Affirme que la valeur est nulle.
	 *
	 * @throws fr.arpinum.cocoritest.affirmation.ExceptionAffirmation
	 *          si l'affirmation est erronée.
	 */
	void estNulle();

	/**
	 * Affirme que la valeur n'est pas nulle.
	 *
	 * @throws fr.arpinum.cocoritest.affirmation.ExceptionAffirmation
	 *          si l'affirmation est erronée.
	 */
	void nEstPasNulle();

	/**
	 * Affirme que la valeur respecte la spécification.
	 *
	 * @param spécification la spécification à respecter.
	 * @throws fr.arpinum.cocoritest.affirmation.ExceptionAffirmation
	 *          si l'affirmation est erronée.
	 */
	void respecte(Specification<T> spécification);
}
