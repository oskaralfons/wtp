package com.niklar.wtp;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *  JOrtho
 *
 *  Copyright (C) 2005-2009 by i-net software
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as
 *  published by the Free Software Foundation; either version 2 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 *  USA.
 *
 * Created on 16.06.2009
 */

public class ParsingUtils {
    /**
     * Read the information of the template placeholder
     *
     * @return null if nothing find
     */
    static Properties parseRule(String wikiText, String tempalateName, int fromIndex) {
        int start = findTemplate(wikiText, tempalateName, fromIndex);
        if (start > 0) {
            final int length = wikiText.length();
            int braces = 2;
            for (int end = start; end < length; end++) {

                switch (wikiText.charAt(end)) {
                    case '{':
                        braces++;
                        break;
                    case '}':
                        if (--braces == 0) {
                            return parseRule(wikiText, start, end - 1);
                        }
                        break;
                }
            }
        }
        return null;
    }

    /**
     * Find a template name in the wiki text. the problem are possible whitespaces.
     *
     * @param wikiText
     * @param tempalateName
     * @return the index after the first | or -1.
     */
    static int findTemplate(String wikiText, String tempalateName, int fromIndex) {
        //find {{  tempalateName  |
        Pattern pattern = Pattern.compile("\\{\\{\\s*\\Q" + tempalateName.replace(" ", "\\E\\s+\\Q") + "\\E\\s*\\|");
        Matcher matcher = pattern.matcher(wikiText);

        if (matcher.find(fromIndex)) {
            return matcher.end();
        }

        return -1;
    }

    /**
     * Read the information of the template placeholder
     */
    static Properties parseRule(String wikiText, int idxStart, int idxEnd) {
        Properties props = new Properties();
        int idxName = 1;

        int bracket = 0;
        int valueStart = idxStart;
        for (; idxStart < idxEnd; idxStart++) {
            char ch = wikiText.charAt(idxStart);
            switch (ch) {
                case '[':
                    bracket++;
                    break;
                case ']':
                    bracket--;
                    break;
                case '|':
                    if (bracket == 0) {
                        String value = wikiText.substring(valueStart, idxStart).trim();
                        int idx = value.indexOf('=');
                        if (idx > 0) {
                            String name = value.substring(0, idx);
                            value = value.substring(idx + 1);
                            props.setProperty(name.trim(), value.trim());
                        } else {
                            props.setProperty(Integer.toString(idxName++), value.trim());
                        }
                        valueStart = idxStart + 1;
                    }
            }
        }
        String value = wikiText.substring(valueStart, idxEnd).trim();
        int idx = value.indexOf('=');
        if (idx > 0) {
            String name = value.substring(0, idx);
            value = value.substring(idx + 1);
            props.setProperty(name.trim(), value.trim());
        } else {
            props.setProperty(Integer.toString(idxName++), value.trim());
        }
        return props;
    }

    public static boolean isValidWord(String word){
        if( word == null ){
            return false;
        }
        final int length = word.length();
        if(length <= 1) return false;
        int last = length - 1;
        for( int i = last; i >= 0; i-- ) {
            char ch = word.charAt( i );
            if( Character.isLetter( ch ) ) {
                continue;
            }
            if( ch == '\'' ) {
                if( i == last && word.charAt( 0 ) == '\'' ) {
                    return false;
                }
                if( i > 0 && word.charAt( i - 1 ) == '\'' ) {
                    return false;
                }
                continue;
            }

            //Bei Abkürzungen einen Punkt am Ende
            if( ch == '.' && i == last ) {
                continue;
            }

            //Bindestriche nur in der Wortmitte
            if( ch == '-' && i != 0 && i != last ) {
                continue;
            }

            return false;
        }
        return true;
    }
}
