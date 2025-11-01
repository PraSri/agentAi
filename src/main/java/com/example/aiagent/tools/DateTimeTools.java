package com.example.aiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTools {

    @Tool(
            name = "GET_DATE_TIME",
            description = "Returns the current date and time in the user's timezone in ISO-8601 format. Use this to calculate future or past times for scheduling alarms or reminders."
    )
    String getCurrentDateTime() {
        return LocalDateTime.now()
                .atZone(LocaleContextHolder.getTimeZone().toZoneId())
                .toString();
    }

// TODO: This tool is not working properly with llama3.2 local LLM
    @Tool(
            name = "SET_ALARM",
            description = """
                    Use this tool whenever the user asks to set, schedule, or create an alarm, reminder, or alert for a specific time or duration.
                    The tool accepts one parameter — 'time' — which must be in ISO-8601 format (e.g., 'YYYY-MM-DDTHH:MM:ss').
                    If the user asks to set an alarm like '10 minutes from now', first call the GET_DATE_TIME tool to obtain the current time, then calculate the future time and pass it here.
                    Only call this tool when the user explicitly or implicitly requests an alarm or reminder.
                    """
    )
    void setAlarm(
            @ToolParam(description = "Exact time to trigger the alarm, in ISO-8601 format. Compute this param using GET_DATE_TIME and user request. (e.g., 'YYYY-MM-DDTHH:MM:ss')")
            String time
    ) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Alarm set for " + alarmTime);
    }

}
