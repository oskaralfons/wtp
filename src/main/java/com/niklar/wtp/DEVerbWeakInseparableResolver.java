package com.niklar.wtp;

import java.util.ArrayList;
import java.util.Collection;
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

    private static Collection<WordForm> infinitives(final Properties props) {
        ArrayList<WordForm> infinitives = new ArrayList<WordForm>();

        return infinitives;
    }

    public static String getBaseForm(final Properties props) {
        final String prefix = props.getProperty("1", "");
        if (!props.getProperty("Infinitiv Präsens", "").isEmpty()) {
            return prefix + props.getProperty("Infinitiv Präsens");
        } else if (!props.getProperty("10", "").isEmpty()) {
            return prefix + props.getProperty("10");
        } else if (!props.getProperty("2", "").isEmpty()){
            return prefix + props.getProperty("2")+"en";
        }
        //TODO throw exception
        return "";
    }
}
