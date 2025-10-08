// 代码生成时间: 2025-10-08 18:54:58
package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class MemoryAnalysisService {

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    /**
     * Retrieves and returns memory usage details.
     *
     * @return ResponseEntity containing memory usage details
     */
    public ResponseEntity<String> getMemoryUsage() {
        try {
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            // StringBuilder is used to construct the memory usage report.
            StringBuilder memoryReport = new StringBuilder();
            memoryReport.append("Heap Memory Usage: 
")
                    .append("  Used: ").append(heapMemoryUsage.getUsed()).append(" bytes
")
                    .append("  Commited: ").append(heapMemoryUsage.getCommitted()).append(" bytes
")
                    .append("  Max: ").append(heapMemoryUsage.getMax()).append(" bytes
")
                    .append("  Initilized: ").append(heapMemoryUsage.getInit()).append(" bytes
");
            memoryReport.append("Non-Heap Memory Usage: 
")
                    .append("  Used: ").append(nonHeapMemoryUsage.getUsed()).append(" bytes
")
                    .append("  Commited: ").append(nonHeapMemoryUsage.getCommitted()).append(" bytes
")
                    .append("  Max: ").append(nonHeapMemoryUsage.getMax()).append(" bytes
")
                    .append("  Initilized: ").append(nonHeapMemoryUsage.getInit()).append(" bytes
");

            return new ResponseEntity<>(memoryReport.toString(), HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception and return a server error response.
            // In a real-world scenario, you would use a logging framework like SLF4J.
            String errorMessage = "Error while retrieving memory usage details: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
