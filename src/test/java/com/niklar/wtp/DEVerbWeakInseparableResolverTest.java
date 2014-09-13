package com.niklar.wtp;

import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.*;

public class DEVerbWeakInseparableResolverTest {
    @Test
    public void testBaseForm() throws Exception {
        final String kennenTemplate = "{{Deutsch Verb unregelmäßig|2=kenn|3=kannte|4=kennt|5=gekannt|8=n|vp=ja|zp=nein|gerund=ja}}";
        final String seinTemplate = "{{Deutsch Verb unregelmäßig|2=sei|3=war|4=wär|5=gewesen|6=bi|Hilfsverb=sein|vp=nein|zp=nein|gerund=nein\n" +
                "|Infinitiv Präsens=sein\n" +
                "|Imperativ (du)=sei\n" +
                "|Imperativ (ihr)=seid\n" +
                "|Indikativ Präsens (ich)=bin\n" +
                "|Indikativ Präsens (du)=bist\n" +
                "|Indikativ Präsens (man)=ist\n" +
                "|Indikativ Präsens (wir)=sind\n" +
                "|Indikativ Präsens (ihr)=seid\n" +
                "|Indikativ Präsens (sie)=sind\n" +
                "|Konjunktiv Präsens (ich)=sei\n" +
                "|Konjunktiv Präsens (du)=seiest\n" +
                "|Konjunktiv Präsens Alternativform (du)=seist\n" +
                "|Konjunktiv Präsens (man)=sei\n" +
                "|Konjunktiv Präsens (wir)=seien\n" +
                "|Konjunktiv Präsens (ihr)=seiet\n" +
                "|Konjunktiv Präsens (sie)=seien}}\n";
        final String antunTemplate = "{{Deutsch Verb unregelmäßig|1=an|2=tu|3=tat|4=tät|5=getan|10=tun|vp=ja|zp=ja|gerund=ja}}";

        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(ParsingUtils.parseRule(kennenTemplate, "Deutsch Verb unregelmäßig", 0)))
                .isEqualToIgnoringCase("kennen");
        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(ParsingUtils.parseRule(seinTemplate, "Deutsch Verb unregelmäßig", 0)))
                .isEqualToIgnoringCase("sein");
        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(ParsingUtils.parseRule(antunTemplate, "Deutsch Verb unregelmäßig", 0)))
                .isEqualToIgnoringCase("antun");
    }
}