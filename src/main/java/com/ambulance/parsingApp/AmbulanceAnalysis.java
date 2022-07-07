package com.ambulance.parsingApp;

import org.springframework.data.relational.core.sql.In;
import org.springframework.ui.Model;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AmbulanceAnalysis {
    List<String> years;

    //Прогноз делается по методу скользящих средних
    public List<Integer> forecast(String forecastYear, AmbulanceEntityRepo repo) throws Exception {
        Model forecastModel = null;

        //Забираем все значения по вызовам из дб и закидываем их в один большой лист
        var dataPerYear = getForecastData(repo);
        //Проверка на небходимость прогноза
        if (!dataPerYear.get(years.indexOf(forecastYear)).contains(0)) {
            System.out.println("Известны все значения в году, прогноз не требуется");
            return null;
        }

        //Находим среднее для каждых трех элементов в каждом годы
        List<List<Integer>> avgPerYear = new ArrayList<>();
        for (List<Integer> year: dataPerYear) {
            List<Integer> avg = new ArrayList<>();
            //Отбрасываем год, если в нем отсутсвуют какие-либо данные
            if (year.contains(0))
                continue;
            for (int j=2;j<year.size();j++)
                avg.add((year.get(j-2)+year.get(j-1)+year.get(j))/3);

            avgPerYear.add(avg);
        }

        //Находим среднее средних
        int[] rollingAvg = new int[10];
        for (List<Integer> integers : avgPerYear)
            for (int j = 0; j < 10; j++) {
                rollingAvg[j] = rollingAvg[j] + integers.get(j);
            }
        for (int i=0;i<rollingAvg.length; i++)
            rollingAvg[i]=rollingAvg[i]/avgPerYear.size();

        List<Integer> forecast = dataPerYear.get(years.indexOf(forecastYear));
        if (forecast.get(0)==0 || forecast.get(1)==0) {
            for (int i = 0; i<forecast.size();i++)
                if (forecast.get(i) == 0) forecast.set(i, rollingAvg[i]);
        }
        else {
            for (int i=2; i<forecast.size();i++)
                if (forecast.get(i) == 0)
                    forecast.set(i, 3 * rollingAvg[i - 2] - forecast.get(i - 2) - forecast.get(i - 1));
        }


        System.out.println(forecast);
        return forecast;
    }

    //Соберем данные для постройки прогноза
    List<List<Integer>> getForecastData(AmbulanceEntityRepo repo) throws Exception {
        File directory = new File("src/data");
        years = new ArrayList<>();

        //Получаем года, по которым есть информация
        for (File year: directory.listFiles())
            years.add(year.getName());

        List<List<Integer>> dataPerYear = new ArrayList<>();
        List<Integer> values;

        //Получаем информацию по вызовам
        for (String year : years) {
            values = new ArrayList<>();
            for (int j = 0; j < 12; j++)
                //Забираем данные по вызовам за каждый месяц по годам
                values.add(repo.countAllByCallDateBetween(new SimpleDateFormat(
                                "dd.MM.yyyy", Locale.ENGLISH).parse("01." + (j + 1) + "." + year),
                        new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse("31." + (j + 1) + "." + year)));
            dataPerYear.add(values);
        }
        return dataPerYear;
    }
}

