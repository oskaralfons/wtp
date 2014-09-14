package com.niklar.wtp;

import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.*;

public class DEVerbWeakInseparableResolverTest {
    private final String kennenTemplate = "{{Deutsch Verb unregelmäßig|2=kenn|3=kannte|4=kennt|5=gekannt|8=n|vp=ja|zp=nein|gerund=ja}}";
    private final String seinTemplate = "{{Deutsch Verb unregelmäßig|2=sei|3=war|4=wär|5=gewesen|6=bi|Hilfsverb=sein|vp=nein|zp=nein|gerund=nein\n" +
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
    private final String antunTemplate = "{{Deutsch Verb unregelmäßig|1=an|2=tu|3=tat|4=tät|5=getan|10=tun|vp=ja|zp=ja|gerund=ja}}";
    private final Properties kennenProps = ParsingUtils.parseRule(kennenTemplate, "Deutsch Verb unregelmäßig", 0);
    private final Properties seinProps = ParsingUtils.parseRule(seinTemplate, "Deutsch Verb unregelmäßig", 0);
    private final Properties antunProps = ParsingUtils.parseRule(antunTemplate, "Deutsch Verb unregelmäßig", 0);


    @Test
    public void testBaseForm() throws Exception {
        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(kennenProps))
                .isEqualToIgnoringCase("kennen");
        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(seinProps))
                .isEqualToIgnoringCase("sein");
        assertThat(DEVerbWeakInseparableResolver
                .getBaseForm(antunProps))
                .isEqualToIgnoringCase("antun");
    }
    @Test
    public void testInfinitives() {
        Set<String> kennenInfinitives = Sets.newHashSet("kennen", "gekannt");
        Set<String> seinInfinitives = Sets.newHashSet("sein", "gewesen");
        Set<String> antunInfinitives = Sets.newHashSet("antun", "angetan");

        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.infinitives(kennenProps)))
                .isEqualTo(kennenInfinitives);
        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.infinitives(seinProps)))
                .isEqualTo(seinInfinitives);
        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.infinitives(antunProps)))
                .isEqualTo(antunInfinitives);
    }
    @Test
    public void testExtendedInfinitives() {
        Set<String> kennenInfinitives = Sets.newHashSet("kennen", "gekannt");
        Set<String> seinInfinitives = Sets.newHashSet("sein", "gewesen");
        Set<String> antunInfinitives = Sets.newHashSet("anzutun", "angetan");

        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.extendedInfinitives(kennenProps)))
                .isEqualTo(kennenInfinitives);
        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.extendedInfinitives(seinProps)))
                .isEqualTo(seinInfinitives);
        assertThat(wordFormToStrings(DEVerbWeakInseparableResolver.extendedInfinitives(antunProps)))
                .isEqualTo(antunInfinitives);
    }



    private Set<String> wordFormToStrings(final Collection<WordForm> wordForms) {
        return wordForms.stream()
                .map(WordForm::getWordForm)
                .collect(Collectors.toSet());
    }
}