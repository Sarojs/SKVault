/*
 * HumanTime.java
 * 
 * Created on 06.10.2008
 * 
 * Copyright (c) 2008 Johann Burkard (<mailto:jb@eaio.com>) <http://eaio.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package SKUtility;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.util.Log;

final public class SKDateFormatter {

	/*
	static public String humanFormat(Date date) {

		Date now = new Date();
		long interval = SKDateFormatter.getDateDiff(date, now, TimeUnit.MILLISECONDS);
		String result;
		String plural = "";

		if (interval > 31536000) {
			float number = interval / 31536000;
			if (number > 1)
				plural = "s";
			result = number + " year" + plural + " ago";
		} else if (interval > 2592000) {
			float number = interval / 2592000;
			if (number > 1)
				plural = "s";
			result = number + " month" + plural + " ago";
		} else if (interval > 86400) {
			float number = interval / 86400;
			if (number > 1)
				plural = "s";
			result = number + " day" + plural + " ago";
		} else if (interval > 3600) {
			float number = interval / 3600;
			if (number > 1)
				plural = "s";
			result = number + " hour" + plural + " ago";
		} else if (interval > 60) {
			float number = interval / 60;
			if (number > 1)
				plural = "s";
			result = number + " minute" + plural + " ago";
		} else {
			if (interval > 1)
				plural = "s";
			result = interval + " month" + plural + " ago";
		}
		return result;
	}
	*/
	
	public static String humanFormat(Date date){
		
		Date now = new Date();
		long duration = SKDateFormatter.getDateDiff(date, now, TimeUnit.MILLISECONDS);
		
		String result = SKDateFormatter.friendlyTimeDiff(duration);
		result = result + " ago";
		
		return result;
	}
	
	
	private static String friendlyTimeDiff(long timeDifferenceMilliseconds) {
		
	    long diffSeconds = timeDifferenceMilliseconds / 1000;
	    long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
	    long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
	    long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);
	    long diffWeeks = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 7);
	    long diffMonths = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 30.41666666));
	    long diffYears = (long) (timeDifferenceMilliseconds / (60 * 60 * 1000 * 24 * 365));

	    if (diffSeconds < 1) {
	        return "less than a second";
	    } else if (diffMinutes < 1) {
	        return diffSeconds + " seconds";
	    } else if (diffHours < 1) {
	        return diffMinutes + " minutes";
	    } else if (diffDays < 1) {
	        return diffHours + " hours";
	    } else if (diffWeeks < 1) {
	        return diffDays + " days";
	    } else if (diffMonths < 1) {
	        return diffWeeks + " weeks";
	    } else if (diffYears < 1) {
	        return diffMonths + " months";
	    } else {
	        return diffYears + " years";
	    }
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
