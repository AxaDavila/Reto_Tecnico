package com.sqa.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import com.sqa.datepicker.interactions.SwitchToDatepickerFrame;
import com.sqa.datepicker.interactions.WaitForCalendar;

import static com.sqa.datepicker.ui.DatepickerPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectDateFromCalendar implements Task {

    private final String day;
    private final boolean needsNextMonth;

    public SelectDateFromCalendar(String day, boolean needsNextMonth) {
        this.day = day;
        this.needsNextMonth = needsNextMonth;
    }

    public static SelectDateFromCalendar day(String day) {
        return instrumented(SelectDateFromCalendar.class, day, false);
    }

    public static SelectDateFromCalendar dayFromNextMonth(String day) {
        return instrumented(SelectDateFromCalendar.class, day, true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            SwitchToDatepickerFrame.inside(DATEPICKER_IFRAME),
            Click.on(DATE_INPUT_FIELD),
            WaitForCalendar.toBeVisible(CALENDAR_WIDGET)
        );

        if (needsNextMonth) {
            actor.attemptsTo(
                Click.on(NEXT_MONTH_BUTTON)
            );
        }

        actor.attemptsTo(
            Click.on(CALENDAR_DAY.of(day))
        );
    }
}