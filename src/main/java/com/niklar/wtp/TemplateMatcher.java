package com.niklar.wtp;

import java.util.Optional;

/**
 * Created by Oskar on 13.09.2014.
 */
public class TemplateMatcher {

    public enum Template {
        DE_VERB_IRREGULAR,
        DE_VERB_WEAK_INSEPARABLE,
        DE_VERB_WEAK_INSEPARABLE_REFLEXIVE,
        DE_VERB_WEAK_SEPARABLE,
        DE_VERB_WEAK_SEPARABLE_REFLEXIVE,
        DE_VERB_WEAK_DOUBLE_SEPARABLE,
        DE_ADJECTIVE_DEKLINITION
    }

    public static Optional<Template> matchTemplate(final String wiktionaryTemplate) {
        if (wiktionaryTemplate.startsWith("{{Deutsch Verb unregelmäßig")) {
            return   Optional.of(Template.DE_VERB_IRREGULAR);
        } else if (wiktionaryTemplate.startsWith("{{Deutsch Verb schwach untrennbar")) {
            return   Optional.of(Template.DE_VERB_WEAK_INSEPARABLE);
        } else if (wiktionaryTemplate.startsWith("{{Deutsch Verb schwach untrennbar reflexiv")) {
            return   Optional.of(Template.DE_VERB_WEAK_INSEPARABLE_REFLEXIVE);
        }else if (wiktionaryTemplate.startsWith("{{Deutsch Verb schwach trennbar")) {
            return   Optional.of(Template.DE_VERB_WEAK_SEPARABLE);
        }else if (wiktionaryTemplate.startsWith("{{Deutsch Verb schwach trennbar reflexiv")) {
            return   Optional.of(Template.DE_VERB_WEAK_SEPARABLE_REFLEXIVE);
        }else if (wiktionaryTemplate.startsWith("{{Deutsch Verb schwach doppelt trennbar")) {
            return   Optional.of(Template.DE_VERB_WEAK_DOUBLE_SEPARABLE);
        }else if (wiktionaryTemplate.startsWith("{{Deklinationsseite Adjektiv")) {
            return   Optional.of(Template.DE_ADJECTIVE_DEKLINITION);
        }
        return Optional.empty();
    }
}
