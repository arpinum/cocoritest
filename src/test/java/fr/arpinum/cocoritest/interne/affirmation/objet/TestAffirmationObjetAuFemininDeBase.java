/*
 * Copyright (C) 2013, Arpinum
 *
 * Cocoritest est un logiciel libre ; vous pouvez le redistribuer ou le modifier suivant les termes de la GNU Lesser
 * General Public License telle que publiée par la Free Software Foundation ; soit la version 3 de la licence,
 * soit (à votre gré) toute version ultérieure.
 *
 * Cocoritest est distribué dans l'espoir qu'il sera utile, mais SANS AUCUNE GARANTIE ; pas même la garantie
 * implicite de COMMERCIABILISABILITÉ ni d'ADÉQUATION à UN OBJECTIF PARTICULIER. Consultez la GNU Lesser General
 * Public License pour plus de détails.
 *
 * Vous devez avoir reçu une copie de la GNU Lesser General Public License en même temps que Cocoritest ; si ce n'est
  * pas le cas, consultez http://www.gnu.org/licenses.
 */

package fr.arpinum.cocoritest.interne.affirmation.objet;

import static fr.arpinum.cocoritest.Affirmations.*;
import static fr.arpinum.cocoritest.FabriquePourTest.*;

import org.junit.Before;
import org.junit.Test;

import fr.arpinum.cocoritest.interne.exception.CapteurExceptionDeBase;
import fr.arpinum.cocoritest.specification.Specification;

public class TestAffirmationObjetAuFemininDeBase {

	@Before
	public void avantChaqueTest() {
		capteur = new CapteurExceptionDeBase();
	}

	@Test
	public void onPeutAffirmerQuUnNombreEstLeMêmeNombre() {
		alors().la(valeurUn()).est(1);
	}

	@Test
	public void onNePeutPasAffirmerQuUnNombreEstUnNombreDifférent() {
		Exception exception = capteur.capte(() -> alors().la(valeurUn()).est(2));

		alors().cette(exception).respecte(créeSpécificationException("L'objet est <1> au lieu de <2>."));
	}

	@Test
	public void onPeutAffirmerQuUnNombreNEstPasUnNombreDifférent() {
		alors().la(valeurUn()).nEstPas(2);
	}

	@Test
	public void onNePeutPasAffirmerQuUnNombreNEstPasLeMêmeNombre() {
		Exception exception = capteur.capte(() -> alors().la(valeurUn()).nEstPas(1));

		String messageAttendu = "L'objet est <1> alors que ce n'était pas voulu.";
		alors().cette(exception).respecte(créeSpécificationException(messageAttendu));
	}

	@Test
	public void onNePeutPasAffirmerQuUneChaîneEstUneChaîneDifférente() {
		Exception exception = capteur.capte(() -> alors().la(chaineToto()).est("tutu"));

		alors().cette(exception).respecte(créeSpécificationException("L'objet est <toto> au lieu de <tutu>."));
	}

	@Test
	public void onPeutAffirmerQuUnObjetEstNul() {
		alors().la(valeurNulle()).estNulle();
	}

	@Test
	public void onPeutAffirmerQuUnObjetNEstPasNul() {
		alors().la(chaineToto()).nEstPasNulle();
	}

	@Test
	public void onNePeutPasAffirmerQuUnObjetNonNulEstNul() {
		Exception exception = capteur.capte(() -> alors().la(chaineToto()).estNulle());

		alors().cette(exception).respecte(créeSpécificationException("L'objet est <toto> au lieu de <nul>."));
	}

	@Test
	public void onNePeutPasAffirmerQuUnObjetNulNEstPasNul() {
		Exception exception = capteur.capte(() -> alors().la(valeurNulle()).nEstPasNulle());

		String messageAttendu = "L'objet est <nul> alors que ce n'était pas voulu.";
		alors().cette(exception).respecte(créeSpécificationException(messageAttendu));
	}

	@Test
	public void onPeutAffirmerQuUnObjetRespecteUneSpecification() {
		Specification<Integer> spécification = créeSpécificationSatisfaite();

		alors().la(valeurUn()).respecte(spécification);
	}

	@Test
	public void onNePeutPasAffirmerATortQuUnObjetRespecteUneSpecification() {
		final Specification<Integer> spécification = créeSpécificationInsatisfaite();

		Exception exception = capteur.capte(() -> alors().la(valeurUn()).respecte(spécification));

		alors().cette(exception).respecte(créeSpécificationException("1 ne respecte pas la spécification."));
	}

	@Test
	public void onPeutAffirmerSurUnObjetDeManiereFluide() {
		alors().la(chaineToto()).nEstPasNulle()
				.et().est("toto")
				.et().respecte(créeSpécificationSatisfaite())
				.et().nEstPasNulle();
	}

	private int valeurUn() {
		return 1;
	}

	private String chaineToto() {
		return "toto";
	}

	private Object valeurNulle() {
		return null;
	}

	private CapteurExceptionDeBase capteur;
}