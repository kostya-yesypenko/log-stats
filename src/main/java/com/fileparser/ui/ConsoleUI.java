package com.fileparser.ui;

import com.fileparser.model.OrderAttribute;
import com.fileparser.parser.JsonStreamReader;
import com.fileparser.parser.OrderJsonParser;
import com.fileparser.service.StatisticsService;
import com.fileparser.service.ThreadPoolService;
import com.fileparser.service.XmlWriterService;
import com.fileparser.util.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ConsoleUI {

    public void start(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java -jar app.jar <folder> <attribute> [threads]");
            return;
        }

        String folder = args[0];
        String attribute = args[1];
        int threads = args.length >= 3 ? Integer.parseInt(args[2]) : 4;

        OrderAttribute orderAttr = OrderAttribute.valueOf(attribute);

        List<File> files = FileUtils.getJsonFiles(folder);

        OrderJsonParser parser = new JsonStreamReader();
        ThreadPoolService executor = new ThreadPoolService(threads);

        List<com.fileparser.model.Order> orders = executor.parseFiles(files, parser);

        StatisticsService statsService = new StatisticsService();
        Map<String, Integer> stats = statsService.calculateStats(orders, orderAttr);

        new XmlWriterService().writeStatistics(stats, attribute);

        System.out.println("Statistics file has been created!");
    }
}
