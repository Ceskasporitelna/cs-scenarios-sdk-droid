package cz.csas.scenarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 18/08/2017
 */

public class Utils {
    final static int COLON_INDEX = 22;

    public static Date getISO8601Date(String time) {
        if (time == null)
            return null;
        if (!time.contains("+"))
            time = time + "Z";
        String s = time.replace("Z", "+00:00");
        try {
            if (s.length() > COLON_INDEX && s.charAt(COLON_INDEX) == ':') {
                s = s.substring(0, COLON_INDEX) + s.substring(COLON_INDEX + 1);  // to get rid of the ":"
            }
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).parse(s);
            return date;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getISO8601String(Date time) {
        if (time == null)
            return null;
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
                .format(time);
        return formatted.substring(0, COLON_INDEX) + ":" + formatted.substring(COLON_INDEX);
    }
}
