package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int totalSalaryOfAPerson = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splittedLine = data[j].split(DELIMITER);
                if (!splittedLine[NAME_INDEX].equals(names[i])) {
                    continue;
                }
                if (isDateRelevant(splittedLine[DATE_INDEX], dateFrom, dateTo)) {
                    totalSalaryOfAPerson += Integer.parseInt(splittedLine[HOURS_INDEX])
                            * Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalaryOfAPerson);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    private boolean isDateRelevant(String dataToCheck, String dataFrom, String dataTo) {
        LocalDate currentDate = LocalDate.parse(dataToCheck, FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dataFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dataTo, FORMATTER);
        return currentDate.compareTo(localDateFrom) >= 0 && currentDate.compareTo(localDateTo) <= 0;
    }
}
