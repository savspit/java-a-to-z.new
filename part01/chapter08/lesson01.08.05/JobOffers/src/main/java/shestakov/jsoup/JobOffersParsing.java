package shestakov.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shestakov.sql.JobOffersStorage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Job offers parsing.
 */
public class JobOffersParsing {
    private static final Logger Log = LoggerFactory.getLogger(JobOffersParsing.class);
    private JobOffersStorage storage = new JobOffersStorage();

    /**
     * Instantiates a new Job offers parsing.
     *
     * @param storage the db working
     */
    public JobOffersParsing(JobOffersStorage storage) {
        this.storage = storage;
    }

    /**
     * Gets job offers.
     */
    public void getJobOffers() {
        if (this.storage.isFirstRun()) {
            getJobOffersFromStartDate(getCurrentDateMinusYear());
        } else {
            getJobOffersFromStartDate(this.storage.getLastRunTime());
        }
    }

    /**
     * Gets job offers from start date.
     *
     * @param startDate the start date
     */
    public void getJobOffersFromStartDate(long startDate) {
        int offersPageCount = 0;
        String offerLink = null;
        String offerText = null;
        long offerDate = 0L;
        String offerAuthor = null;
        String offerAuthorLink = null;
        boolean needToBreak = false;
        do {
            try {
                Document document = Jsoup.connect(getNextMainJobOffersUrl(offersPageCount++))
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                Elements allElements = document.select("tr");
                for (Element elem : allElements) {
                    Elements post = elem.select("td.postslisttopic");
                    Elements isClosedPost = post.select("span.closedTopic");
                    if (isClosedPost.size() == 0) {
                        Elements jobOffer = post.select("[href*=sql.ru]");
                        if (jobOffer.hasClass("") && isJavaJobOffer(jobOffer) ) {
                            offerLink = jobOffer.attr("href");
                            offerText = getOfferText(jobOffer.text());
                            Elements authorAndTimeElements = elem.select("td.altCol");
                            for (Element authorAndTimeElem : authorAndTimeElements) {
                                if (authorAndTimeElem.html().contains("sql.ru")) {
                                    offerAuthor = authorAndTimeElem.text();
                                    offerAuthorLink = getUrlFromString(authorAndTimeElem.html());
                                } else {
                                    offerDate = convertDateTimeToTimestamp(authorAndTimeElem.text());
                                }
                            }
                            if (offerDate < startDate) {
                                needToBreak = true;
                                break;
                            }
                            storage.addDataInDB(offerLink, offerText, offerAuthor, offerAuthorLink, offerDate);
                        }
                    }
                }
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        } while (!needToBreak);
    }

    /**
     * Is java job offer boolean.
     *
     * @param jobOffer the job offer
     * @return the boolean
     */
    public boolean isJavaJobOffer(Elements jobOffer) {
        return jobOffer.text().toUpperCase().contains("JAVA") && !jobOffer.text().toUpperCase().contains("SCRIPT");
    }

    /**
     * Gets next main job offers url.
     *
     * @param offersPageCount the offers page count
     * @return the next main job offers url
     */
    public String getNextMainJobOffersUrl(int offersPageCount) {
        return String.format("%s/%s", this.storage.getOffersUrl(), offersPageCount);
    }

    /**
     * Gets current date minus year.
     *
     * @return the current date minus year
     */
    public long getCurrentDateMinusYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime().getTime();
    }

    /**
     * Gets offer text.
     *
     * @param offerText the offer text
     * @return the offer text
     */
    public String getOfferText(String offerText) {
        return offerText.replace(" [new]","");
    }

    /**
     * Gets url from string.
     *
     * @param stringWithUrl the string with url
     * @return the url from string
     */
    public String getUrlFromString(String stringWithUrl) {
        if (stringWithUrl.isEmpty()) {
            return stringWithUrl;
        }
        String[] multiStr = stringWithUrl.split("\"");
        return multiStr[1];
    }

    /**
     * Convert date time to timestamp long.
     *
     * @param strDateTime the str date time
     * @return the long
     */
    public long convertDateTimeToTimestamp(String strDateTime) {
        long result = 0L;
        if (strDateTime.contains("сегодня")) {
            strDateTime = strDateTime.replace("сегодня","");
            Date resultdate = new Date(System.currentTimeMillis());
            strDateTime = new SimpleDateFormat("dd MMM yy").format(resultdate) + strDateTime;
        } else if (strDateTime.contains("вчера")) {
            strDateTime = strDateTime.replace("вчера","");
            Date resultdate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            strDateTime = new SimpleDateFormat("dd MMM yy").format(resultdate) + strDateTime;
        }
        try {
            result = new SimpleDateFormat("dd MMM yy, HH:mm").parse(strDateTime).getTime();
        } catch (ParseException e) {
            Log.error(e.getMessage(), e);
        }
        return result;
    }
}
