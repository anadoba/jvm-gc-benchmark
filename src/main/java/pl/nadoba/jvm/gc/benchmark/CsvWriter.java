package pl.nadoba.jvm.gc.benchmark;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvWriter {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final char DELIMITER = ';';
    private static final Object[] FILE_HEADERS = {"allocations", "millis"};

    private CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR).withDelimiter(DELIMITER);

    public void write(String filename, List<Measurement> measurements) {
        FileWriter fileWriter;
        CSVPrinter csvFilePrinter;

        try {
            fileWriter = new FileWriter(filename);
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            csvFilePrinter.printRecord(FILE_HEADERS);

            for (Measurement measurement: measurements) {
                List<String> measurementRecord = new ArrayList<>();
                measurementRecord.add(String.valueOf(measurement.getAllocations()));
                measurementRecord.add(String.valueOf(measurement.getMillis()));

                csvFilePrinter.printRecord(measurementRecord);
            }

            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
