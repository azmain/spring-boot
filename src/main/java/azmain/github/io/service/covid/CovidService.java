package azmain.github.io.service.covid;

import azmain.github.io.domain.covid.CovidDailyDataReport;

import java.io.IOException;
import java.util.List;

public interface CovidService {

    List<CovidDailyDataReport> fetchCovidDailyDataReport();
}
