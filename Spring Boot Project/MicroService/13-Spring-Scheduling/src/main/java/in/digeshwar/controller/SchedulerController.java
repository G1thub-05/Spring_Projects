package in.digeshwar.controller;

import in.digeshwar.components.ReportScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController {

    private final ReportScheduler scheduler;

    public SchedulerController(ReportScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @GetMapping("/scheduler/last-run")
    public String getLastRun() {
        return "Last run at: " + scheduler.getLastRunTime();
    }
}