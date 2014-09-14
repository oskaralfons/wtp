package com.niklar.wtp;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

/**
 * Created by Oskar on 13.09.2014.
 */
public class DEVerbWeakInseparableResolver {
    static Collection<WordForm> resolve(final String wiktiTemplate) {
        final Properties props = ParsingUtils.parseRule(wiktiTemplate, "Deutsch Verb unregelmäßig", 0);
        final Collection<WordForm> conjugations = new ArrayList<WordForm>();

        return conjugations;
    }

    public static Collection<WordForm> infinitives(final Properties props) {
        final ArrayList<WordForm> infinitives = new ArrayList<WordForm>();
        final String prefix = props.getProperty("1", "");
        // Infinitiv Präsens Aktiv
        infinitives.add(new WordForm(getBaseForm(props)));
        // Infinitiv Perfekt Aktiv
        infinitives.add(new WordForm(prefix + props.getProperty("5", "")));
        // Infinitiv Präsens Aktiv Partizip+
        if (!"".equals(props.getProperty("Partizip+", ""))) {
            infinitives.add(new WordForm(prefix + props.getProperty("Partizip+", "")));
        }
        //  Vorgangspassiv / Zusstandspassiv
        String vp = props.getProperty("vp", "");
        final String zp = props.getProperty("zp","");
        HashSet<String> vpZpYes = Sets.newHashSet("ja", "1", "sie_werden", "vp3", "zp3");
        if (!"".equals(vp)){
            if (vpZpYes.contains(vp)){
                // Vorgangspassiv Infinitiv Präsens
                infinitives.add(new WordForm(prefix+props.get("5")));
                // Vorgangspassiv Infinitiv Perfekt
                infinitives.add(new WordForm(prefix+props.get("5")));
            }
            if (vpZpYes.contains(zp)){
                // Zustandspassiv Infinitiv Präsens
                infinitives.add(new WordForm(prefix+props.get("5")));
                // Zustandspassiv Infinitiv Perfekt
                infinitives.add(new WordForm(prefix+props.get("5")));
            }
        }
        return infinitives;
    }

    public static String getBaseForm(final Properties props) {
        final String prefix = props.getProperty("1", "");
        if (!props.getProperty("Infinitiv Präsens", "").isEmpty()) {
            return prefix + props.getProperty("Infinitiv Präsens");
        } else if (!props.getProperty("10", "").isEmpty()) {
            return prefix + props.getProperty("10");
        } else if (!props.getProperty("2", "").isEmpty()) {
            return prefix + props.getProperty("2") + "en";
        }
        //TODO throw exception
        return "";
    }


}
