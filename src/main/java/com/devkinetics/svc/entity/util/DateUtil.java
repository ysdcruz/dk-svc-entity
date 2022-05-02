package com.devkinetics.svc.entity.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;

@Slf4j
public class DateUtil {

    public DateUtil() {
    }

    public static Date getCurrentServerDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Manila"));
        return new java.sql.Timestamp(calendar.getTime().getTime());
    }

    public static Date addDays(Date date, int days) {
        Instant dateTimeStamp = date.toInstant();

        LocalDate convertedDate = dateTimeStamp.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate = convertedDate.plusDays(days);

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date subtractDays(Date date, int days) {
        Instant dateTimeStamp = date.toInstant();

        LocalDate convertedDate = dateTimeStamp.atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate = convertedDate.minusDays(days);

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date formatDate(Date date, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateFormat.format(date));
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return date;
        }
    }

    public static Date getEarliestDateInAnArrayOfDates(List<String> lstDates, String format) {
        Date earliestDate = null;

        if (lstDates != null && !lstDates.isEmpty()) {
            for (String date : lstDates) {
                if (date != null) {
                    if (earliestDate == null) {
                        earliestDate = DateUtil.convertStringDateToDateWithFormat(date, format);
                    } else {
                        Date selectedDate = DateUtil.convertStringDateToDateWithFormat(date, format);

                        if (DateUtil.isDateOneSameOrBeforeDateTwo(selectedDate, earliestDate)) {
                            earliestDate = DateUtil.convertStringDateToDateWithFormat(date, format);
                        }
                    }
                }
            }
        }

        return earliestDate;
    }

    public static Date getFarthestDateInAnArrayOfDates(List<String> lstDates, String format) {
        Date farthestDate = null;

        if (lstDates != null && !lstDates.isEmpty()) {
            for (String date : lstDates) {
                if (date != null) {
                    if (farthestDate == null) {
                        farthestDate = DateUtil.convertStringDateToDateWithFormat(date, format);
                    } else {
                        Date selectedDate = DateUtil.convertStringDateToDateWithFormat(date, format);

                        if (DateUtil.isDateOneSameOrAfterDateTwo(selectedDate, farthestDate)) {
                            farthestDate = DateUtil.convertStringDateToDateWithFormat(date, format);
                        }
                    }
                }
            }
        }

        return farthestDate;
    }

    public static List<String> rearrangeDateList(List<String> lstDates, String format, String order) {
        List<String> lstArrangedDate = new ArrayList<>();
        String date = null;
        String convertedDateFormat = null;
        int dateIndex = -1;
        int arrangedDateIndex = -1;

        if (lstDates != null && !lstDates.isEmpty()) {
            for (int i = lstDates.size(); i > 0; i--) {
                if (order.equals("desc")) {
                    date = DateUtil.convertDateToStringWithFormat(DateUtil.getFarthestDateInAnArrayOfDates(lstDates, format), format);
                } else {
                    date = DateUtil.convertDateToStringWithFormat(DateUtil.getEarliestDateInAnArrayOfDates(lstDates, format), format);
                }

                dateIndex = lstDates.indexOf(date);
                arrangedDateIndex = lstArrangedDate.indexOf(date);

                if (dateIndex != -1) {
                    lstDates.remove(dateIndex);
                }

                if (arrangedDateIndex == -1) {
                    lstArrangedDate.add(date);
                }
            }
        }

        return lstArrangedDate;
    }

    public static String convertDateToStringWithFormat(Date date, String format) {

        // date and format must not be null
        if (date == null || format == null) {
            log.error("Null date and format");
            return null;
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return null;
        }
    }

    public static Date convertStringDateToDateWithFormat(String date, String format) {

        // date and format must not be null
        if (date == null || format == null) {
            log.error("Null date and format");
            return null;
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(date);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return null;
        }
    }

    public static boolean isTwoDatesTheSame(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.equals(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isDateOneSameOrBeforeDateTwo(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.equals(dateTwo) || dateOne.before(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isDateOneBeforeDateTwo(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.before(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isDateOneAfterDateTwo(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.after(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isDateOneSameOrAfterDateTwo(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.equals(dateTwo) || dateOne.after(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isDateOneAfterDateTwoWithTimeChecking(Date date1, Date date2) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

            Date dateOne = dateFormat.parse(dateFormat.format(date1));
            Date dateTwo = dateFormat.parse(dateFormat.format(date2));

            return dateOne.after(dateTwo);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static boolean isCurrentDateIsOnOrBeforeDate(Date endDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT_YYYY_MM_DD);

            Date currentDate = dateFormat.parse(dateFormat.format(getCurrentServerDate()));
            Date inputEndDate = dateFormat.parse(dateFormat.format(endDate));

            return currentDate.before(inputEndDate) || currentDate.equals(inputEndDate);
        } catch (Exception ex) {
            log.error("Caught date parsing error -> {}", ex.getMessage());
            return false;
        }
    }

    public static Date addMillisecondsToDate(Date inputDate, int millisecondsToBeAdded) {
        Instant inputDateTimeStamp = inputDate.toInstant();
        LocalDateTime inputDateConverted = inputDateTimeStamp.atZone(ZoneId.systemDefault()).toLocalDateTime();

//        log.info("Local Date Time: {}", inputDateConverted);
        LocalDateTime dateMillisecondsAdded = inputDateConverted.plus(millisecondsToBeAdded, ChronoField.MILLI_OF_SECOND.getBaseUnit());
//        log.info("Local Date Time After Adding Milliseconds: {}", dateMillisecondsAdded);

        return Date.from(dateMillisecondsAdded.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getDayFromDate(Date inputDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");
        return simpleDateFormat.format(inputDate);
    }

}
