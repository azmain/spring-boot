package azmain.github.io.service.covid;

import azmain.github.io.domain.covid.CaseType;
import azmain.github.io.domain.covid.CovidDailyDataReport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidServiceImpl implements CovidService {

    private static String CASES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private static String DEATHS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

    private static String RECOVERY_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private HashMap<String, CovidDailyDataReport> dailyDataReports = new HashMap<>();

    private final RestTemplate restTemplate;

    public CovidServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CovidDailyDataReport> fetchCovidDailyDataReport() {
        return this.dailyDataReports.values().stream().collect(Collectors.toList());
    }

    @PostConstruct
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleDataFetch(){
        System.out.println("Fetching Data From Server at "+ new Date());
        this.fetchData(CASES_DATA_URL, CaseType.AFFECTED);
        this.fetchData(DEATHS_DATA_URL, CaseType.DEATH);
        this.fetchData(RECOVERY_DATA_URL, CaseType.RECOVERY);
    }

    public void fetchData(String url, CaseType caseType){

        try {

            String data = restTemplate.getForObject(url, String.class);

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(data));

            for(CSVRecord record: records){

                String country = record.get("Country/Region");

                CovidDailyDataReport report = dailyDataReports.get(country);
                if(report == null){
                    report = new CovidDailyDataReport();
                    report.setCountry(country);
                    dailyDataReports.put(country, report);
                }

                BigInteger last = new BigInteger(record.get(record.size()-1));
                BigInteger prev = new BigInteger(record.get(record.size()-2));
                BigInteger diff = last.subtract(prev);

                switch (caseType){
                    case AFFECTED:
                        report.setTotalCases(last);
                        report.setNewCases(diff);
                        break;

                    case DEATH:
                        report.setTotalDeaths(last);
                        report.setNewDeaths(diff);
                        break;

                    case RECOVERY:
                        report.setTotalRecovered(last);
                        break;
                }
            }
        }
        catch (Exception e){
           throw new RuntimeException("CSV parse error.");
        }
    }
}

