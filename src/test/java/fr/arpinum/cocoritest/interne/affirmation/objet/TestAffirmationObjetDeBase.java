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

import fr.arpinum.cocoritest.interne.exception.CapteurExceptionDeBase;
import fr.arpinum.cocoritest.specification.Specification;
import org.junit.Before;
import org.junit.Test;

import static fr.arpinum.cocoritest.Affirmations.alors;
import static fr.arpinum.cocoritest.FabriquePourTest.*;

public class TestAffirmationObjetDeBase {

    @Before
    public void avantChaqueTest() {
        capteur = new CapteurExceptionDeBase();
    }

    @Test
    public void onPeutAffirmerQuUnNombreEstLeMêmeNombre() {
        creeAffirmation(1).est(1);
    }

    @Test
    public void onNePeutPasAffirmerQuUnNombreEstUnNombreDifférent() {
        Exception exception = capteur.capte(() -> creeAffirmation(1).est(2));

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <1> au lieu de <2>."));
    }

    @Test
    public void onPeutAffirmerQuUnNombreNEstPasUnNombreDifférent() {
        creeAffirmation(1).nEstPas(2);
    }

    @Test
    public void onNePeutPasAffirmerQuUnNombreNEstPasLeMêmeNombre() {
        Exception exception = capteur.capte(() -> creeAffirmation(1).nEstPas(1));

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <1> alors que ce n'était pas voulu."));
    }

    @Test
    public void onNePeutPasAffirmerQuUneChaîneEstUneChaîneDifférente() {
        Exception exception = capteur.capte(() -> creeAffirmation("toto").est("tutu"));

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <toto> au lieu de <tutu>."));
    }

    @Test
    public void onPeutAffirmerQuUnObjetEstNul() {
        creeAffirmation(null).estNul();
        creeAffirmation(null).estNulle();
    }

    @Test
    public void onPeutAffirmerQuUnObjetNEstPasNul() {
        creeAffirmation("toto").nEstPasNul();
        creeAffirmation("toto").nEstPasNulle();
    }

    @Test
    public void onNePeutPasAffirmerQuUnObjetNonNulEstNul() {
        Exception exception = capteur.capte(() -> creeAffirmation("toto").estNul());

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <toto> au lieu de <nul>."));
    }

    @Test
    public void onNePeutPasAffirmerAuFémininQuUnObjetNonNulEstNul() {
        Exception exception = capteur.capte(() -> creeAffirmation("toto").estNulle());

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <toto> au lieu de <nul>."));
    }

    @Test
    public void onNePeutPasAffirmerQuUnObjetNulNEstPasNul() {
        Exception exception = capteur.capte(() -> creeAffirmation(null).nEstPasNul());

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <nul> alors que ce n'était pas voulu."));
    }

    @Test
    public void onNePeutPasAffirmerAuFémininQuUnObjetNulNEstPasNul() {
        Exception exception = capteur.capte(() -> creeAffirmation(null).nEstPasNulle());

        alors().cette(exception).respecte(créeSpécificationException("L'objet est <nul> alors que ce n'était pas voulu."));
    }

    @Test
    public void onPeutAffirmerQuUnObjetRespecteUneSpecification() {
        Specification<Integer> spécification = créeSpécificationSatisfaite();

        creeAffirmation(1).respecte(spécification);
    }

    @Test
    public void onNePeutPasAffirmerATortQuUnObjetRespecteUneSpecification() {
        final Specification<Integer> spécification = créeSpécificationInsatisfaite();

        Exception exception = capteur.capte(() -> creeAffirmation(1).respecte(spécification));

        alors().cette(exception).respecte(créeSpécificationException("1 ne respecte pas la spécification."));
    }

    private static <T> AffirmationObjetDeBase<T> creeAffirmation(T valeur) {
        return new AffirmationObjetDeBase<>(valeur);
    }

    private CapteurExceptionDeBase capteur;
}